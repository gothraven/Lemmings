package main.view.level.status;

import main.module.game.level.LevelInfo;
import main.module.game.level.lemming.power.Power;
import main.util.geometry.Position;

import java.awt.*;

public class GStatus {

	private static final String playerName = "Player : ";
	private static final String playerScore = " , Score : ";
	private static final String selectedPower = "Selected Power : ";
	private static final String powersBank = "Possible Power Postions :\n";
	private static final String blocker = "- " + Power.BLOCKER.name() + ": (";
	private static final String bomber = "- " + Power.BOMBER.name() + ": (";
	private static final String builder = "- " + Power.BUILDER.name() + ": (";
	private static final String climber = "- " + Power.CLIMBER.name() + ": (";
	private static final String digger = "- " + Power.DIGGER.name() + ": (";
	private static final String miner = "- " + Power.MINER.name() + ": (";
	private static final String paratrooper = "- " + Power.PARATROOPER.name() + ": (";
	private static final String time = "Time left : ";
	private static final String lemsToSave = "save (";

	private static String newLine = System.getProperty("line.separator");
	private String status;
	private Position statusPos;

	public GStatus (Position statusPos) {
		this.statusPos = new Position(statusPos);
	}

	public void update (LevelInfo levelInfo) {
		if (levelInfo != null) {
			status = "";
			status += playerName + levelInfo.getPlayer().getName() + playerScore + levelInfo.getPlayer().getScore();
			status += newLine;
			status += selectedPower;
			if (levelInfo.getSelectedPower() != null)
				status += levelInfo.getSelectedPower().name();
			status += newLine;
			status += powersBank;
			status += newLine;
			status += blocker + levelInfo.getPowerUps().get(Power.BLOCKER) + ")";
			status += newLine;
			status += bomber + levelInfo.getPowerUps().get(Power.BOMBER) + ")";
			status += newLine;
			status += builder + levelInfo.getPowerUps().get(Power.BUILDER) + ")";
			status += newLine;
			status += climber + levelInfo.getPowerUps().get(Power.CLIMBER) + ")";
			status += newLine;
			status += digger + levelInfo.getPowerUps().get(Power.DIGGER) + ")";
			status += newLine;
			status += miner + levelInfo.getPowerUps().get(Power.MINER) + ")";
			status += newLine;
			status += paratrooper + levelInfo.getPowerUps().get(Power.PARATROOPER) + ")";
			status += newLine;
			status += time + levelInfo.getGameTime() + " sec(s)";
			status += newLine;
			if (levelInfo.isInPause())
				status += " [PAUSED]";
			status += lemsToSave + levelInfo.getNbLemToSave() + "/" + levelInfo.getNbLemTotal() + ")";
		}
	}

	public void draw (Graphics g) {
		int x = statusPos.getX();
		int y = statusPos.getY();
		g.setColor(Color.BLACK);
		if (status != null)
			for (String line : status.split(newLine))
				g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}
}
