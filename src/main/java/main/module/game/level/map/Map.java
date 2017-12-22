package main.module.game.level.map;

import main.util.exceptions.InvalideFileException;
import main.util.exceptions.TileAlreadyExistsException;
import main.util.geometry.Direction;
import main.util.geometry.Position;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;

public class Map {

	private HashMap<Position, Tile> map;
	private Position enterPos;
	private Position exitPos;

	public Map () {
		this.map = new HashMap<>();
	}

	public Map (File file) throws InvalideFileException {
		try {
			BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line;
			boolean isMap = false;
			int y = 0;
			while ((line = buff.readLine()) != null) {
				if (line.startsWith("map")) {
					isMap = true;
					this.map = new HashMap<>();
					this.enterPos = null;
					this.exitPos = null;
				} else if (isMap) {
					for (int x = 0; x < line.length(); x++) {
						switch (line.charAt(x)) {
							case '+': // BOX
								this.addTile(new Position(x, y), TileType.BOX);
								break;
							case '@': // EXIT
								if (this.exitPos != null)
									throw new InvalideFileException("there are more then one exit");
								else {
									this.addTile(new Position(x, y), TileType.EXIT);
									this.exitPos = new Position(x, y);
								}
								break;
							case 48: // ENTER
								if (this.enterPos != null)
									throw new InvalideFileException("there are more then one entrence");
								else {
									this.addTile(new Position(x, y), TileType.ENTER);
									this.enterPos = new Position(x, y);
								}
								break;
							case '-': //WALL
								this.addTile(new Position(x, y), TileType.WALL);
								break;
							case '#': //LAVA
								this.addTile(new Position(x, y), TileType.LAVA);
								break;
							case '%': //TELEPORT
								this.addTile(new Position(x, y), TileType.TELEPORT);
								break;
							case 'B': //BOMB
								this.addTile(new Position(x, y), TileType.BOMB);
								break;
							case '&': //MAGIC
								this.addTile(new Position(x, y), TileType.MAGIC);
						}
					}
					y++;
				}
			}
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalideFileException("File is not a map");
		}
	}

	public Map(Map map) {
		this.enterPos = new Position(map.getEnterPos());
		this.exitPos = new Position(map.getExitPos());
		this.map = new HashMap<>();
		this.map.putAll(map.getMap());
	}

	public Position teleport(Position p) {
		Set<Position> positions = this.map.keySet();
		for (Position position : positions) {
			if (!position.equals(p))
				if (map.get(position).getType() == TileType.TELEPORT)
					return position;
		}
		return null;
	}

	public Dimension getMaxAxes() {
		int xMax = 0;
		int yMax = 0;

		Set<Position> possitions = this.map.keySet();
		for (Position pos : possitions) {

			if (pos.getX() > xMax) {
				xMax = pos.getX();
			}

			if (pos.getY() > yMax) {
				yMax = pos.getY();
			}
		}

		return new Dimension(xMax + 1, yMax + 1);
	}


	public void addTile(Position p, TileType type) throws TileAlreadyExistsException
	{
		if (getTile(p) != null)
			throw new TileAlreadyExistsException("there is already a tile in x="+p.getX()+", y="+p.getY());
		else
			this.map.put(p, new Tile(type, p));
	}


	public void removeTile(Position p)
	{
		Set<Position> positions = this.map.keySet();
		Position toRemove = null;
		for (Position position : positions) {
			if (position.equals(p)) {
				toRemove = position;
				break;
			}
		}
		if (toRemove != null)
			this.map.remove(toRemove);
	}

	public Tile getTileInThisDirection(Position p, Direction d) {
		return getTile(new Position(p.getX()+d.getXdir(),p.getY()+d.getYdir()));
	}

	public Tile getTile(Position p)
	{
		Set<Position> positions = this.map.keySet();
		for (Position position : positions) {
			if (position.equals(p))
				return this.map.get(position);
		}
		return null;
	}

	public Position getEnterPos() {
		return enterPos;
	}

	public Position getExitPos() {
		return exitPos;
	}

	public HashMap<Position, Tile> getMap() {
		return map;
	}

	public void setEnterPos (Position enterPos) {
		this.enterPos = enterPos;
	}

	public void setExitPos (Position exitPos) {
		this.exitPos = exitPos;
	}

	public void setMap (HashMap<Position, Tile> map) {
		this.map = map;
	}

}
