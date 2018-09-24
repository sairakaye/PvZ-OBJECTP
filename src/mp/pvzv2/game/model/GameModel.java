package mp.pvzv2.game.model;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.ArrayList;

import mp.pvzv2.game.controller.GameController;

public class GameModel {
	
	public GameModel() {
		zombies = new ArrayList<PVZObject>();
		plants = new ArrayList<PVZObject>();
		animatingSuns = new ArrayList<PVZObject>();
		suns = new ArrayList<PVZObject>();
		bullets = new ArrayList<PVZObject>();
		map = new Map();
		level = 1;
		sun = 50;
		LocalDateTime now = LocalDateTime.now();
		animatingSunLastUpdateTime = now;
		startGameTime = now;
		zombieSpawnLastUpdateTime = now;
		isFinalWave = false;
		inactiveCherryBombButton = new CherryBombButton();
		inactivePeashooterButton = new PeashooterButton();
		inactiveSunflowerButton = new SunflowerButton();
	}
	
	public BufferedImage getBackground() {
		return background;
	}
	
	public void setBackground(BufferedImage background) {
		this.background = background;
	}
	
	public BufferedImage getPlantsWinImg() {
		return plantsWinImg;
	}
	
	public void setPlantsWinImg(BufferedImage plantsWinImg) {
		this.plantsWinImg = plantsWinImg;
	}
	
	public BufferedImage getZombiesWinImg() {
		return zombiesWinImg;
	}
	
	public void setZombiesWinImg(BufferedImage zombiesWinImg) {
		this.zombiesWinImg = zombiesWinImg;
	}
	
	public BufferedImage getLastWinImg() {
		return lastWinImg;
	}
	
	public void setLastWinImg(BufferedImage lastWinImg) {
		this.lastWinImg = lastWinImg;
	}
	
	public BufferedImage getZombiesComingImg() {
		return zombiesComingImg;
	}
	
	public void setZombiesComingImg(BufferedImage zombiesComingImg) {
		this.zombiesComingImg = zombiesComingImg;
	}
	
	public BufferedImage getFinalWaveImg() {
		return finalWaveImg;
	}
	
	public void setFinalWaveImg(BufferedImage finalWaveImg) {
		this.finalWaveImg = finalWaveImg;
	}
	
	public CherryBombButton getInactiveCherryBombButton() {
		return inactiveCherryBombButton;
	}

	public PeashooterButton getInactivePeashooterButton() {
		return inactivePeashooterButton;
	}
	
	public SunflowerButton getInactiveSunflowerButton() {
		return inactiveSunflowerButton;
	}

	public ArrayList<PVZObject> getZombies() {
		return zombies;
	}
	
	public ArrayList<PVZObject> getPlants() {
		return plants;
	}
	
	public ArrayList<PVZObject> getAnimatingSuns() {
		return animatingSuns;
	}
	
	public ArrayList<PVZObject> getSuns() {
		return suns;
	}

	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public Plant getAssignedPlant() {
		return assignedPlant;
	}
	public void setAssignedPlant(Plant plant) {
		assignedPlant = plant;
	}
	
	public int getSun() {
		return sun;
	}
	
	public void setSun(int sun) {
		this.sun = sun;
	}
	
	public ArrayList<PVZObject> getBullets() {
		return bullets;
	}
	
	public boolean isEndGame() {
		return isEndGame;
	}
	
	public void setEndGame(boolean endgame) {
		isEndGame = endgame;
	}
	
	public String getWinner() {
		return winner;
	}
	
	public void setWinner(String winner) {
		this.winner = winner;
	}

	public boolean isFinalWave() {
		return isFinalWave;
	}

	public void setFinalWave(boolean isFinalWave) {
		this.isFinalWave = isFinalWave;
	}

	public LocalDateTime getStartGameTime() {
		return startGameTime;
	}

	public void setStartGameTime(LocalDateTime startGameTime) {
		this.startGameTime = startGameTime;
	}

	public LocalDateTime getZombieSpawnLastUpdateTime() {
		return zombieSpawnLastUpdateTime;
	}

	public void setZombieSpawnLastUpdateTime(LocalDateTime zombieSpawnLastUpdateTime) {
		this.zombieSpawnLastUpdateTime = zombieSpawnLastUpdateTime;
	}

	public LocalDateTime getAnimatingSunLastUpdateTime() {
		return animatingSunLastUpdateTime;
	}

	public void setAnimatingSunLastUpdateTime(LocalDateTime animatingSunLastUpdateTime) {
		this.animatingSunLastUpdateTime = animatingSunLastUpdateTime;
	}
	
	public boolean doesZombieHasPlantNeighbor(PVZObject zombie) {
		Cell zombieCell = Map.getMapCell(zombie.getX(), zombie.getY() + 100);
		int newZombieXPosition = zombie.getX() + zombie.getvelX();
		
		ArrayList<PVZObject> plants = getPlants();
		for (int j = 0; j < plants.size(); j++) {
			Plant plant = (Plant)plants.get(j);
			Cell plantCell = Map.getMapCell(plant.getX(), plant.getY());
			
			if (plantCell.getRow() == zombieCell.getRow() && (newZombieXPosition >= plant.getX() 
					&& newZombieXPosition < plant.getX() + 123))
				return true;
		}
		
		return false;
	}
	
	public boolean doesZombieHasZombieNeighbor(PVZObject zombie) {
		Cell zombieCell = Map.getMapCell(zombie.getX(), zombie.getY() + 100);
		int newZombieXPosition = zombie.getX() + zombie.getvelX();
		ArrayList<PVZObject> zombies = getZombies();
		
		for (int j = 0; j < zombies.size(); j++) {
			Zombie neighborZombie = (Zombie)zombies.get(j);
			if (zombie == neighborZombie)
				continue;
			
			Cell neighborZombieCell = Map.getMapCell(neighborZombie.getX(), neighborZombie.getY() + 100);
			
			if (neighborZombieCell.getRow() == zombieCell.getRow() && (newZombieXPosition >= neighborZombie.getX() 
					&& newZombieXPosition < neighborZombie.getX() + 123))
				return true;
		}
		
		return false;
	}
	
	public boolean isMapCellEmpty(Cell cell) {
		ArrayList<PVZObject> plants = getPlants();
		for (int i = 0; i < plants.size(); i++) {
			Plant plant = (Plant)plants.get(i);
			Cell plantCell = Map.getMapCell(plant.getX(), plant.getY());
			
			if (plantCell.getRow() == cell.getRow() && plantCell.getColumn() == cell.getColumn())
				return false;
		}
		
		return true;
	}
	
	public void reset() {
		zombies = new ArrayList<PVZObject>();
		plants = new ArrayList<PVZObject>();
		animatingSuns = new ArrayList<PVZObject>();
		suns = new ArrayList<PVZObject>();
		bullets = new ArrayList<PVZObject>();
		LocalDateTime now = LocalDateTime.now();
		animatingSunLastUpdateTime = now;
		startGameTime = now;
		zombieSpawnLastUpdateTime = now;
		isFinalWave = false;
		isEndGame = false;
		sun = 50;
		GameController.resetRuntime();
	}
	
	private BufferedImage background;
	private BufferedImage plantsWinImg;
	private BufferedImage zombiesWinImg;
	private BufferedImage lastWinImg;
	private BufferedImage zombiesComingImg;
	private BufferedImage finalWaveImg;
	private CherryBombButton inactiveCherryBombButton;
	private PeashooterButton inactivePeashooterButton;
	private SunflowerButton inactiveSunflowerButton;
	private ArrayList<PVZObject> zombies;
	private ArrayList<PVZObject> plants;
	private ArrayList<PVZObject> animatingSuns;
	private ArrayList<PVZObject> suns;
	private ArrayList<PVZObject> bullets;
	private int level;
	private Plant assignedPlant;
	private Map map;
	private int sun;
	private boolean isEndGame;
	private String winner;
	private boolean isFinalWave;
	private LocalDateTime animatingSunLastUpdateTime;
	private LocalDateTime startGameTime;
	private LocalDateTime zombieSpawnLastUpdateTime;
}
