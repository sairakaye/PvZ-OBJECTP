package mp.pvzv2.game.controller;

import java.util.ArrayList;

import mp.pvzv2.game.model.Bullet;
import mp.pvzv2.game.model.GameModel;
import mp.pvzv2.game.model.PVZObject;
import mp.pvzv2.game.model.Zombie;

public class DecreaseZombieLifeController {
	public DecreaseZombieLifeController(GameModel model) {
		this.model = model;
	}
	
	public void tick() {
		ArrayList<PVZObject> zombies = model.getZombies();
		ArrayList<PVZObject> bullets = model.getBullets();
		
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = (Bullet)bullets.get(i);
			
			for (int j = 0; j < zombies.size(); j++) {
				Zombie zombie = (Zombie)zombies.get(j);
				
				if ((bullet.getX() >= zombie.getX() && bullet.getX() < zombie.getX() + 132) && 
						(bullet.getY() >= zombie.getY() && bullet.getY() < zombie.getY() + 132)) {
					model.getBullets().remove(i);
					i--;
					zombie.setHealth(zombie.getHealth() - bullet.getDamage());
					
					if (zombie.getHealth() <= 0)
						model.getZombies().remove(j);
					
					break;
				}
			}
		}
	}
	
	private GameModel model;
}
