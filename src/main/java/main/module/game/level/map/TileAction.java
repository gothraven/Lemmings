package main.module.game.level.map;

import main.module.game.level.lemming.Lemming;
import main.util.exceptions.TileAlreadyExistsException;
import main.util.geometry.Direction;
import main.util.geometry.Position;
import main.util.power.PowerRules;

import java.util.ArrayList;

public enum TileAction implements PowerRules {
	TELEPORT{
		@Override
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			Position nextPos;
			if (lem.inGame())
				if ((nextPos = map.teleport(lem.getPos())) != null)
					lem.setPos(nextPos);
		}
	},
	LAVA {
		@Override
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			if (lem.inGame())
				lem.kill();
		}
	},
	MAGIC {
		@Override
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			try {
				map.addTile(Direction.UP.WhatIsNextPosition(map.getExitPos()),TileType.BOX);
				map.addTile(Direction.DOWN.WhatIsNextPosition(map.getExitPos()),TileType.BOX);
				map.addTile(Direction.RIGHT.WhatIsNextPosition(map.getExitPos()),TileType.BOX);
				map.addTile(Direction.LEFT.WhatIsNextPosition(map.getExitPos()),TileType.BOX);
			} catch (TileAlreadyExistsException e) {
				e.printStackTrace();
			}
		}
	},
	EXIT {
		@Override
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			if (lem.inGame())
				lem.save();
		}
	},
	BOMB {
		@Override
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
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
	NOTHING {
		@Override
		public void action (Lemming lem, Map map, ArrayList<Lemming> lems) {
			//this does nothing
		}
	}
}
