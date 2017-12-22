package main.module.game.level;

import main.module.game.level.lemming.power.Power;
import main.module.game.player.Player;
import main.util.exceptions.InvalideFileException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class LevelInfo {

	public static int SPEED_SCALE = 1000, LEM_SHOW_SPEED_MAX = 1000, LEM_SHOW_SPEED_MIN = 1;

	private static int GAME_SPEED_MIN = 1, GAME_SPEED_MAX = 10;

	private int nbLemTotal, nbLemDead, nbLemSaved, nbLemToSave, nbLemInGame;

	private int limShowSpeed, gameSpeed;

	private int gameTime, timeToSave;

	private boolean won;

	private boolean inPause;

	private Map<Power, Integer> powerUps;

	private Power selectedPower;

	private Player player;

	LevelInfo (File file) throws InvalideFileException
	{
		this.powerUps = new TreeMap<>();
		this.inPause = false;
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
		this.selectedPower = null;
		this.load_info(file);
	}

	private void load_info(File file) throws InvalideFileException
	{
		try {
			BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = buff.readLine();
			while (((line != null) && !line.startsWith("map"))) {
				String[] lineParts = line.split("[\\t ]");
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
						this.powerUps.put(Power.CLIMBER, Integer.parseInt(lineParts[1]));
						break;
					case "paratrooper":
						this.powerUps.put(Power.PARATROOPER, Integer.parseInt(lineParts[1]));
						break;
					case "blocker":
						this.powerUps.put(Power.BLOCKER, Integer.parseInt(lineParts[1]));
						break;
					case "bomber":
						this.powerUps.put(Power.BOMBER, Integer.parseInt(lineParts[1]));
						break;
					case "builder":
						this.powerUps.put(Power.BUILDER, Integer.parseInt(lineParts[1]));
						break;
					case "digger":
						this.powerUps.put(Power.DIGGER, Integer.parseInt(lineParts[1]));
						break;
					case "miner":
						this.powerUps.put(Power.MINER, Integer.parseInt(lineParts[1]));
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
		return gameTime > 0 && nbLemTotal > 0 && nbLemSaved <= nbLemTotal;
	}

	public int getNbLemTotal() {
		return nbLemTotal;
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

	public boolean isInPause () {
		return inPause;
	}

	public void setInPause (boolean inPause) {
		this.inPause = inPause;
	}

	public Map<Power, Integer> getPowerUps () {
		return powerUps;
	}

	public int getGameTime() {
		return gameTime;
	}

	public void setGameTime(int gameTime) {
		this.gameTime = gameTime;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public void usedPowerUp (Power power) {
		int currectNumber = this.powerUps.get(power);
		this.powerUps.replace(power, currectNumber - 1);
	}

	public Power getSelectedPower () {
		return selectedPower;
	}

	public void setSelectedPower (Power selectedPower) {
		this.selectedPower = selectedPower;
	}

	@Override
	public String toString() {
		String result = "";
		result += "dead: " + this.nbLemDead + "/" + this.nbLemTotal + "  ";
		result += "saved: " + this.nbLemSaved + "/" + this.nbLemToSave + "  ";
		result += "time: " + this.gameTime +" sec";
		return result;
	}

	public boolean selectedPowerCanBeUsed () {
		return powerUps.get(selectedPower) != 0;
	}

	public Player getPlayer () {
		return player;
	}

	public void setPlayer (Player player) {
		this.player = player;
	}
}
