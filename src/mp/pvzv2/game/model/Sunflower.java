package mp.pvzv2.game.model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sunflower extends Plant {
	public Sunflower(int x, int y) {
		super(x, y);
		damage = 0;
		range = 0;
		dirdamage = 0;
		speed = 10;
		cost = 50;
		regen = 5;
		
		try {
			img = ImageIO.read(new File("res\\sunflower.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setvelX(0);
		setvelY(0);
	}
}