package mp.pvzv2.game.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

public class PeashooterButton {
	public PeashooterButton() {
		lastUpdateTime = LocalDateTime.now();
		
		try {
			inactive = ImageIO.read(new File("res\\InPeashooterButton.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getInactive() {
		return inactive;
	}

	public LocalDateTime getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public boolean isRegen() {
		return isRegen;
	}
	
	public void setRegen(boolean isRegen) {
		this.isRegen = isRegen;
	}
	
	private BufferedImage inactive;
	private LocalDateTime lastUpdateTime;
	private boolean isRegen;
}
