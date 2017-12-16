package main.module.game.level.factory.map;

public enum TileType {
	WALL(TileAction.NOTHING, false), BOX(TileAction.NOTHING, true), LAVA(TileAction.LAVA, false),
	ENTER(TileAction.NOTHING, false), EXIT(TileAction.EXIT, false), BOMB(TileAction.BOMB, true),
	MAGIC(TileAction.MAGIC, true), TELEPORT(TileAction.TELEPORT, false), BLOCKER(TileAction.BLOCKER, false);

	private TileAction action;
	private boolean destructible;

	TileType(TileAction action, boolean destructible) {
		this.action = action;
		this.destructible = destructible;
	}
	
	public TileAction getAction() {
		return action;
	}

	public boolean isDestructible() {
		return destructible;
	}
}
