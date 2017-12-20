package main.module.game.level.lemming.state;

import main.module.game.level.lemming.Lemming;
import main.module.game.level.map.Map;
import main.module.game.level.map.Tile;
import main.util.power.PowerRules;

import java.util.ArrayList;

public enum State implements PowerRules {

	WALKER {
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			Tile tile = map.getTile(lem.getPos());
			if (tile != null)
				tile.action(lem, map, lems);
			if (lem.fall(map))
				return;
			if (lem.walk(map, lems))
				return;
			if (lem.jump(map))
				return;
			lem.oppositDirection();
		}
	},
	BOMBER {
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {

		}
	},
	BLOCKER {
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			Tile tile = map.getTile(lem.getPos());
			if (tile != null)
				tile.action(lem, map, lems);
			lem.fall(map);
		}
	},
	BUILDER {
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {

		}
	},
	CLIMBER {
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {

		}
	},
	DIGGER {
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {

		}
	},
	MINER {
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {

		}
	},
	PARATROOPER {
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {

		}
	}

}