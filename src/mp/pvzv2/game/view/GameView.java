package mp.pvzv2.game.view;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

import mp.pvzv2.game.controller.GameController;
import mp.pvzv2.game.model.GameModel;
import mp.pvzv2.game.model.PVZObject;
import mp.pvzv2.game.model.Plant;


public class GameView {
	public GameView(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		
		frame.add(canvas);
		frame.pack();
		
		model = new GameModel();
		controller = new GameController(this, model);
		
	}
	
	public void show() {
		controller.start();
	}
	
	public void render() {
		bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.drawImage(model.getBackground(), 0, 0, null);
		g.setFont(new Font("Arial", Font.BOLD, 30));		
		g.drawString(Integer.toString(model.getSun()), 20, 195);
		
		renderButtons(g);
		renderMessage(g);
		renderPVZObjects(g, model.getPlants());		
		renderPVZObjects(g, model.getZombies());
		renderPVZObjects(g, model.getBullets());
		renderPVZObjects(g, model.getAnimatingSuns());
		renderPVZObjects(g, model.getSuns());
		renderAssignedPlant(g);
		
		if (model.isEndGame() == true)
			renderWinner(g);
		
		g.drawString("Level " + Integer.toString(model.getLevel()), 20, 790);
		g.drawString(String.format("%02d", GameController.getRuntime() / 60) + ":" + 
				String.format("%02d", GameController.getRuntime() % 60), 20, 850);
		
		g.dispose();
		bs.show();
	}
	
	private void renderPVZObjects(Graphics g, ArrayList<PVZObject> objects) {
		for (int i = 0; i < objects.size(); i++) {
			g.drawImage(objects.get(i).getImage(), objects.get(i).getX(), objects.get(i).getY(), null);
		}
	}
	
	private void renderAssignedPlant(Graphics g) {
		Plant plant = model.getAssignedPlant();
		
		if (plant != null) {
			g.drawImage(plant.getImage(), plant.getX(), plant.getY(), null);
		}
	}
	
	public void renderWinner(Graphics g) {
		if (model.getLevel() < 3) {
			if (model.getWinner().equals("Plants"))
				g.drawImage(model.getPlantsWinImg(), 430, 216, null);
			else if (model.getWinner().equals("Zombies"))
				g.drawImage(model.getZombiesWinImg(), 430, 216, null);
		} else if (model.getLevel() >= 3) {
			if (model.getWinner().equals("Plants"))
				g.drawImage(model.getLastWinImg(), 430, 216, null);
			else if (model.getWinner().equals("Zombies"))
				g.drawImage(model.getZombiesWinImg(), 430, 216, null);			
		}
	}
	
	public void renderButtons(Graphics g) {
		if (model.getInactiveSunflowerButton().isRegen() == true)
			g.drawImage(model.getInactiveSunflowerButton().getInactive(), 0, 262, null);
		if (model.getInactivePeashooterButton().isRegen() == true)
			g.drawImage(model.getInactivePeashooterButton().getInactive(), 0, 421, null);
		if (model.getInactiveCherryBombButton().isRegen() == true)
			g.drawImage(model.getInactiveCherryBombButton().getInactive(), 0, 578, null);
	}

	public void renderMessage(Graphics g) {
		if (GameController.getRuntime() >= 25 && GameController.getRuntime() < 27)
			g.drawImage(model.getZombiesComingImg(), 563, 374, null);
		if (GameController.getRuntime() >= 167 && GameController.getRuntime() < 169)
			g.drawImage(model.getFinalWaveImg(), 563, 374, null);
	}
	
	public void addMouseListener(MouseInputListener listener) {
		canvas.addMouseListener(listener);
		canvas.addMouseMotionListener(listener);
	}
	
	private JFrame frame;
	private Canvas canvas;
	private String title;
	private int width, height;
	private BufferStrategy bs;
	private Graphics g;
	private GameController controller;
	private GameModel model;
}