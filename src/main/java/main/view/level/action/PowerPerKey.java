package main.view.level.action;


import main.module.game.level.lemming.state.State;

public enum PowerPerKey {
	A(State.BLOCKER), Z(State.BOMBER), E(State.BUILDER),
	Q(State.CLIMBER), S(State.DIGGER), D(State.MINER),
	W(State.PARATROOPER);


	State power;

	PowerPerKey (State power) {
		this.power = power;
	}

	public State getPower () {
		return power;
	}
}
