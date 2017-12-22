package main.module.game.level.lemming.powertype;

import main.module.game.level.lemming.state.Power;

public enum PowerType {
	CLIMBER(Power.CLIMBER, "Climber"), PARATROOPER(Power.PARATROOPER, "Paratrooper"),
	BLOCKER(Power.BLOCKER, "Blocker"), BOMBER(Power.BOMBER, "Bomber"),
	BUILDER(Power.BUILDER, "Builder"), DIGGER(Power.DIGGER, "Digger"),
	MINER(Power.MINER, "Miner");

	private String name;
	private Power power;

	PowerType (Power power, String name) {
		this.name = name;
		this.power = power;
	}

	public String getName () {
		return name;
	}

	public Power getPower () {
		return power;
	}
}

