package main.module.game.level.lemming.state;

import main.module.game.level.lemming.Lemming;
import main.module.game.level.map.Map;
import main.module.game.level.map.Tile;
import main.module.game.level.map.TileType;
import main.util.exceptions.TileAlreadyExistsException;
import main.util.geometry.Direction;
import main.util.geometry.Position;
import main.util.power.PowerRules;

import java.util.ArrayList;

public enum State implements PowerRules {

	WALKER {
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			Tile tile = map.getTile(lem.getPos());
			if (tile != null && tile.getType() !=  TileType.ENTER) {
				tile.action(lem, map, lems);
				return;
			}
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
		private int Step = 0;
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			Step++;
			if (Step >=4) {
				explode(lem,map,lems);
			}
			Tile tile = map.getTile(lem.getPos());
			if (tile != null && tile.getType() !=  TileType.ENTER) {
				tile.action(lem, map, lems);
				return;
			}
			if (lem.fall(map))
				return;
			if (lem.walk(map, lems))
				return;
			if (lem.jump(map))
				return;
			lem.oppositDirection();
		}

		/*
		* void explode(lem, map, lems) -> explode
		* */
		private  void explode (Lemming lem , Map map ,ArrayList<Lemming> lems){

			//on peut faire aussi  State.BOMBER.action(lem,map,lems);

			int x = lem.getPos().getX(), y = lem.getPos().getY();
			ArrayList<Position> positions = new ArrayList<>();
			positions.add(new Position(x - 1, y - 1));
			positions.add(new Position(x, y - 1));
			positions.add(new Position(x + 1, y - 1));
			positions.add(new Position(x - 1, y));
			positions.add(new Position(x + 1, y));
			positions.add(new Position(x - 1, y + 1));
			positions.add(new Position(x, y + 1));
			positions.add(new Position(x + 1, y + 1));

			for (Position p : positions) {
				Tile t = map.getTile(p);
				if (t != null)
					if (t.getType().isDestructible())
						map.removeTile(p);

			}

			map.removeTile(lem.getPos());

			for (Position p : positions) {
				for (Lemming l : lems) {
					if (l.getPos().equals(p))
						l.kill();
				}
			}
			lem.kill();
		}
	},
	BLOCKER {
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			Tile tile = map.getTile(lem.getPos());
			if (tile != null && tile.getType() !=  TileType.ENTER) {
				tile.action(lem, map, lems);
				return;
			}
			lem.fall(map);
		}
	},
	BUILDER {
		private  int step = 0;
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			try {
				if (lem.build(map)){
					step ++;
					return;
				}else {
					lem.changePower(State.WALKER);
				}
			} catch (TileAlreadyExistsException e) {
				e.printStackTrace();
			}
			if (step >=6)
				lem.changePower(State.WALKER);
			if (lem.jump(map))
			return;
		}
	},
	CLIMBER {
		private boolean imClimbing = false;
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			System.out.println(lem);
			Tile tile = map.getTile(lem.getPos());
			if (tile != null && tile.getType() !=  TileType.ENTER) {
				tile.action(lem, map, lems);
				return;
			}
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
		private int step=0,allStep =0;
		private boolean startDigger = false;
		private boolean digger(Map map ,Lemming lem){
			Tile t = map.getTile(new Position(Direction.DOWN.WhatIsNextPosition(lem.getPos())));

			if (t.getType().isDestructible()){
				startDigger = true;
				map.removeTile(new Position(Direction.DOWN.WhatIsNextPosition(lem.getPos())));
				step++;
				return true;
			}
			if (step >=6)
				lem.changePower(State.WALKER);
			return false;
		}

		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			if (startDigger)
				allStep++;
			Tile tile = map.getTile(lem.getPos());
			if (tile != null && tile.getType() !=  TileType.ENTER) {
				tile.action(lem, map, lems);
				return;
			}
			if (lem.fall(map))
				return;
			if (allStep-step>=1)
				lem.changePower(State.WALKER);
			if( digger(map,lem))
				return;
			if (lem.walk(map, lems))
				return;
			if (lem.jump(map))
				return;
			lem.oppositDirection();
		}
	},
	MINER {
		private boolean startMiner = false;
		private void shouldStartMiner(Map map ,Lemming lem){
			Tile t = map.getTile(new Position(lem.getDir().WhatIsNextPosition(lem.getPos())));
			if (t.getType().isDestructible()){
				startMiner = true;
			}
		}
		private void miner(Map map ,Lemming lem){
			Tile t = map.getTile(new Position(lem.getDir().WhatIsNextPosition(lem.getPos())));
			if (t.getType().isDestructible()){
				map.removeTile(new Position(lem.getDir().WhatIsNextPosition(lem.getPos())));
			}else{
				lem.changePower(State.WALKER);
			}
		}
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {

			Tile tile = map.getTile(lem.getPos());
			if (tile != null && tile.getType() !=  TileType.ENTER) {
				tile.action(lem, map, lems);
				return;
			}
			shouldStartMiner(map,lem);
			if (lem.fall(map)){
				if (startMiner)
					lem.changePower(State.WALKER);
				return;
			}
			if (startMiner)
				miner(map,lem);
			if (lem.walk(map, lems))
				return;
			if (lem.jump(map))
				return;
			lem.oppositDirection();
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
			if (tile != null && tile.getType() !=  TileType.ENTER) {
				tile.action(lem, map, lems);
				return;
			}
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