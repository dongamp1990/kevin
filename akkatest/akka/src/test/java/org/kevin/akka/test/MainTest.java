package org.kevin.akka.test;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kevin.akka.SpringExt;
import org.kevin.akka.actor.GreeterActor;
import org.kevin.akka.actor.HelloWorldActor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.testkit.JavaTestKit;
import akka.testkit.TestActorRef;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-core.xml")
public class MainTest{
	
	@Resource
	SpringExt springExt;
	@Resource
	ActorSystem actorSystem;
	
	@Test
	public void test2() {
//		ActorRef actor = actorSystem.actorOf(springExt.props("helloWorldActor"), "helloworld");
		//Inbox inbox = Inbox.create(actorSystem);
		//inbox.send(actor, GreeterActor.Msg.GREET);
		new JavaTestKit(actorSystem) {{
			final TestActorRef<HelloWorldActor> helloWorldActor = TestActorRef.create(actorSystem, springExt.props("helloWorldActor"), "helloworld");
			final TestActorRef<GreeterActor> greeterActor = TestActorRef.create(actorSystem, springExt.props("greeterActor"), "greeter");
			greeterActor.tell(GreeterActor.Msg.GREET, helloWorldActor);
		}};
	}
	
	@After
	public void destory() {
//		actorSystem.terminate();
	}
}
