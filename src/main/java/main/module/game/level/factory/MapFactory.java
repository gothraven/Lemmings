package main.module.game.level.factory;

import main.module.game.level.factory.map.Map;

import java.io.InputStreamReader;

public class MapFactory {

	public static Map createMap(InputStreamReader isr) {
		//TODO make sur to handle everything here from and just give a good map at the end
		//TODO even if nothing works we need a map to be used

		return new Map(isr);
	}
}
