package main.module.game.levelfactory;

import main.module.game.Game;
import main.module.game.level.Level;
import main.util.exceptions.InvalideFileException;

import java.io.File;

public class LevelFactory {
	public static Level createLevel(Game game, File file) {
		try {
			return new Level(game, file);
		} catch (InvalideFileException e) {
			System.out.println("lazem nelgaw hal hna");
			e.printStackTrace();
		}
		return null;
	}
}
