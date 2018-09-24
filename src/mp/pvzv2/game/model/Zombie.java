package mp.pvzv2.game.model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Zombie extends PVZObject {
	
	public Zombie(int x, int y) {
		super(x, y);
		
		speed = 4;
		damage = 10;
		health = 70;
		
		try {
			img = ImageIO.read(new File("res\\zombie.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setvelX(-1 * speed);
		setvelY(0);
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	protected int speed;
	protected int damage;
	protected int health;
}
