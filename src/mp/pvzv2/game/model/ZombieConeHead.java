package mp.pvzv2.game.model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ZombieConeHead extends Zombie {
	public ZombieConeHead(int x, int y) {
		super(x, y);
		health += 18;
		speed = 3;
		damage -= 2;
		
		try {
			img = ImageIO.read(new File("res\\zombieconehead.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setvelX(-1 * speed);
	}
}
