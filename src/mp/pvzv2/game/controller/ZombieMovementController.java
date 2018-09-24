package mp.pvzv2.game.controller;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import mp.pvzv2.game.model.GameModel;
import mp.pvzv2.game.model.PVZObject;

public class ZombieMovementController {
	public ZombieMovementController(GameModel model) {
		this.model = model;
	}
	
	public void tick() {
		ArrayList<PVZObject> zombies = model.getZombies();
		LocalDateTime now = LocalDateTime.now();
		
		for (int i = 0; i < zombies.size(); i++) {
			PVZObject zombie = zombies.get(i);
			boolean hasNeighbor = model.doesZombieHasPlantNeighbor(zombie) || 
									model.doesZombieHasZombieNeighbor(zombie);
			
			if (!hasNeighbor && Duration.between(zombie.getLastUpdateTime(), now).getNano() >= 200000000) {
				int newZombieXPosition = zombie.getX() + zombie.getvelX();
				zombie.setX(newZombieXPosition);
				zombie.setLastUpdateTime(now);
			}
		}
	}
	
	private GameModel model;
}
