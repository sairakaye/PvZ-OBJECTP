package mp.pvzv2.game.controller;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import mp.pvzv2.game.model.GameModel;
import mp.pvzv2.game.view.GameView;

public class GameController implements Runnable {
	public GameController(GameView view, GameModel model) {
		this.view = view;
		this.model = model;
		zombieSpawnController = new ZombieSpawnController(model);
		zombieMovementController = new ZombieMovementController(model);
		plantAssignmentController = new PlantAssignmentController(model);
		generateSunController = new GenerateSunController(model);
		sunMovementController = new SunMovementController(model);
		collectingSunController = new CollectingSunController(model);
		generateBulletController = new GenerateBulletController(model);
		bulletMovementController = new BulletMovementController(model);
		decreaseZombieLifeController = new DecreaseZombieLifeController(model);
		plantLifeController = new DecreasePlantLifeController(model);
		determineWinnerController = new DetermineWinnerController(model);
		endGameController = new EndGameController(model);
		buttonsController = new ButtonsController(model);
		view.addMouseListener(plantAssignmentController);
		view.addMouseListener(collectingSunController);
		view.addMouseListener(endGameController);
		loadImages();
	}
	
	private void loadImages()
	{
		try {
			model.setBackground(ImageIO.read(new File("res\\Background.png")));	
			model.setPlantsWinImg(ImageIO.read(new File("res\\plantswin.png")));
			model.setZombiesWinImg(ImageIO.read(new File("res\\zombieswin.png")));
			model.setLastWinImg(ImageIO.read(new File("res\\endlevel.png")));
			model.setZombiesComingImg(ImageIO.read(new File("res\\zombiecoming.png")));
			model.setFinalWaveImg(ImageIO.read(new File("res\\finalwave.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1) {
				view.render();
				
				if (!model.isEndGame())
					tick();
				
				delta--;
			}
			
			if (timer >= 1000000000) {
				if (!model.isEndGame())			
					runtime++;
				timer = 0;
			}
		}
	}
	
	public synchronized void start() {
		if (running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running)
			return;
		
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static int getRuntime() {
		return runtime;
	}
	
	public static void resetRuntime() {
		runtime = 0;
	}
	
	private void tick() {
		zombieSpawnController.tick();
		zombieMovementController.tick();
		generateSunController.tick();
		sunMovementController.tick();
		generateBulletController.tick();
		bulletMovementController.tick();
		decreaseZombieLifeController.tick();
		plantLifeController.tick();
		determineWinnerController.tick();
		buttonsController.tick();
	}
	
	private GameView view;
	private GameModel model;
	private ZombieSpawnController zombieSpawnController;
	private ZombieMovementController zombieMovementController;
	private PlantAssignmentController plantAssignmentController;
	private GenerateSunController generateSunController;
	private SunMovementController sunMovementController;
	private CollectingSunController collectingSunController;
	private GenerateBulletController generateBulletController;
	private BulletMovementController bulletMovementController;
	private DecreaseZombieLifeController decreaseZombieLifeController;
	private DecreasePlantLifeController plantLifeController;
	private DetermineWinnerController determineWinnerController;
	private EndGameController endGameController;
	private ButtonsController buttonsController;
	private boolean running = false;
	private Thread thread;
	private static volatile int runtime = 0;	
}