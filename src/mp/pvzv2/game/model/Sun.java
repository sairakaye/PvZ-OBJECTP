package mp.pvzv2.game.model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sun extends PVZObject {
	public Sun(int x, int y) {
		super(x, y);
		
		try {
			img = ImageIO.read(new File("res\\sun.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		amount = 25;
		
		setvelX(0);
		setvelY(4);
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Cell getTargetCell() {
		return targetCell;
	}

	public void setTargetCell(Cell targetCell) {
		this.targetCell = targetCell;
	}
	
	private int amount;
	private Cell targetCell;
}
