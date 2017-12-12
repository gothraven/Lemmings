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

    public static void main (String[] args) {
        HallOfFame hf = new HallOfFame();
        System.out.println(hf.getDatabase().peek().getName());
        System.out.println(hf.getDatabase().peek().getScore());
    }

    public void addPlayer(Player player) {
        if (isHighScore(player.getScore())) {
            this.database.push(player);
            //TODO add the player to the high score chart
        }

    }

    private boolean isHighScore(int score) {
        return database.peek().getScore() < score;
    }

    public Stack<Player> getDatabase () {
        return database;
    }

    private void loadDataBase(BufferedReader bufferedReader) {
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] player = line.split("  ");

                this.database.push(new Player(player[0], 123));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
