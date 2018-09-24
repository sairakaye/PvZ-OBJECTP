package mp.pvzv2.game.model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ZombieFlag extends Zombie {
	public ZombieFlag(int x, int y) {
		super(x, y);
		speed += 1;
		
		try {
			img = ImageIO.read(new File("res\\zombieflag.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setvelX(-1 * speed);
	}
}
