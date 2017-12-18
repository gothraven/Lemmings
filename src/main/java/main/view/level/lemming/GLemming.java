package main.view.level.lemming;

import main.module.game.level.lemming.Lemming;
import main.view.level.GamePanel;

import java.awt.*;

public class GLemming {

	public static void draw(Lemming lem, Graphics graphics) {
		graphics.setColor(getColor(lem));
		graphics.fillOval(lem.getPos().getX() * GamePanel.SCALE,
				lem.getPos().getY() * GamePanel.SCALE,
				GamePanel.SCALE,
				GamePanel.SCALE);
	}

	private static Color getColor (Lemming lem) {
		return GLemmingState.valueOf(lem.getPower().name()).getColor();
	}

}
