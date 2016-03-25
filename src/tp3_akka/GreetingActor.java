package tp3_akka;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class GreetingActor extends UntypedActor {

	public List<ActorRef> childs = new ArrayList<ActorRef>();
	public String name;
	public boolean messageReceived = false;

	public GreetingActor(String name) {
		this.name = name;
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof Greeting) {
			if (messageReceived == false) {
				System.out.println(name + " Recois les greeting de : "
						+ message);
				for (ActorRef a : childs) {
					a.forward(new Greeting(name), this.getContext());
				}
				messageReceived = true;
			}
		} else if (message instanceof AddActor) {
			childs.add(((AddActor) message).actorToAdd);
		} else {
			unhandled(message);
		}
	}
}
