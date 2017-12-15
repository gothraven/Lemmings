package main.util.geometry;

public enum Direction {
	NULL(0, 0), UP(0, -1),DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0),
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

}
