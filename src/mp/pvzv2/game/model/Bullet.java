package mp.pvzv2.game.model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet extends PVZObject {
	public Bullet(int x, int y, int damage) {
		super(x, y);
		
		try {
			img = ImageIO.read(new File("res\\peabullet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setvelX(4);
		setvelY(0);
		
		setDamage(damage);
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	private int damage;
}
