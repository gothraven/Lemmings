package main.module.game.level.factory.lemming.state;

import main.module.game.level.factory.lemming.Lemming;
import main.module.game.level.factory.map.Tile;
import main.module.game.level.factory.map.TileType;
import main.util.geometry.Direction;
import main.util.geometry.Position;
import main.util.power.PowerRules;
import main.module.game.level.factory.map.Map;

public enum 	State implements PowerRules {

	WALKER {
		public void action (Lemming lem, Map map) {
			if(shouldFall(lem.getPos(),map) && lem.getPower() == State.WALKER){
				lem.fall();
				lem.fallingCounter();
			}else{
				if (shouldMove(lem,map)) {
					lem.move(map);
				}else {
					if(shouldJump(lem,map))
						lem.jump();
					else
						lem.oppositDirection();
				}
				lem.restFallingCounter();
			}

			if (lem.getFallingCounter() >= Lemming.MAX_HEIGHT)
				lem.kill();
			if(amIsomewhere(lem,map))
				iAmSomewhere(lem,map);


		}
	},
	BOMBER {
		public void action (Lemming lem, Map map) {
			System.out.println("im bombing");
			//TODO implement the bomber
		}
	},
	BLOCKER {
		public void action (Lemming lem, Map map) {
			if (shouldFall(lem.getPos() ,map)) {
				lem.fall();
				lem.fallingCounter();
			}else{
				lem.restFallingCounter();
			}
			if (lem.getFallingCounter() >= Lemming.MAX_HEIGHT)
				lem.kill();
			if(amIsomewhere(lem,map))
				iAmSomewhere(lem,map);
		}
	},
	BUILDER {
		public void action (Lemming lem, Map map) {
			System.out.println("im building");
		}
	},
	CLIMBER {
		public void action (Lemming lem, Map map) {
			Tile tDirection = map.getTileInThisDirection(lem.getPos(), lem.getDir());
			if(tDirection == null){
				WALKER.action(lem,map);
			}else{

				lem.moveUp();
			}

		}
	},
	DIGGER {
		public void action (Lemming lem, Map map) {

			Tile TDown = map.getTileInThisDirection(lem.getPos(), Direction.DOWN);
			if(TDown.getType().isDestructible()){

			}else{
				State.WALKER.action(lem,map);
			}
			if (countDigger == 6) {
				lem.changePower(State.WALKER);
				countDigger = 0;
			}
		}
	},
	MINER {
		public void action (Lemming lem, Map map) {
			if(shouldMove(lem , map) || shouldFall(lem.getPos(),map)){
				State.WALKER.action(lem,map);
			}else{
				Tile tDirection = map.getTileInThisDirection(lem.getPos(), lem.getDir());
				if(tDirection.getType().isDestructible()){
					map.removeTile(new Position(lem.getPos().getX()+lem.getDir().getXdir(),lem.getPos().getY()+lem.getDir().getYdir()));
				}else {
					lem.oppositDirection();
					lem.changePower(State.WALKER);
				}
			}
		}
	},
	PARATROOPER {
		public void action (Lemming lem, Map map) {

			if(shouldFall(lem.getPos(),map)){
				if (isFlying){
					lem.fall();
					lem.fallingCounter();
					isFlying = false;
				}else{
					isFlying = true;
				}

			}else{
				if (shouldMove(lem,map)) {
					lem.move(map);
				}else {
					if(shouldJump(lem,map))
						lem.jump();
					else
						lem.oppositDirection();
				}
				lem.restFallingCounter();
			}

			if (lem.getFallingCounter() >= Lemming.MAX_HEIGHT)
				lem.kill();
			if(amIsomewhere(lem,map))
				iAmSomewhere(lem,map);
		}
	};




	boolean isFlying = false;
	int countDigger = 0;







	protected boolean shouldFall(Position pos, Map map){
		return map.getTile(new Position(pos.getX() + Direction.DOWN.getXdir(), pos.getY() + Direction.DOWN.getYdir())) == null;

	}
	protected boolean shouldMove(Lemming lem, Map map){

		Tile tDirection = map.getTileInThisDirection(lem.getPos(), lem.getDir());
		return tDirection == null || tDirection.getType() == TileType.ENTER || tDirection.getType() == TileType.BOMB || tDirection.getType() == TileType.EXIT || tDirection.getType() == TileType.LAVA || tDirection.getType() == TileType.TELEPORT;
	}
	protected boolean shouldJump(Lemming lem, Map map){
		Tile tUp = map.getTileInThisDirection(lem.getPos(), Direction.UP);
		Tile tDUpper = map.getTileInThisDirection(lem.getPos(), lem.getDir().upper());
		return tUp == null & (tDUpper == null || tDUpper.getType() == TileType.BOMB || tDUpper.getType() == TileType.EXIT || tDUpper.getType() == TileType.TELEPORT || tDUpper.getType() == TileType.LAVA || tDUpper.getType() == TileType.ENTER);
	}
	protected boolean amIsomewhere(Lemming lem , Map map){
		Tile tMe = map.getTile(lem.getPos());
		return tMe != null;
	}
	protected void iAmSomewhere(Lemming lem, Map map){
		Tile tMe = map.getTile(lem.getPos());
		if(tMe.getType() == TileType.EXIT)
			lem.save();
		else if(tMe.getType() == TileType.LAVA)
			lem.kill();
		else if(tMe.getType() == TileType.TELEPORT)
			//TODO add function to Teleport
			System.out.println("TELEPORT");
		else if (tMe.getType() == TileType.BOMB)
			//TODO add function explose
			System.out.println("BOOM!!!");
	}

}