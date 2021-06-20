package com.javacakegames.ballsy;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public final class Inputs implements InputProcessor {

  private boolean fullScreen;
  private int oldWindowWidth;
  private int oldWindowHeight;

  @Override
  public boolean keyDown(int keycode) {
    if (
      keycode == Input.Keys.F11 ||
      (keycode == Input.Keys.ENTER || keycode == Input.Keys.NUMPAD_ENTER) &&
      (Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT) ||
       Gdx.input.isKeyPressed(Input.Keys.ALT_RIGHT))
    ) {
      if (
        !fullScreen &&
        Gdx.app.getType() != Application.ApplicationType.WebGL
      ) {
        oldWindowWidth = Gdx.graphics.getWidth();
        oldWindowHeight = Gdx.graphics.getHeight();
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        fullScreen = true;
      } else {
        Gdx.graphics.setWindowedMode(oldWindowWidth, oldWindowHeight);
        fullScreen = false;
      }
    }
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  @Override
  public boolean scrolled(float amountX, float amountY) {
    return false;
  }
}
