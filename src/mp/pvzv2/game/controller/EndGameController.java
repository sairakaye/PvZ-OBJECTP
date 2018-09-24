package mp.pvzv2.game.controller;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import mp.pvzv2.game.model.GameModel;

public class EndGameController implements MouseInputListener {
	public EndGameController(GameModel model) {
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
		if (model.isEndGame() && model.getLevel() < 3) {
			if (model.getWinner().equalsIgnoreCase("Plants")) {
				if (e.getX() >= 518 && e.getX() < 882 && e.getY() >= 428 && e.getY() < 519) {
					model.setLevel(model.getLevel() + 1);
					model.reset();
				} else if (e.getX() >= 891 && e.getX() < 1256 && e.getY() >= 428 && e.getY() < 519)
					System.exit(0);
			} else if (model.getWinner().equalsIgnoreCase("Zombies")) {
				if (e.getX() >= 518 && e.getX() < 882 && e.getY() >= 428 && e.getY() < 519) {
					model.setLevel(1);
					model.reset();
				} else if (e.getX() >= 891 && e.getX() < 1256 && e.getY() >= 428 && e.getY() < 519)
					System.exit(0);	
			}
		} else if (model.isEndGame() && model.getLevel() >= 3) {
			if (model.getWinner().equalsIgnoreCase("Plants")) {
				if (e.getX() >= 705 && e.getX() < 1070 && e.getY() >= 426 && e.getY() < 519)
					System.exit(0);
			} else if (model.getWinner().equalsIgnoreCase("Zombies")) {
				if (e.getX() >= 518 && e.getX() < 882 && e.getY() >= 428 && e.getY() < 519) {
					model.setLevel(1);
					model.reset();
				} else if (e.getX() >= 891 && e.getX() < 1256 && e.getY() >= 428 && e.getY() < 519)
					System.exit(0);		
			}			
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
