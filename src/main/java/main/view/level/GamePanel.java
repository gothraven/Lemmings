package main.view.level;

import main.module.event.level.LevelEvent;
import main.module.game.level.lemming.Lemming;
import main.module.game.level.map.Map;
import main.util.event.Event;
import main.util.observer.Observer;
import main.view.level.lemming.GLemming;
import main.view.level.map.GMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GamePanel extends JComponent implements Observer {

	public static final int SCALE = 25;
	private static final int STATUS_HEIGHT = 30;

	private long start;
	private long targetTime;

	private ArrayList<Lemming> lemmings;
	private Map map;

	public GamePanel(Dimension panelDimentions)
	{
		int width = (int) panelDimentions.getWidth();
		int height = (int) panelDimentions.getHeight();
		setPreferredSize(new Dimension(width * SCALE, height * SCALE +  STATUS_HEIGHT));

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getX() + ", " + e.getY());

			}
		});

		setFocusable(true);
		requestFocus();
		requestFocusInWindow();
		this.lemmings = new ArrayList<>();
		start = System.currentTimeMillis();
		int FPS = 7;
		targetTime = 1000 / FPS;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		start = System.currentTimeMillis();
		if (map != null)
			GMap.draw(map, g);

		for (Lemming lemming : lemmings) GLemming.draw(lemming, g);
	}

	public void display() {
		this.setVisible(true);
	}

	public void update(LevelEvent event) {
		map = new Map(event.getMap());
		lemmings = event.getLemmings();
		//updateStatus(event);

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
		//TODO work on this later
		if (e.getClass() == LevelEvent.class)
			update((LevelEvent)e);
	}

}