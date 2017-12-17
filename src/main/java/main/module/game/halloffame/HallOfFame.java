package main.module.game.halloffame;

import main.module.game.player.Player;

import java.io.*;
import java.util.Stack;

public class HallOfFame {
	private static String PATH = "high scores/hall of fame.db";
	private Stack<Player> database;

	public HallOfFame () {
		this.database = new Stack<>();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(PATH)));
		this.loadDataBase(bufferedReader);
	}

	public void addPlayer (Player player) {
		if (isHighScore(player.getScore())) {
			this.database.push(player);
			try {
				String newHighScore = player.getName() + "	" + player.getScore();
				File file = new File(ClassLoader.getSystemResource(PATH).toURI());
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
				bufferedWriter.write(newHighScore);
				bufferedWriter.newLine();
				bufferedWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isHighScore (int score) {
		if (database.empty())
			return true;
		return database.peek().getScore() < score;
	}

	public Stack<Player> getDatabase () {
		return database;
	}

	private void loadDataBase (BufferedReader bufferedReader) {
		try {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] player = line.split("	");
				this.database.push(new Player(player[0], Integer.parseInt(player[1])));
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}