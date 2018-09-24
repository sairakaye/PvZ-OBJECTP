package mp.pvzv2.game.controller;

import java.util.ArrayList;
import mp.pvzv2.game.model.Cell;
import mp.pvzv2.game.model.GameModel;
import mp.pvzv2.game.model.Map;
import mp.pvzv2.game.model.PVZObject;
import mp.pvzv2.game.model.Zombie;

public class DetermineWinnerController {
	public DetermineWinnerController(GameModel model) {
		this.model = model;
	}
	
	public void tick() {
		ArrayList<PVZObject> zombies = model.getZombies();
		
		for (int i = 0; i < zombies.size(); i++) {
			Zombie zombie = (Zombie)zombies.get(i);
			Cell zombieCell = Map.getMapCell(zombie.getX(), zombie.getY());
			
			if (zombieCell.getColumn() < 0) {
				model.setEndGame(true);
				model.setWinner("Zombies");
				break;
			}
		}
		
		if (GameController.getRuntime() >= 180) {
			model.setEndGame(true);
			model.setWinner("Plants");		
		}
	}
	
	private GameModel model;
}
