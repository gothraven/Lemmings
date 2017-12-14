package main.geometry;

public class Position {

	private int x;
	private int y;

	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}

	public Position(Position pos) {
		this(pos.x, pos.y);
	}

	@Override
	public boolean equals (Object obj) {
		if (obj == null)
			return false;
		else if (obj.getClass() != getClass())
			return false;
		else
			return ((((Position)obj).x) == x && (((Position)obj).y == y));
	}

	public void nextPosition(Direction dir) {
		x = x + dir.getXdir();
		y = y + dir.getYdir();
	}

	public int getX () {
		return x;
	}

	public void setX (int x) {
		this.x = x;
	}

	public int getY () {
		return y;
	}

	public void setY (int y) {
		this.y = y;
	}
}
