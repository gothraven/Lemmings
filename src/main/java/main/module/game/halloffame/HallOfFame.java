package main.module.game.halloffame;

import main.module.game.player.Player;

import java.util.Stack;

public class HallOfFame {

    private Stack<Player> database;

    public HallOfFame (){
        this.database = new Stack<Player>();
        //TODO look for the halloffame data base file
        //TODO load it if it exists
        //TODO fill the hashmap with its content
    }

    public void addPlayer(Player player) {
        if (isHighScore(player.getScore()))
            this.database.push(player);
    }

    private boolean isHighScore(int score) {
        return database.peek().getScore() < score;
    }

    public Stack<Player> getDatabase () {
        return database;
    }
}
