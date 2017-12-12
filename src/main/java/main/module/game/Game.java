package main.module.game;


import main.module.game.halloffame.HallOfFame;
import main.module.game.level.Level;
import main.module.game.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Game {
    private Player player;
    private HallOfFame hallOfFame;
    private Stack<Level> levels;

    public Game(String playerName){
        this.player = new Player(playerName);
        this.hallOfFame = new HallOfFame();
        this.levels = new Stack<Level>();
    }

    public void loadLevels(){
        BufferedReader buff = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("test.lvl")));
        try {
            String s = buff.readLine();
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer () {
        return player;
    }
}


