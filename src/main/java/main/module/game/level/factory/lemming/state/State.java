package main.module.game.level.factory.lemming.state;

import main.module.game.level.factory.lemming.Lemming;
import main.module.game.level.factory.lemming.power.PowerRules;

public enum State implements PowerRules {
	WALKER {
		public void action (Lemming lem) {
			System.out.println("im walking");
			//TODO implement the walker
		}
	},
	BOMBER {
		public void action (Lemming lem) {
			System.out.println("im bombing");
			//TODO implement the bomber
		}
	},
	BLOCKER {
		public void action (Lemming lem) {
			System.out.println("im blocking");
		}
	},
	BUILDER {
		public void action (Lemming lem) {
			System.out.println("im building");
		}
	},
	CLIMBER {
		public void action (Lemming lem) {
			System.out.println("im climbing");
		}
	},
	DIGGER {
		public void action (Lemming lem) {
			System.out.println("im digging");
		}
	},
	MINER {
		public void action (Lemming lem) {
			System.out.println("im minning");
		}
	},
	PARATROOPER {
		public void action (Lemming lem) {
			System.out.println("im flying");
		}
	}
}