package main.module.game.halloffame;

import main.module.game.player.Player;

import java.io.*;
import java.util.Stack;

public class HallOfFame {
	private static String PATH = "high scores/high scores.db";
	private Stack<Player> database;

	public HallOfFame () {
		this.database = new Stack<Player>();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(PATH)));
		this.loadDataBase(bufferedReader);
	}

	public void addPlayer (Player player) {
		if (isHighScore(player.getScore())) {
			this.database.push(player);

			try {
				//TODO work on adding the new high score into the file
				String newHighScore = player.getName() + "	" + player.getScore();
				FileWriter fileWriter = new FileWriter(new File(ClassLoader.getSystemResource(PATH).getPath()));
				if (fileWriter == null)
					System.out.println("test");
			} catch (IOException e) {
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
		//TODO needs to be implemented
		/*try {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] player = line.split("	");

				this.database.push(new Player(player[0], Integer.parseInt(player[1])));
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
}