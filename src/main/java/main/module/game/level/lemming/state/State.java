package main.module.game.level.lemming.state;

import main.module.game.level.lemming.Lemming;
import main.module.game.level.map.Map;
import main.module.game.level.map.Tile;
import main.util.geometry.Direction;
import main.util.geometry.Position;
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
		private boolean imClimbing = false;
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			System.out.println(lem);
			Tile tile = map.getTile(lem.getPos());
			if (tile != null)
				tile.action(lem, map, lems);
			if (!imClimbing)
				if (lem.fall(map))
					return;
			if (lem.walk(map, lems)){
				imClimbing = false;
				return;
			}
			if (lem.jump(map))
				return;
			if(lem.climb(map)) {
				imClimbing = true;
				return;
			}
			lem.oppositDirection();
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
		private boolean imFlying = false;
		private boolean fly(Map map ,Lemming lem) {
			Tile t = map.getTile(new Position(Direction.DOWN.WhatIsNextPosition(lem.getPos())));
			if (t == null || t.getType().canBeIn()) {
				if (imFlying) {
					lem.fly(map);
					imFlying = false;
				} else {
					imFlying = true;
				}
				return true;
			}else{
				return false;
			}
		}
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			Tile tile = map.getTile(lem.getPos());
			if (tile != null)
				tile.action(lem, map, lems);
			if (fly(map,lem))
				return;
			if (lem.walk(map, lems))
				return;
			if (lem.jump(map))
				return;
			lem.oppositDirection();
		}
	}

}