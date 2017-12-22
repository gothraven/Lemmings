package main.util.factory;

import main.module.game.level.lemming.Lemming;
import main.util.geometry.Position;

public class LemmingFactory {

	public static Lemming createLemming(Position pos) {
		return new Lemming(pos);
	}

}
