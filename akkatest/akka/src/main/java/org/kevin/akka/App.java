package org.kevin.akka;

import org.kevin.akka.actor.HelloWorldActor;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		akka.Main.main(new String[] {HelloWorldActor.class.getName()});
	}
}
