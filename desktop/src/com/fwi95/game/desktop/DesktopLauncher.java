package com.fwi95.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fwi95.game.ShinyAdventure;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Shiny Adventure";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new ShinyAdventure(), config);
	}
}
