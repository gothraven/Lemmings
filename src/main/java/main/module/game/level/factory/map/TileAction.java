package main.module.game.level.factory.map;

import main.module.game.level.factory.lemming.Lemming;
import main.module.game.level.factory.lemming.power.PowerRules;

public enum TileAction implements PowerRules {
	TELEPORT{
		@Override
		public void action (Lemming lem, Map map) {

		}
	}
}
