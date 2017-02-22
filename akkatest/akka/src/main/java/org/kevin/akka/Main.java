package org.kevin.akka;

import org.kevin.akka.actor.HelloWorldActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Main {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("Hello");
		ActorRef a = system.actorOf(Props.create(HelloWorldActor.class), "helloWorld");
		system.actorOf(Props.create(Terminator.class, a), "terminator");
	}

	public static class Terminator extends UntypedActor {

		private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
		private final ActorRef ref;

		public Terminator(ActorRef ref) {
			this.ref = ref;
			getContext().watch(ref);
		}

		@Override
		public void onReceive(Object msg) {
			if (msg instanceof Terminated) {
				log.info("{} has terminated, shutting down system", ref.path());
				getContext().system().terminate();
			} else {
				unhandled(msg);
			}
		}

	}
}
