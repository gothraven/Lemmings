package main.view.game;

import main.module.event.factory.game.GameEvent;
import main.module.game.Game;
import main.util.event.Event;
import main.util.observer.Observer;
import main.view.level.GamePanel;

import javax.swing.*;

public class GameFrame implements Observer {
	private JFrame window;
	private GamePanel gameView;
	private Game game;

	public GameFrame(Game game) {
		window = new JFrame("Lemmings");
		this.game = game;
		gameView = new GamePanel(game.getLevel());
		this.init();
	}

	private void init() {
		window.setLocationRelativeTo(null);
		window.setContentPane(gameView);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(350, 100);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}

	public void show() {
		gameView.display();
	}

	public void end() {
		window.dispose();
		/*if (game.getLevel().getInfo().isWon())
			JOptionPane.showMessageDialog(null, "YOU WON", "Level end", 1);
		else
			JOptionPane.showMessageDialog(null, "YOU LOST", "Level end", 0);*/
	}

	public GamePanel getGameView() {
		return gameView;
	}

	public void showHelp() {
		//gameView.showHelp();
	}

	public void update (GameEvent e) {

	}

	public void update (Event e) {
		if (e.getClass() == GameEvent.class)
			update((GameEvent)e);
	}
}
