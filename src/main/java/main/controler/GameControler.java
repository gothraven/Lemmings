package main.controler;

import main.module.game.Game;
import main.view.game.GameFrame;

public class GameControler {

	private Game game;
	private GameFrame window;

	public GameControler()
	{
		//String player = JOptionPane.showInputDialog("What is your name");
		String player = "test";
		if (player == null || player.isEmpty())
			player = "test";
		game = new Game(player);
		window = new GameFrame();
		game.registerObserver(window);
		game.start();
		while (game.isOn())
		{
			run();
		}
		this.window.end();
	}

	private void run() {
		game.update();
		window.show();
	}
}
