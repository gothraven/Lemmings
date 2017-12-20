package main.view.level.map;

import java.awt.*;

public enum GTileType {
	WALL(Color.BLACK), BOX(Color.ORANGE), LAVA(Color.RED),
	ENTER(Color.YELLOW), EXIT(Color.GREEN), BOMB(Color.PINK),
	MAGIC(Color.CYAN), TELEPORT(Color.BLUE);

	private Color color;

	GTileType (Color color) {
		this.color = color;
	}

	public Color getColor () {
		return color;
	}
}
