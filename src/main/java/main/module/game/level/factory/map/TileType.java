package main.module.game.level.factory.map;

public enum TileType {
	WALL(null, false), BOX(null, true), LAVA(null, false),
	ENTER(null, false), EXIT(null, false), BOMB(null, true),
	MAGIC(null, true), TELEPORT(null, false);

	private Object color;
	private boolean destructible;

	TileType(Object action, boolean destructible) {
		this.color = action;
		this.destructible = destructible;
	}

	public Object getAction() {
		return color;
	}

	public boolean isDestructible() {
		return destructible;
	}
}
