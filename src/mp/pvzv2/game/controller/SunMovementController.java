package mp.pvzv2.game.controller;

import java.awt.Point;
import java.util.ArrayList;

import mp.pvzv2.game.model.GameModel;
import mp.pvzv2.game.model.Map;
import mp.pvzv2.game.model.PVZObject;
import mp.pvzv2.game.model.Sun;

public class SunMovementController{
	public SunMovementController(GameModel model) {
		this.model = model;
	}
	
	public void tick() {
		ArrayList<PVZObject> suns = model.getAnimatingSuns();
		for (int i = 0; i < suns.size(); i++) {
			Sun sun = (Sun)suns.get(i);
			
			Point position = Map.getMapCellPosition(sun.getTargetCell());
			if (sun.getY() >= position.y) {
				model.getAnimatingSuns().remove(i);
				i--;
				
				sun.setY(position.y);
				model.getSuns().add(sun);
			} else
				sun.setY(sun.getY() + sun.getvelY());
		}
	}
	
	private GameModel model;
}
