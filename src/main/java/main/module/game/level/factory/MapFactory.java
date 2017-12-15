package main.module.game.level.factory;

import main.module.game.level.factory.map.Map;
import main.util.exceptions.InvalideFileException;

import java.io.InputStreamReader;

public class MapFactory {

	public static Map createMap(InputStreamReader isr) {
		//TODO make sur to handle everything here from and just give a good map at the end
		//TODO even if nothing works we need a map to be used

		try {
			return new Map(isr);
		} catch (InvalideFileException e) {
			e.printStackTrace();
		}
		return null;
	}
}
