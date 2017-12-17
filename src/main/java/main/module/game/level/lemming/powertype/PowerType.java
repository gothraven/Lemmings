package main.module.game.level.lemming.powertype;

import main.module.game.level.lemming.state.State;

public enum PowerType {
	CLIMBER(State.CLIMBER,"Climber"), PARATROOPER(State.PARATROOPER,"Paratrooper"),
	BLOCKER(State.BLOCKER,"Blocker"), BOMBER(State.BOMBER, "Bomber"),
	BUILDER(State.BUILDER, "Builder"), DIGGER(State.DIGGER, "Digger"),
	MINER(State.MINER, "Miner");

	private String name;
	private State state;

	PowerType (State state, String name) {
		this.name = name;
		this.state = state;
	}

	public String getName () {
		return name;
	}

	public State getState () {
		return state;
	}
}

