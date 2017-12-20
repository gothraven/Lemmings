package main.module.game.level.map;

public enum TileType {
	WALL(TileAction.NOTHING, false, false), BOX(TileAction.NOTHING, true, false), LAVA(TileAction.LAVA, false, true),
	ENTER(TileAction.NOTHING, false, false), EXIT(TileAction.EXIT, false, true), BOMB(TileAction.BOMB, true, true),
	MAGIC(TileAction.MAGIC, true, true), TELEPORT(TileAction.TELEPORT, false, true);

	private boolean beIn;
	private TileAction action;
	private boolean destructible;

	TileType (TileAction action, boolean destructible, boolean beIn) {
		this.action = action;
		this.destructible = destructible;
		this.beIn = beIn;
	}
	
	public TileAction getAction() {
		return action;
	}

	public boolean isDestructible() {
		return destructible;
	}

	public boolean canBeIn () {
		return beIn;
	}
}
