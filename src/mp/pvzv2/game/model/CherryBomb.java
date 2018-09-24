package mp.pvzv2.game.model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CherryBomb extends Plant {
	public CherryBomb(int x, int y) {
		super(x, y);
		
		damage = 10;
		health = 2;
		range = 0;
		dirdamage = 250;
		speed = 1;
		cost = 150;
		regen = 45;
		
		try {
			img = ImageIO.read(new File("res\\cherrybomb.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		setvelX(0);
		setvelY(0);
	}
}