package main.util.factory;

import main.module.game.Game;
import main.module.game.level.Level;
import main.util.exceptions.InvalideFileException;

import java.io.File;

public class LevelFactory {
	public static Level createLevel(Game game, File file) {
		try {
			return new Level(game, file);
		} catch (InvalideFileException e) {
			e.printStackTrace();
		}
		return null;
	}
}
