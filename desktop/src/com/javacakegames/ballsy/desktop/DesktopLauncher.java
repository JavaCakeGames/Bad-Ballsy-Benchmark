package com.javacakegames.ballsy.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.HdpiMode;
import com.javacakegames.ballsy.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		//It's a WIDESCREEN experience!
		config.width = 960;
		config.height = 540;

		//Attempt to run at a million frames per second
		config.vSyncEnabled = false;
		config.foregroundFPS = 0;
		config.backgroundFPS = 0;

		//Run on potatoes
		config.allowSoftwareMode = true;
		LwjglApplicationConfiguration.disableAudio = true;

		//Fix stupid defaults
		config.initialBackgroundColor = Color.RED;
		config.forceExit = false;
		config.useHDPI = true;

		//Doesn't work on Windows, maybe because it's high-res?
		config.addIcon("icon.png", Files.FileType.Internal);

		new LwjglApplication(new Main(), config);

	}
}
