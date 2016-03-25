package tp3_akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActorSystem system1 = ActorSystem.create("MySystem1");
		ActorSystem system2 = ActorSystem.create("MySystem2");

		ActorRef greeter1, greeter2, greeter3, greeter4, greeter5, greeter6;

		greeter1 = system1.actorOf(
				Props.create(GreetingActor.class, "Acteur 1"), "greeter1");
		greeter2 = system2.actorOf(
				Props.create(GreetingActor.class, "Acteur 2"), "greeter2");
		greeter3 = system2.actorOf(
				Props.create(GreetingActor.class, "Acteur 3"), "greeter3");
		greeter4 = system2.actorOf(
				Props.create(GreetingActor.class, "Acteur 4"), "greeter4");
		greeter5 = system1.actorOf(
				Props.create(GreetingActor.class, "Acteur 5"), "greeter5");
		greeter6 = system1.actorOf(
				Props.create(GreetingActor.class, "Acteur 6"), "greeter6");

		greeter1.tell(new AddActor(greeter2), ActorRef.noSender());
		greeter1.tell(new AddActor(greeter5), ActorRef.noSender());
		greeter2.tell(new AddActor(greeter3), ActorRef.noSender());
		greeter2.tell(new AddActor(greeter4), ActorRef.noSender());
		greeter5.tell(new AddActor(greeter6), ActorRef.noSender());

		// Cas de la question 5
		greeter4.tell(new AddActor(greeter6), ActorRef.noSender());

		// Cas afin de verifier qu'on empêche les boucles infinies
		greeter3.tell(new AddActor(greeter1), ActorRef.noSender());

		// Envois de messages
		System.out.println("Départ a partir de l'acteur 1 :");
		greeter2.tell(new Greeting("Main"), ActorRef.noSender());

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		system1.shutdown();
		system2.shutdown();
	}
}
