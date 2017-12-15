package main.module.game.level.factory.map;

import main.module.game.level.factory.lemming.Lemming;
import main.module.game.level.factory.lemming.power.PowerRules;

public enum TileAction implements PowerRules {
	TELEPORT{
		@Override
		public void action (Lemming lem, Map map) {
			//TODO implement the function
		}
	},
	LAVA {
		@Override
		public void action (Lemming lem, Map map) {
			//TODO implement the function

		}
	},
	MAGIC {
		@Override
		public void action (Lemming lem, Map map) {
			//TODO implement the function

		}
	},
	EXIT {
		@Override
		public void action (Lemming lem, Map map) {
			//TODO implement the function

		}
	},
	BOMB {
		@Override
		public void action (Lemming lem, Map map) {
			//TODO implement the function

		}
	},
	BLOCKER {
		@Override
		public void action (Lemming lem, Map map) {
			//TODO implement the function

		}
	},
	NOTHING {
		@Override
		public void action (Lemming lem, Map map) {
			//this does nothing
		}
	}
}
