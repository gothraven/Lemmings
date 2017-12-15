package main.module.game.level;

import main.module.game.level.factory.lemming.powertype.PowerType;
import main.util.exceptions.InvalideFileException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class LevelInfo {
	public static int SPEED_SCALE = 1000, LEM_SHOW_SPEED_MAX = 1000, LEM_SHOW_SPEED_MIN = 1;
	public static int GAME_SPEED_MIN = 1, GAME_SPEED_MAX = 10;

	private int nbLemTotal, nbLemDead, nbLemSaved, nbLemToSave, nbLemInGame;
	private int limShowSpeed, gameSpeed;

	private int gameTime, timeToSave;

	private boolean won;

	private boolean enPause;

	private Map<PowerType, Integer> powerUps;

	public LevelInfo(InputStreamReader isr) throws InvalideFileException
	{
		this.powerUps = new TreeMap<PowerType, Integer>();
		this.enPause = false;
		this.won = false;
		this.nbLemTotal = 0;
		this.nbLemDead = 0;
		this.nbLemSaved = 0;
		this.nbLemToSave = 0;
		this.nbLemInGame = 0;
		this.gameTime = 0;
		this.timeToSave = 0;
		this.limShowSpeed = LEM_SHOW_SPEED_MIN;
		this.gameSpeed = GAME_SPEED_MIN;
		this.load_info(isr);
	}

	private void load_info(InputStreamReader isr) throws InvalideFileException
	{
		try {
			BufferedReader buff = new BufferedReader(isr);
			String line = buff.readLine();
			while (((line != null) && !line.startsWith("map"))) {
				String[] lineParts = line.split("\\t| ");
				switch (lineParts[0].toLowerCase()) {
					case "lemmingspeed":
						this.limShowSpeed = Integer.parseInt(lineParts[1]);
						break;
					case "gamespeed":
						this.gameSpeed = Integer.parseInt(lineParts[1]);
						break;
					case "time":
						this.gameTime = Integer.parseInt(lineParts[1]);
						break;
					case "number":
						this.nbLemTotal = Integer.parseInt(lineParts[1]);
						break;
					case "tosave":
						this.nbLemToSave = Integer.parseInt(lineParts[1]);
						break;
					case "climber":
						this.powerUps.put(PowerType.CLIMBER, Integer.parseInt(lineParts[1]));
						break;
					case "paratrooper":
						this.powerUps.put(PowerType.PARATROOPER, Integer.parseInt(lineParts[1]));
						break;
					case "blocker":
						this.powerUps.put(PowerType.BLOCKER, Integer.parseInt(lineParts[1]));
						break;
					case "bomber":
						this.powerUps.put(PowerType.BOMBER, Integer.parseInt(lineParts[1]));
						break;
					case "builder":
						this.powerUps.put(PowerType.BUILDER, Integer.parseInt(lineParts[1]));
						break;
					case "digger":
						this.powerUps.put(PowerType.DIGGER, Integer.parseInt(lineParts[1]));
						break;
					case "miner":
						this.powerUps.put(PowerType.MINER, Integer.parseInt(lineParts[1]));
						break;
				}
				line = buff.readLine();
			}
			buff.close();
			if (!verify_data())
				throw new InvalideFileException("File data are bad");
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalideFileException("File is corrupted");
		}
	}

	private boolean verify_data() {
		/*
		 * TODO find all the conditions that shouldn't exist in a level file
		 */
		return (true);
	}

	public int getNbLemTotal() {
		return nbLemTotal;
	}

	public void setNbLemTotal(int nbLemTotal) {
		this.nbLemTotal = nbLemTotal;
	}

	public int getNbLemDead() {
		return nbLemDead;
	}

	public void setNbLemDead(int nbLemDead) {
		this.nbLemDead = nbLemDead;
	}

	public int getNbLemSaved() {
		return nbLemSaved;
	}

	public void setNbLemSaved(int nbLemSaved) {
		this.nbLemSaved = nbLemSaved;
	}

	public int getNbLemToSave() {
		return nbLemToSave;
	}

	public void setNbLemToSave(int nbLemToSave) {
		this.nbLemToSave = nbLemToSave;
	}

	public int getNbLemInGame() {
		return nbLemInGame;
	}

	public void setNbLemInGame(int nbLemInGame) {
		this.nbLemInGame = nbLemInGame;
	}

	public int getLimShowSpeed() {
		return limShowSpeed;
	}

	public void setLimShowSpeed(int limShowSpeed) {
		if (limShowSpeed <= LEM_SHOW_SPEED_MIN)
			this.limShowSpeed = LEM_SHOW_SPEED_MIN;
		else if (limShowSpeed >= LEM_SHOW_SPEED_MAX)
			this.limShowSpeed = LEM_SHOW_SPEED_MAX;
		else
			this.limShowSpeed = limShowSpeed;
	}

	public int getGameSpeed() {
		return gameSpeed;
	}

	public void setGameSpeed(int gameSpeed) {
		if(gameSpeed < GAME_SPEED_MIN)
			this.gameSpeed = GAME_SPEED_MIN;
		else if (gameSpeed > GAME_SPEED_MAX)
			this.gameSpeed = GAME_SPEED_MAX;
		else
			this.gameSpeed = gameSpeed;
	}

	public int getTimeToSave() {
		return timeToSave;
	}

	public void setTimeToSave(int timeToSave) {
		this.timeToSave = timeToSave;
	}

	public boolean isEnPause() {
		return enPause;
	}

	public void setEnPause(boolean enPause) {
		this.enPause = enPause;
	}

	public Map<PowerType, Integer> getPowerUps() {
		return powerUps;
	}

	public void setPowerUps(Map<PowerType, Integer> powerUps) {
		this.powerUps = powerUps;
	}

	public int getGameTime() {
		return gameTime;
	}

	public void setGameTime(int gameTime) {
		this.gameTime = gameTime;
	}

	public void addNezLemInGame () {
		this.nbLemInGame += 1;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	@Override
	public String toString() {
		String result = "";
		result += "dead: " + this.nbLemDead + "/" + this.nbLemTotal + "  ";
		result += "saved: " + this.nbLemSaved + "/" + this.nbLemToSave + "  ";
		result += "time: " + this.gameTime +" sec";
		return result;
	}

}
