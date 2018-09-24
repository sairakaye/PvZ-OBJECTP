package mp.pvzv2.game.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import mp.pvzv2.game.model.GameModel;
import mp.pvzv2.game.model.Zombie;
import mp.pvzv2.game.model.ZombieConeHead;
import mp.pvzv2.game.model.ZombieFlag;

public class ZombieSpawnController {
	private GameModel model;
	private Random rand;
	private ArrayList<Integer> rowPositions;
	
	public ZombieSpawnController(GameModel model) {
		this.model = model;
		rowPositions = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++)
			rowPositions.add(12 + (158 * i));
		
		rand = new Random();
	}
	
	public void tick() {
		LocalDateTime now = LocalDateTime.now();
		int spawnsecs = 10;
		int value;
		int spawntype = rand.nextInt(2);
		
		if (GameController.getRuntime() >= 30) {
			if (GameController.getRuntime() >= 81 && GameController.getRuntime() < 141)
				spawnsecs = 5;
			else if (GameController.getRuntime() >= 141 && GameController.getRuntime() < 171)
				spawnsecs = 3;
			
			if (!model.isFinalWave() && 
					Duration.between(model.getZombieSpawnLastUpdateTime(), now).getSeconds() >= spawnsecs) {
				value = rand.nextInt(rowPositions.size());
				
				Zombie zombie;
				
				if (spawntype == 0)
					zombie = new ZombieConeHead(1354, rowPositions.get(value));
				else
					zombie = new Zombie(1354, rowPositions.get(value));	
				
				zombie.setLastUpdateTime(now);
				model.getZombies().add(zombie);
				model.setZombieSpawnLastUpdateTime(now);
			}
		
			if (!model.isFinalWave() &&
					Duration.between(model.getStartGameTime(), now).getSeconds() >= 171) {
				model.setStartGameTime(now);
				model.setFinalWave(true);
				value = rand.nextInt(rowPositions.size());
			
				Zombie zombieflag = new ZombieFlag(1354, rowPositions.get(value));
				zombieflag.setLastUpdateTime(now);
				model.getZombies().add(zombieflag);
			
				int numberOfZombies = 4;
				if (model.getLevel() == 2) {
					numberOfZombies = 6;
				} else if (model.getLevel() == 3) {
					numberOfZombies = 8;
				}
			
				for (int i = 0; i < numberOfZombies; i++) {
					value = rand.nextInt(rowPositions.size() - 1);
					Zombie zombie;
					
					if (spawntype == 1)
						zombie = new ZombieConeHead(1354, rowPositions.get(value));
					else
						zombie = new Zombie(1354, rowPositions.get(value));
					
					zombie.setLastUpdateTime(now);
					model.getZombies().add(zombie);
				}
			}
		}
	}
}