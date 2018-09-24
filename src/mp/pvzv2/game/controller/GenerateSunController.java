package mp.pvzv2.game.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

import mp.pvzv2.game.model.Cell;
import mp.pvzv2.game.model.GameModel;
import mp.pvzv2.game.model.Map;
import mp.pvzv2.game.model.Plant;
import mp.pvzv2.game.model.Sun;
import mp.pvzv2.game.model.Sunflower;

public class GenerateSunController {
	public GenerateSunController(GameModel model) {
		this.model = model;
		rand = new Random();
	}

	private void generateSunsFromSunflowers() {
		LocalDateTime now = LocalDateTime.now();
		
		for (int i = 0; i < model.getPlants().size(); i++) {
			Plant plant = (Plant) model.getPlants().get(i);
			if (plant instanceof Sunflower && 
					Duration.between(plant.getLastUpdateTime(), now).getSeconds() >= plant.getSpeed()) {
				Sun sun = new Sun(plant.getX(), plant.getY());
				model.getSuns().add(sun);
				plant.setLastUpdateTime(now);
			}
		}
	}

	private void generateAnimatingSun() {
		LocalDateTime now = LocalDateTime.now();
		if (Duration.between(model.getAnimatingSunLastUpdateTime(), now).getSeconds() >= 20) {
			int column = rand.nextInt(8);
			Sun sun = new Sun(Map.getColumnPosition(column), 0);
			sun.setTargetCell(new Cell(rand.nextInt(4), column));
			model.getAnimatingSuns().add(sun);
			model.setAnimatingSunLastUpdateTime(now);
		}
	}

	public void tick() {
		generateSunsFromSunflowers();
		generateAnimatingSun();
	}
	
	private GameModel model;
	private Random rand;
}
