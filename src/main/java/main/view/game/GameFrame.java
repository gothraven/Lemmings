package main.view.game;

import main.module.event.game.GameEvent;
import main.module.game.Game;
import main.module.game.level.Level;
import main.module.game.player.Player;
import main.util.event.Event;
import main.util.factory.EventFactory;
import main.util.observebale.Observable;
import main.util.observer.Observer;
import main.view.level.GamePanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Stack;

public class GameFrame implements Observer {

	private JFrame window;
	private GamePanel gameView;
	private Game game;

	public GameFrame() {
		window = new JFrame("Lemmings");
		this.init();
	}

	private void init() {
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(200, 100);
		window.setResizable(false);
		window.setVisible(true);
		window.pack();
		window.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed (KeyEvent e) {
				if (gameView != null)
					gameView.keyPressed(e.getKeyChar());
			}
		});
	}

	public void show() {
		if (gameView != null)
			gameView.display();
	}

	private void end () {
		String message = "Your Score " + game.getPlayer().getScore();
		JOptionPane.showMessageDialog(null, message, "Game Over", 1);
		String highScores = "";
		Stack<Player> dataBase = game.getHallOfFame().getDatabase();
		while (! dataBase.isEmpty())
			highScores += dataBase.pop().toString() + System.getProperty("line.separator");
		JOptionPane.showMessageDialog(null, highScores, "High Scores", 1);
		window.dispose();
	}

	private void update (GameEvent e) {
		if (e.getID() == EventFactory.GAMESTART) {
			Observable o = e.getObservable();
			if (o.getClass() == Game.class)
				this.game = (Game)o;
		} else if (e.getID() == EventFactory.LEVELSTART) {
			Observable o = e.getObservable();
			if (o.getClass() == Level.class) {
				Level level = (Level)o;
				this.gameView = new GamePanel(level.getMapDimensions());
				this.gameView.setLevel(level);
				this.window.setContentPane(gameView);
				this.window.pack();
				level.registerObserver(this.gameView);
			}
		} else if (e.getID() == EventFactory.GAMEEND) {
			this.end();
		}
	}

	public void update (Event e) {
		if (e.getClass() == GameEvent.class)
			update((GameEvent)e);
	}
}
