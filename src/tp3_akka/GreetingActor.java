package tp3_akka;

import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class GreetingActor extends UntypedActor {

	public List<ActorRef> childs;
	public String name;

	public GreetingActor(String name, List<ActorRef> system) {
		this.childs = system;
		this.name = name;
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof Greeting) {
			System.out.println(name + " Recois les greeting de : " + message);
			if (childs != null) {
				for (ActorRef a : childs) {
					a.tell(new Greeting(name), ActorRef.noSender());
				}
			}
		} else if (message instanceof String) {
			System.out.println("Message : " + message);
		} else {
			unhandled(message);
		}
	}
}
