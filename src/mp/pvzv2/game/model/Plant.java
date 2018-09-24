package mp.pvzv2.game.model;

public class Plant extends PVZObject {
	public Plant(int x, int y) {
		super(x, y);
		damage = 10;
		health = 40;
		range = 9;
		dirdamage = 12;
		speed = 2;
		cost = 100; 
		regen = 5;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getRange() {
		return range;
	}
	
	public int getdirDamage() {
		return dirdamage;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getCost() {
		return cost;
	}

	public int getRegen() {
		return regen;
	}	
	
	// to center the image of the plant when it is dragged.
	public void adjustPosition() {
		setX(getX() - getImage().getWidth() / 2);
		setY(getY() - getImage().getHeight() / 2);
	}
	
	protected int damage;
	protected int health;
	protected int range;
	protected int dirdamage;
	protected int speed;
	protected int cost;
	protected int regen;
}