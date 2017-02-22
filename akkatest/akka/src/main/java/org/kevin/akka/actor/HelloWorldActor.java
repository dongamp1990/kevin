package org.kevin.akka.actor;

import javax.annotation.Resource;

import org.kevin.akka.SpringExt;
import org.springframework.stereotype.Component;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;

@Component("helloWorldActor")
public class HelloWorldActor extends UntypedActor {

	@Resource
	SpringExt springExt;
	@Resource
	ActorSystem actorSystem;
	
	@Override
	public void preStart() throws Exception {
//		ActorRef greeter = actorSystem.actorOf(springExt.props("greeterActor"), "greeter");
//		greeter.tell(GreeterActor.Msg.GREET, getSelf());
	}

	@Override
	public void onReceive(Object msg) throws Throwable {
		if(msg == GreeterActor.Msg.GREET){
			System.out.println("HelloWorldActor receive greet msg");
		}else if (msg == GreeterActor.Msg.DONE) {
			System.out.println("HelloWorldActor receive done msg, stop application");
		} else {
			unhandled(msg);
		}
	}
}
