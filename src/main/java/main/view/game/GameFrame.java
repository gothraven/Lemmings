package main.view.game;

import main.module.event.game.GameEvent;
import main.module.game.level.Level;
import main.util.event.Event;
import main.util.factory.EventFactory;
import main.util.observebale.Observable;
import main.util.observer.Observer;
import main.view.level.GamePanel;

import javax.swing.*;

public class GameFrame implements Observer {

	private JFrame window;
	private GamePanel gameView;

	public GameFrame() {
		window = new JFrame("Lemmings");
		this.init();
	}

	private void init() {
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(200, 100);
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
		if (e.getID() == EventFactory.LEVELSTART) {
			Observable o = e.getObservable();
			if (o.getClass() == Level.class) {
				Level level = (Level)o;
				this.gameView = new GamePanel(level.getMapDimensions());
				this.window.setContentPane(gameView);
				this.window.pack();
				level.registerObserver(this.gameView);
			}
		}else if (e.getID() == EventFactory.GAMEEND) {
			this.end();
		}
	}

	public void update (Event e) {
		if (e.getClass() == GameEvent.class)
			update((GameEvent)e);
	}
}
