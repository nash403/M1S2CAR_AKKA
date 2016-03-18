package tp3_akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef greeter1, greeter2;
		greeter1 = system
				.actorOf(Props.create(GreetingActor.class), "greeter1");
		greeter2 = system
				.actorOf(Props.create(GreetingActor.class), "greeter2");

		// Envois de messages
		greeter1.tell(new Greeting("Charlie Parker"), ActorRef.noSender());
		greeter2.tell(new Greeting("Bobby Marley"), ActorRef.noSender());

		system.shutdown(); // arrêt du système (fin des acteurs)
	}

}
