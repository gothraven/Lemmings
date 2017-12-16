package main.module.game.level.factory;

import main.module.game.level.factory.map.Map;
import main.util.exceptions.InvalideFileException;

import java.io.File;

public class MapFactory {

	public static Map createMap(File file) {
		//TODO make sur to handle everything here from and just give a good map at the end
		//TODO even if nothing works we need a map to be used

		try {
			return new Map(file);
		} catch (InvalideFileException e) {
			e.printStackTrace();
		}
		return null;
	}
}
