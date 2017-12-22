package main.module.game.player;

import main.module.game.level.lemming.state.State;

public class Player {

	private static int SCORE_PER_LEM = 80;
	private static int SCORE_PER_SEC = 30;

	private String name;
	private long score;
	private State selectedPower;

	public Player (String name) {
		this(name, 0);
	}

	public Player (String name, long score) {
		this.name = name;
		this.score = score;
		this.selectedPower = null;
	}

	public String getName () {
		return name;
	}

	public void scoreUP(int nbLemmingsSaved, int timeLeftTillEnd) {
		this.score = (nbLemmingsSaved * SCORE_PER_LEM) + (timeLeftTillEnd * SCORE_PER_SEC);
	}

	public void changePower (State selectedPower) {
		System.out.println(selectedPower.name());
		this.selectedPower = selectedPower;
	}

	public long getScore () {
		return score;
	}

	@Override
	public String toString () {
		return "name :" + name +", score :" + score;
	}
}
