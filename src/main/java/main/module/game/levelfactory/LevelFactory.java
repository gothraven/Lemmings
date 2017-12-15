package main.module.game.levelfactory;

import main.module.game.Game;
import main.module.game.level.Level;
import main.util.exceptions.InvalideFileException;

import java.io.InputStreamReader;

public class LevelFactory {
	public static Level createLevel(Game game, InputStreamReader isr) {
		try {
			return new Level(game, isr);
		} catch (InvalideFileException e) {
			e.printStackTrace();
		}
		return null;
	}
}
