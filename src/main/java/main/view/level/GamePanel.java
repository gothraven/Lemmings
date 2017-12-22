package main.view.level;

import main.module.event.level.LevelEvent;
import main.module.game.level.Level;
import main.module.game.level.lemming.Lemming;
import main.module.game.level.map.Map;
import main.util.event.Event;
import main.util.geometry.Position;
import main.util.observer.Observer;
import main.view.level.lemming.GLemming;
import main.view.level.map.GMap;
import main.view.level.status.GStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GamePanel extends JComponent implements Observer {

	public static final int SCALE = 25;
	private static final int STATUS_HEIGHT = 210;
	private long start;
	private long targetTime;

	private Level level;
	private ArrayList<Lemming> lemmings;
	private Map map;
	private GStatus status;

	public GamePanel(Dimension panelDimentions)
	{
		int width = (int) panelDimentions.getWidth();
		int height = (int) panelDimentions.getHeight();
		setPreferredSize(new Dimension(width * SCALE, height * SCALE +  STATUS_HEIGHT));

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (level != null)
					level.mouseClicked(new Position((e.getX() / SCALE), (e.getY() / SCALE)));
			}
		});

		setFocusable(true);
		requestFocus();
		requestFocusInWindow();
		this.lemmings = new ArrayList<>();
		this.status = new GStatus(new Position(20, height * SCALE));
		start = System.currentTimeMillis();
		int FPS = 3;
		targetTime = 1000 / FPS;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		start = System.currentTimeMillis();

		showHelp(g);
		if (map != null)
			GMap.draw(map, g);

		for (Lemming lemming : lemmings) GLemming.draw(lemming, g);

		status.draw(g);
	}

	public void display() {
		this.setVisible(true);
	}

	private void update (LevelEvent event) {
		map = new Map(event.getMap());
		lemmings.clear();
		lemmings.addAll(event.getLemmings());
		status.update(event.getLevelInfo());

		long elapsed = System.currentTimeMillis() - start;
		long wait =  targetTime - elapsed / 1000000;

		if(wait < 0) wait = 5000;
		try {
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		repaint();
	}

	public void update (Event e) {
		if (e.getClass() == LevelEvent.class)
			update((LevelEvent)e);
	}

	public void keyPressed (char keyChar) {
		level.keyPressed(keyChar);
	}

	public void setLevel (Level level) {
		this.level = level;
	}

	private void showHelp (Graphics g) {
		String newLine = System.getProperty("line.separator");
		int x = getSize().width / 2;
		int y = getSize().height - STATUS_HEIGHT;

		String message = "Controle keys are the following:" + newLine + newLine
				+ "- A to to change a  lemming to a BLOCKER" + newLine
				+ "- Z to to change a  lemming to a BOMBER" + newLine
				+ "- E to to change a  lemming to a BUILDER" + newLine
				+ "- Q to to change a  lemming to a CLIMBER" + newLine
				+ "- S to to change a  lemming to a DIGGER" + newLine
				+ "- D to to change a  lemming to a MINER" + newLine
				+ "- W to to change a  lemming to a PARATROOPER" + newLine
				+ "- P for pausing and unpausing the game";
		for (String line : message.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}
}