package main.view.level.status;

import main.module.game.level.lemming.state.Power;

public class GStatus {

	private static String selectedPower = "Selected Power :";
	private static String powersBank = "Possible Power Postions :\n";
	private static String blocker = "- " + Power.BLOCKER.name() + ": (";
	private static String bomber = "- " + Power.BOMBER.name() + ": (";
	private static String builder = "- " + Power.BUILDER.name() + ": (";
	private static String climber = "- " + Power.CLIMBER.name() + ": (";
	private static String digger = "- " + Power.DIGGER.name() + ": (";
	private static String miner = "- " + Power.MINER.name() + ": (";
	private static String paratrooper = "- " + Power.PARATROOPER.name() + ": (";
	private static String time = "Time left : ";
}
