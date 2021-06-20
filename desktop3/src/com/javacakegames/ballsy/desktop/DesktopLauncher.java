package com.javacakegames.ballsy.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.HdpiMode;
import com.javacakegames.ballsy.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setWindowedMode(960, 540);
		config.disableAudio(true);
		config.setHdpiMode(HdpiMode.Pixels);
		config.setWindowIcon("icon.png");
		config.setInitialBackgroundColor(Color.RED);

		//Attempt to run at a million frames per second
		config.useVsync(false);
		config.setIdleFPS(0);

		new Lwjgl3Application(new Main(), config);

	}
}
