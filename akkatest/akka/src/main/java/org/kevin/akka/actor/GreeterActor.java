package org.kevin.akka.actor;

import org.springframework.stereotype.Component;

import akka.actor.UntypedActor;

@Component("greeterActor")
public class GreeterActor extends UntypedActor {

	public static enum Msg {  
	    GREET, DONE;  
	}
	
	@Override
	public void onReceive(Object msg) throws Throwable {
		if (msg == Msg.GREET) {
			System.out.println("receive greet msg");
			getSender().tell(Msg.DONE, getSelf());
		}else {
			unhandled(msg);
		}
	}

}
