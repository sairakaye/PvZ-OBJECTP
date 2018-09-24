package mp.pvzv2.game;

import mp.pvzv2.game.view.GameView;

public class PVZDriver {
	public static void main(String[] args) {
		GameView view = new GameView("Plants vs Zombies - MP", 1600, 900);
		view.show();
	}
}
