package mp.pvzv2.game.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;

import javax.swing.event.MouseInputListener;

import mp.pvzv2.game.model.Cell;
import mp.pvzv2.game.model.CherryBomb;
import mp.pvzv2.game.model.GameModel;
import mp.pvzv2.game.model.Map;
import mp.pvzv2.game.model.Peashooter;
import mp.pvzv2.game.model.Plant;
import mp.pvzv2.game.model.Sunflower;
import mp.pvzv2.game.model.Zombie;

public class PlantAssignmentController implements MouseInputListener {
	public PlantAssignmentController(GameModel model) {
		this.model = model;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Plant plant = null;
		String plantType = Map.getPlantButton(e.getX(), e.getY());
		if (plantType.equalsIgnoreCase("Peashooter") && model.getSun() >= 100 &&
				model.getInactivePeashooterButton().isRegen() == false)
			plant = new Peashooter(e.getX(), e.getY());
		else if (plantType.equalsIgnoreCase("Sunflower") && model.getSun() >= 50 &&
					model.getInactiveSunflowerButton().isRegen() == false)
			plant = new Sunflower(e.getX(), e.getY());
		else if (plantType.equalsIgnoreCase("CherryBomb") && model.getSun() >= 150 &&
					model.getInactiveCherryBombButton().isRegen() == false)
			plant = new CherryBomb(e.getX(), e.getY());
		
		if (plant != null) {
			plant.adjustPosition();
			model.setAssignedPlant(plant);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Plant plant = model.getAssignedPlant();
		model.setAssignedPlant(null);
		
		if (plant != null) {
			Cell cell = Map.getMapCell(e.getX(), e.getY());
			
			if (cell.getRow() >= 0 && cell.getColumn() >= 0 && model.isMapCellEmpty(cell)) {
				Point position = Map.getMapCellPosition(cell);
				plant.setX(position.x);
				plant.setY(position.y);
				plant.setLastUpdateTime(LocalDateTime.now());
				
				int amount = plant.getCost();
				
				if (plant instanceof CherryBomb)
					explodeCherryBomb(plant);
				else
					model.getPlants().add(plant);
				
				if (plant instanceof Peashooter) {
					model.getInactivePeashooterButton().setLastUpdateTime(LocalDateTime.now());
					model.getInactivePeashooterButton().setRegen(true);
				} else if (plant instanceof CherryBomb) {
					model.getInactiveCherryBombButton().setLastUpdateTime(LocalDateTime.now());
					model.getInactiveCherryBombButton().setRegen(true);
				} else if (plant instanceof Sunflower) {
					model.getInactiveSunflowerButton().setLastUpdateTime(LocalDateTime.now());
					model.getInactiveSunflowerButton().setRegen(true);
				}
				
				model.setSun(model.getSun() - amount);
			}
		}
	}

	private void explodeCherryBomb(Plant cherrybomb) {
		Cell plantCell = Map.getMapCell(cherrybomb.getX(), cherrybomb.getY());
		
		for (int i = 0; i < model.getZombies().size(); i++) {
			Zombie zombie = (Zombie) model.getZombies().get(i);
			Cell zombieCell = Map.getMapCell(zombie.getX(), zombie.getY() + 100);
			
			int plantRow = plantCell.getRow();
			int zombieRow = zombieCell.getRow();
			int plantCol = plantCell.getColumn();
			int zombieCol = zombieCell.getColumn();
			
			if ((zombieRow == plantRow || zombieRow == plantRow - 1 || zombieRow == plantRow + 1) &&
				(zombieCol == plantCol || zombieCol == plantCol - 1 || zombieCol == plantCol + 1)) {
				zombie.setHealth(zombie.getHealth() - cherrybomb.getdirDamage());
				
				if (zombie.getHealth() <= 0) {
					model.getZombies().remove(i);
					i--;
				}
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Plant plant = model.getAssignedPlant();
		if (plant != null) {
			plant.setX(e.getX());
			plant.setY(e.getY());
			plant.adjustPosition();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	private GameModel model;
}
