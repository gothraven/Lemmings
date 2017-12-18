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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GamePanel extends JComponent implements Observer{

	public static final int SCALE = 30;
	private static final int STATUS_HEIGHT = 30;

	private long start;
	private long targetTime;

	private ArrayList<Lemming> lemmings;
	private Map map;
	private String status;

	public GamePanel(Dimension panelDimentions)
	{
		int width = (int) panelDimentions.getWidth();
		int height = (int) panelDimentions.getHeight();
		setPreferredSize(new Dimension(width * SCALE, height * SCALE +  STATUS_HEIGHT));
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getX() + ", " + e.getY());
			}
		});


		setFocusable(true);
		requestFocus();
		this.lemmings = new ArrayList<>();
		this.status = "you have selected : ";
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
		/*g.setColor(Color.BLACK);
		g.drawString(status, 5, GamePanel.HEIGHT * GameFrame.SCALE + 20);*/
	}

	public void display() {
		this.setVisible(true);
	}

	/*
	private void updateStatus(LevelEvent event) {
	String statusUpdate = "you have selected : ";
	this.status = statusUpdate + event.getPowerSelected().getName();
	this.status += "         ";
	this.status += event.getInfo().toString();
	if (event.getInfo().isEnPause())
	this.status += "     PAUSED";
	}
	*/

	public void update(LevelEvent event) {

		map = event.getMap();
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
	/*public void showHelp() {
		String message = "Controle keys are the following: \n\n"
				+ "- A to to change a  lemming to a BLOCKER\n"
				+ "- Z to to change a  lemming to a CLIMBER\n"
				+ "- E to to change a  lemming to a BUILDER\n"
				+ "- Q to to change a  lemming to a DIGGER\n"
				+ "- S to to change a  lemming to a MINER\n"
				+ "- D to to change a  lemming to a PARATROOPER\n"
				+ "- W to to change a  lemming to a BOMBER\n"
				+ "- P for pausing and unpausing the game \n"
				+ "- H to show help while playing the game \n";
		JOptionPane.showMessageDialog(null, message, "Help",  1);
	}*/

}