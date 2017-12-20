package main.util.geometry;

public enum Direction {
	UP(0, - 1), DOWN(0, 1), LEFT(- 1, 0), RIGHT(1, 0),
	UPPERLEFT(LEFT.xdir,UP.ydir), UPPERRIGHT(RIGHT.xdir, UP.ydir),
	DOWNERLEFT(LEFT.xdir, DOWN.ydir), DOWNERRIGHT(RIGHT.xdir, DOWN.ydir);
	int xdir;
	int ydir;

	Direction(int xdir, int ydir) {
		this.xdir = xdir;
		this.ydir = ydir;
	}

	public int getXdir () {
		return xdir;
	}

	public int getYdir () {
		return ydir;
	}

	public Position WhatIsNextPosition(Position pos) {
		return new Position(pos.getX() + xdir, pos.getY() + ydir);
	}

	public Direction upper() {
		if (this.equals(Direction.RIGHT))
			return Direction.UPPERRIGHT;
		if (this.equals(Direction.LEFT))
			return Direction.UPPERLEFT;
		else
			return null;
	}

	public Direction oppositDirection(Direction dir) {
		if (dir.equals(RIGHT))
			return Direction.LEFT;
		else if (dir.equals(LEFT))
			return Direction.RIGHT;
		else if (dir.equals(UP))
			return Direction.DOWN;
		else if (dir.equals(DOWN))
			return Direction.UP;
		else if (dir.equals(UPPERLEFT))
			return Direction.DOWNERRIGHT;
		else if (dir.equals(DOWNERLEFT))
			return Direction.UPPERRIGHT;
		else if (dir.equals(UPPERRIGHT))
			return Direction.DOWNERLEFT;
		else
			return Direction.UPPERLEFT;
	}
}
