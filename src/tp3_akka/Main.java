package tp3_akka;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("MySystem");
		ActorRef greeter1, greeter2, greeter3, greeter4, greeter5, greeter6;

		List<ActorRef> childsActor1 = new ArrayList<ActorRef>();
		List<ActorRef> childsActor2 = new ArrayList<ActorRef>();
		List<ActorRef> childsActor3 = null;
		List<ActorRef> childsActor4 = null;
		List<ActorRef> childsActor5 = new ArrayList<ActorRef>();
		List<ActorRef> childsActor6 = null;

		greeter6 = system.actorOf(
				Props.create(GreetingActor.class, "Acteur 6", childsActor6),
				"greeter6");

		childsActor5.add(greeter6);
		greeter5 = system.actorOf(
				Props.create(GreetingActor.class, "Acteur 5", childsActor5),
				"greeter5");
		greeter4 = system.actorOf(
				Props.create(GreetingActor.class, "Acteur 4", childsActor4),
				"greeter4");
		greeter3 = system.actorOf(
				Props.create(GreetingActor.class, "Acteur 3", childsActor3),
				"greeter3");

		childsActor2.add(greeter3);
		childsActor2.add(greeter4);
		greeter2 = system.actorOf(
				Props.create(GreetingActor.class, "Acteur 2", childsActor2),
				"greeter2");

		childsActor1.add(greeter2);
		childsActor1.add(greeter5);
		greeter1 = system.actorOf(
				Props.create(GreetingActor.class, "Acteur 1", childsActor1),
				"greeter1");

		// Envois de messages
		System.out.println("Départ a partir de l'acteur 1 :");
		greeter1.tell(new Greeting("Main"), ActorRef.noSender());

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\nDépart a partir de l'acteur 2 :");
		greeter2.tell(new Greeting("Main"), ActorRef.noSender());

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		system.shutdown(); // arrêt du système (fin des acteurs)
	}
}
