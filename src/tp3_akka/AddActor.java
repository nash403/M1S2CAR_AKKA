package tp3_akka;

import java.io.Serializable;

import akka.actor.ActorRef;

public class AddActor implements Serializable {
	public ActorRef actorToAdd;

	public AddActor(ActorRef actorRef) {
		this.actorToAdd = actorRef;
	}

	@Override
	public String toString() {
		return actorToAdd.toString();
	}

}
