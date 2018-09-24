package mp.pvzv2.game.model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Peashooter extends Plant {
	public Peashooter(int x, int y) {
		super(x, y);
		
		try {
			img = ImageIO.read(new File("res\\peashooter.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setvelX(0);
		setvelY(0);
	}
}