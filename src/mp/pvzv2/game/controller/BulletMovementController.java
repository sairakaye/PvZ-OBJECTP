package mp.pvzv2.game.controller;

import java.util.ArrayList;

import mp.pvzv2.game.model.Bullet;
import mp.pvzv2.game.model.GameModel;
import mp.pvzv2.game.model.PVZObject;

public class BulletMovementController {
	public BulletMovementController(GameModel model) {
		this.model = model;
	}
	
	public void tick() {
		ArrayList<PVZObject> bullets = model.getBullets();
		
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = (Bullet)bullets.get(i);
			
			bullet.setX(bullet.getX() + bullet.getvelX());
		}		
	}
	
	private GameModel model;
}
