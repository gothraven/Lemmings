package main.module.game.level.lemming.power;

import main.module.game.level.lemming.Lemming;
import main.module.game.level.map.Map;
import main.module.game.level.map.Tile;
import main.module.game.level.map.TileAction;
import main.module.game.level.map.TileType;
import main.util.exceptions.TileAlreadyExistsException;
import main.util.geometry.Direction;
import main.util.geometry.Position;
import main.util.power.PowerRules;

import java.util.ArrayList;

public enum Power implements PowerRules {
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
			if (lem.jump(map,lems))
				return;
			lem.oppositDirection();
		}
	},
	BOMBER {
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			lem.setCountStepAfterChangePower();
			if (lem.getPowerTimeCounter() >= 4) {
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
			if (lem.jump(map,lems))
				return;
			lem.oppositDirection();
		}
		private  void explode (Lemming lem , Map map ,ArrayList<Lemming> lems){
			TileAction.BOMB.action(lem, map, lems);
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
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			try {
				if (lem.build(map)){
					lem.setCountStepAfterChangePower();

				}else {
					lem.changePower(Power.WALKER);
				}
			} catch (TileAlreadyExistsException e) {
				e.printStackTrace();
			}
			if (lem.getPowerTimeCounter() >= 5) {
				lem.changePower(Power.WALKER);
				return;
			}
			lem.jump(map,lems);

		}
	},
	CLIMBER {
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			Tile tile = map.getTile(lem.getPos());
			if (tile != null && tile.getType() !=  TileType.ENTER) {
				tile.action(lem, map, lems);
				return;
			}
			if (lem.getPowerTimeCounter() == 0)
				if (lem.fall(map))
					return;
			if (lem.walk(map, lems)){
				lem.resetCountStepAfterChangePower();
				return;
			}
			if (lem.jump(map,lems))
				return;
			if(lem.climb(map)) {
				lem.setCountStepAfterChangePower();
				return;
			}
			lem.resetCountStepAfterChangePower();
			lem.oppositDirection();
		}
	},
	DIGGER {
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			if (lem.getPowerTimeCounter() == 0) {
				Tile tile = map.getTile(lem.getPos());
				if (tile != null && tile.getType() !=  TileType.ENTER) {
					tile.action(lem, map, lems);
					return;
				}
				if (lem.fall(map))
					return;
				Tile t = map.getTile(new Position(Direction.DOWN.WhatIsNextPosition(lem.getPos())));

				if (t.getType().isDestructible()){
					lem.setCountStepAfterChangePower();
					map.removeTile(t.getPosition());
					lem.setPos(t.getPosition());
					return;
				}
				if (lem.walk(map, lems))
					return;
				if (lem.jump(map,lems))
					return;
				lem.oppositDirection();
			}else{
				Tile t = map.getTile(new Position(Direction.DOWN.WhatIsNextPosition(lem.getPos())));
				if(t!=null) {
					if (t.getType().isDestructible()) {
						lem.setCountStepAfterChangePower();
						map.removeTile(t.getPosition());
						lem.setPos(t.getPosition());
					} else {
						lem.changePower(Power.WALKER);
					}
				}else{
					lem.changePower(Power.WALKER);
				}
				if (lem.getPowerTimeCounter() >= 5)
					lem.changePower(Power.WALKER);
			}

		}
	},
	MINER {
		private boolean Miner(Lemming lem , Map map){
			Tile t = map.getTile(new Position(lem.getDir().WhatIsNextPosition(lem.getPos())));

			if (t!= null){
				if (t.getType().isDestructible()){
					map.removeTile(t.getPosition());
					lem.setPos(t.getPosition());
					lem.setCountStepAfterChangePower();
				}else{
					return false;
				}
				return true;
			}
			return false;
		}
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			if (lem.getPowerTimeCounter() == 0) {
				Tile tile = map.getTile(lem.getPos());
				if (tile != null && tile.getType() !=  TileType.ENTER) {
					tile.action(lem, map, lems);
					return;
				}
				if (lem.fall(map))
					return;
				if (lem.walk(map, lems))
					return;
				if(Miner(lem, map))
					return;
				if (lem.jump(map,lems))
					return;
				lem.oppositDirection();
			}else{
				if (Miner(lem, map))
					return;
				lem.changePower(Power.WALKER);
			}
		}
	},
	PARATROOPER {
		private boolean fly(Map map ,Lemming lem) {
			Tile t = map.getTile(new Position(Direction.DOWN.WhatIsNextPosition(lem.getPos())));
			if (t == null || t.getType().canBeIn()) {
				if ((lem.getPowerTimeCounter() % 2) == 0) {
					lem.fly(map);
					lem.setCountStepAfterChangePower();
				} else {
					lem.resetCountStepAfterChangePower();
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
			if (lem.jump(map,lems))
				return;
			lem.oppositDirection();
		}
	}

}