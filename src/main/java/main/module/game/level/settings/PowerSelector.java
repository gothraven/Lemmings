package main.module.game.level.settings;

import main.module.game.level.lemming.power.Power;

public class PowerSelector {
	private static final PowerPerKey[] powerKeys = PowerPerKey.values();

	public static boolean isAPowerKey (char keyCode) {
		for (PowerPerKey powerPerKey : powerKeys)
			if (powerPerKey.name().toLowerCase().charAt(0) == keyCode)
				return true;
		return false;
	}

	public static Power selectPower (char keyCode) {
		Power selectedPower;

		selectedPower = PowerPerKey.valueOf(Character.toUpperCase(keyCode) + "").getPower();
		if (selectedPower == null)
			return Power.WALKER;
		return selectedPower;
	}
}
