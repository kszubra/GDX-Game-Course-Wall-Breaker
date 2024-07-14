package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.config.GameConfig;
import com.mygdx.game.util.ads.NullAdController;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameConfig.WIDTH;
		config.height = GameConfig.HEIGHT;

		new LwjglApplication(new BrickBreakerGame(NullAdController.INSTANCE), config);
	}
}
