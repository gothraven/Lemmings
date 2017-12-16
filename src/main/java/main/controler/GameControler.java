package main.controler;

import main.module.game.Game;
import main.view.game.GameFrame;

public class GameControler {

	private Game game;
	private GameFrame window;

	public GameControler(String fname)
	{
		game = new Game(fname);
		//window = new GameFrame(game);
		//game.getLevel().setGameView(window.getGameView());
		//window.showHelp();
		while (game.isOn())
		{
			run();
		}
		//this.window.end();
	}

	public void run() {
		game.update();
		//window.show();
	}
}
