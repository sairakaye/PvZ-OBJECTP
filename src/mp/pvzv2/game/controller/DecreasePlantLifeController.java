package mp.pvzv2.game.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import mp.pvzv2.game.model.Cell;
import mp.pvzv2.game.model.GameModel;
import mp.pvzv2.game.model.Map;
import mp.pvzv2.game.model.PVZObject;
import mp.pvzv2.game.model.Plant;
import mp.pvzv2.game.model.Zombie;

public class DecreasePlantLifeController {	
	public DecreasePlantLifeController(GameModel model) {
		this.model = model;
	}
	
	private Zombie getZombieNeighbor(Plant plant) {
		Cell plantCell = Map.getMapCell(plant.getX(), plant.getY());
		
		for (int i = 0; i < model.getZombies().size(); i++) {
			Zombie zombie = (Zombie) model.getZombies().get(i);
			Cell zombieCell = Map.getMapCell(zombie.getX(), zombie.getY() + 100);
			
			if (zombieCell.getRow() == plantCell.getRow() && (zombieCell.getColumn() == plantCell.getColumn() 
					|| zombieCell.getColumn() == plantCell.getColumn() - 1))
				return zombie;
		}
		
		return null;
	}
	
	
	public void tick() {
		ArrayList<PVZObject> plants = model.getPlants();
		LocalDateTime now = LocalDateTime.now();
		
		for (int i = 0; i < plants.size(); i++) {
			Plant plant = (Plant) plants.get(i);
			Zombie zombie = getZombieNeighbor(plant);
			
			if (zombie != null) {
				if (Duration.between(zombie.getLastUpdateTime(), now).getSeconds() >= 3) {
					plant.setHealth(plant.getHealth() - zombie.getDamage());
					zombie.setLastUpdateTime(now);
				}
				
				if (plant.getHealth() <= 0) {
					model.getPlants().remove(i);
					i--;
				}
			}
		}
	}
	
	private GameModel model;
}