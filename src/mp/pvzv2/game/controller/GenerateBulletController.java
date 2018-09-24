package mp.pvzv2.game.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import mp.pvzv2.game.model.Bullet;
import mp.pvzv2.game.model.Cell;
import mp.pvzv2.game.model.GameModel;
import mp.pvzv2.game.model.Map;
import mp.pvzv2.game.model.PVZObject;
import mp.pvzv2.game.model.Peashooter;
import mp.pvzv2.game.model.Plant;
import mp.pvzv2.game.model.Zombie;

public class GenerateBulletController {	
	public GenerateBulletController(GameModel model) {
		this.model = model;
	}
	
	public void tick() {
		ArrayList<PVZObject> zombies = model.getZombies();
		
		for (int i = 0; i < model.getPlants().size(); i++) {
			Plant plant = (Plant) model.getPlants().get(i);
			
			if (plant instanceof Peashooter) {
				Cell plantCell = Map.getMapCell(plant.getX(), plant.getY());
				LocalDateTime now = LocalDateTime.now();
				
				for (int j = 0; j < zombies.size(); j++) {
					Zombie zombie = (Zombie) zombies.get(j);
					Cell zombieCell = Map.getMapCell(zombie.getX(), zombie.getY() + 100);
					
					if (zombieCell.getRow() == plantCell.getRow() && 
						Duration.between(plant.getLastUpdateTime(), now).getSeconds() >= plant.getSpeed()) {
						Bullet bullet;
						
						if (plantCell.getColumn() + 3 >= zombieCell.getColumn())
							bullet = new Bullet(plant.getX() + plant.getImage().getWidth() / 2, 
									(plant.getY() + plant.getImage().getHeight() /2) - 25, plant.getdirDamage());	
						else
							bullet = new Bullet(plant.getX() + plant.getImage().getWidth() / 2, 
									(plant.getY() + plant.getImage().getHeight() /2) - 25, plant.getDamage());
						
						model.getBullets().add(bullet);
						plant.setLastUpdateTime(now);
						
						break;
					}
				}
			}
		}
	}
	
	private GameModel model;
}