package mp.pvzv2.game.controller;

import java.time.Duration;
import java.time.LocalDateTime;

import mp.pvzv2.game.model.GameModel;

public class ButtonsController {
	public ButtonsController(GameModel model) {
		this.model = model;
	}
	
	public void tick() {
		LocalDateTime now = LocalDateTime.now();
		
		if (Duration.between(model.getInactiveSunflowerButton().getLastUpdateTime(), now).getSeconds() >= 5) {
			model.getInactiveSunflowerButton().setRegen(false);
			model.getInactiveSunflowerButton().setLastUpdateTime(now);
		} else if (Duration.between(model.getInactivePeashooterButton().getLastUpdateTime(), now).getSeconds() >= 5) {
			model.getInactivePeashooterButton().setRegen(false);
			model.getInactivePeashooterButton().setLastUpdateTime(now);
		} else if (Duration.between(model.getInactiveCherryBombButton().getLastUpdateTime(), now).getSeconds() >= 45) {
			model.getInactiveCherryBombButton().setRegen(false);
			model.getInactiveCherryBombButton().setLastUpdateTime(now);
		}
	}
	
	private GameModel model;
}