package com.javacakegames.ballsy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import text.formic.Stringf;

public final class Main extends ApplicationAdapter {

  private SpriteBatch batch;

  private OrthographicCamera camera;
  private Viewport viewport;

  private final ArrayList<Ball> balls = new ArrayList<>();

  private final Vector2 boundaries = new Vector2();
  private Vector2 windowShrinkage = new Vector2();

  private int ballSize;

  private BitmapFont bitmapFont;

  private TextureRegion ballTextureRegion;

  @Override
  public final void create () {

    Inputs inputProcessor = new Inputs();
    Gdx.input.setInputProcessor(inputProcessor);

    //A bigger batch size should mean better frame rates
    batch = new SpriteBatch(8191); //This is as big as it can be

    //We only need one texture and it'll be a sure pretty one
    Texture texture = new Texture("font.png");
    texture.setFilter(Texture.TextureFilter.Linear,
                      Texture.TextureFilter.Linear);
    TextureRegion fontTextureRegion = new TextureRegion(texture);

    //The ball is in the bottom-right of the font texture to reduce switches
    ballTextureRegion = new TextureRegion(texture, 488, 104, 24, 24);
    ballSize = ballTextureRegion.getRegionWidth();

    bitmapFont = new BitmapFont(Gdx.files.internal("font.fnt"),
                                fontTextureRegion);
    bitmapFont.setUseIntegerPositions(false);

    camera = new OrthographicCamera();
    camera.update();

    viewport = new ScreenViewport(camera);
    viewport.apply();

    batch.setProjectionMatrix(camera.combined);

  }

  @Override
  public final void render () {

    ScreenUtils.clear(Color.RED); //Agh, my eyes!

    //Gdx.graphics.getFramesPerSecond() is averaged so we do this instead
    float fps = 1 / Gdx.graphics.getDeltaTime();

    //Add balls when over 30fps
    //but not on the first frame when we have infinite fps
    if (fps != Float.POSITIVE_INFINITY && fps > 30) {
      for (int i = 0; i < Math.ceil(fps) - 30; i++) {
        balls.add(new Ball(ballSize));
      }
    }

    batch.begin();

    //Rendering before updating stops the most-recently-spawned ball from
    //appearing to shake around
    for(Ball ball : balls) {
      ball.render(batch, ballTextureRegion);
      ball.update(boundaries, windowShrinkage);
    }

    //Only people with big windows deserve text
    if (
      Gdx.graphics.getBackBufferWidth() > 512 &&
      Gdx.graphics.getBackBufferHeight() > 256
    ) {
      bitmapFont.draw(
        batch,
        "Balls: " + Stringf.format("%,11d", balls.size()).trim() +
        "\nFPS: " + Math.round(fps * 10) / 10f,
        -boundaries.x + 10, boundaries.y - 10
      );
    }

    Gdx.graphics.setTitle(
      "Balls: " + Stringf.format("%,11d", balls.size()).trim() +
      "; FPS: " + Math.round(fps * 10) / 10f + " - Bad Ballsy Benchmark"
    );

    batch.end();

    windowShrinkage.setZero();

  }

  public final void resize(int width, int height) {

    viewport.update(width, height);
    batch.setProjectionMatrix(camera.combined);

    windowShrinkage = new Vector2(boundaries);
    boundaries.x = width / 2f;
    boundaries.y = height / 2f;
    windowShrinkage.sub(boundaries);

  }

  @Override
  public void dispose () {
    batch.dispose();
  }
}
