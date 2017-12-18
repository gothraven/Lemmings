package main.view.level.lemming;

import java.awt.*;

public enum GLemmingState {
	WALKER(Color.ORANGE), BOMBER(Color.RED), BLOCKER(Color.BLACK), BUILDER(Color.BLUE),
	CLIMBER(Color.CYAN), DIGGER(Color.PINK), MINER(Color.DARK_GRAY), PARATROOPER(Color.YELLOW);

	private Color color;

	GLemmingState (Color color) {
		this.color = color;
	}

	public Color getColor () {
		return color;
	}
}
