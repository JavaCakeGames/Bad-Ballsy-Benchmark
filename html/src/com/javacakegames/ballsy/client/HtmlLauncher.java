package com.javacakegames.ballsy.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.javacakegames.ballsy.Main;

public class HtmlLauncher extends GwtApplication {

  @Override
  public GwtApplicationConfiguration getConfig () {
    // Resizable application, uses available space in browser
    GwtApplicationConfiguration config = new GwtApplicationConfiguration(true);
    config.disableAudio = true;
    config.padHorizontal = 0;
    config.padVertical = 0;
    return config;
  }

  @Override
  public ApplicationListener createApplicationListener () {
    return new Main();
  }
}