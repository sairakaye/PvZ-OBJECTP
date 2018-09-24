package mp.pvzv2.game.model;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class PVZObject {
	public LocalDateTime getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public PVZObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public BufferedImage getImage() {
		return img;
	}
	
	public void setvelX(int velX) {
		this.velX = velX;
	}
	
	public void setvelY(int velY) {
		this.velY = velY;
	}
	
	public int getvelX() {
		return velX;
	}
	
	public int getvelY() {
		return velY;
	}
	
	protected int x, y;
	protected int velX, velY;
	protected BufferedImage img;
	protected LocalDateTime lastUpdateTime;
}