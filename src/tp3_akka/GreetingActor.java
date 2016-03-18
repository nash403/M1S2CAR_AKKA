package tp3_akka;

import akka.actor.UntypedActor;

public class GreetingActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof Greeting) {
			System.out.println("Greeting : " + message);
		} else if (message instanceof String) {
			System.out.println("Message : " + message);
		} else {
			unhandled(message);
		}
	}
}
