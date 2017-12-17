package main.view.game;

import main.module.event.game.GameEvent;
import main.util.event.Event;
import main.util.factory.EventFactory;
import main.util.observer.Observer;
import main.view.level.GamePanel;

import javax.swing.*;
import java.awt.*;

public class GameFrame implements Observer {

	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;
	public static final int SCALE = 30;

	private JFrame window;
	private GamePanel gameView;

	public GameFrame() {
		window = new JFrame("Lemmings");
		gameView = new GamePanel();
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

	/*public void showHelp() {
		gameView.showHelp();
	}*/

	public void update (GameEvent e) {
		if (e.getID() == EventFactory.GAMESTART) {
			gameView.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE + 30));
			e.getObservable().registerObserver(this.gameView);
		}else if (e.getID() == EventFactory.GAMEEND) {
			this.end();
		}
	}

	public void update (Event e) {
		if (e.getClass() == GameEvent.class)
			update((GameEvent)e);
	}
}
