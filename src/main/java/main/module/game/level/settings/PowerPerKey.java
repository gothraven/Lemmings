package main.module.game.level.settings;


import main.module.game.level.lemming.power.Power;

public enum PowerPerKey {
	A(Power.BLOCKER), Z(Power.BOMBER), E(Power.BUILDER),
	Q(Power.CLIMBER), S(Power.DIGGER), D(Power.MINER),
	W(Power.PARATROOPER);


	Power power;

	PowerPerKey (Power power) {
		this.power = power;
	}

	public Power getPower () {
		return power;
	}
}
