package mp.pvzv2.game.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.event.MouseInputListener;

import mp.pvzv2.game.model.GameModel;
import mp.pvzv2.game.model.PVZObject;
import mp.pvzv2.game.model.Sun;

public class CollectingSunController implements MouseInputListener {
	public CollectingSunController(GameModel model) {
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
		ArrayList<PVZObject> suns = model.getSuns();
		ArrayList<PVZObject> asuns = model.getAnimatingSuns();
		Point position = e.getPoint();
		Sun collect = null;
		
		for (int i = 0; i < suns.size(); i++) {
			Sun sun = (Sun)suns.get(i);
			if ((position.x >= sun.getX() && position.x < sun.getX() + 103) 
				&& (position.y >= sun.getY() && position.y < sun.getY() + 103)) {
				suns.remove(i);
				collect = sun;
			}
		}
		
		for (int i = 0; i < asuns.size(); i++) {
			Sun sun = (Sun)asuns.get(i);
			
			if ((position.x >= sun.getX() && position.x < sun.getX() + 103) 
					&& (position.y >= sun.getY() && position.y < sun.getY() + 103)) {
				asuns.remove(i);
				collect = sun;			
			}
		}
		
		if (collect != null) {
			model.setSun(model.getSun() + collect.getAmount());
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	private GameModel model;
}