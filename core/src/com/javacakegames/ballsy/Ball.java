package com.javacakegames.ballsy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public final class Ball {

  private final Vector2 position;
  private final Vector2 velocity = new Vector2();
  private float angle;
  private final int size;

  public Ball(int size) {
    position = new Vector2(-size / 2f, -size / 2f);
    this.size = size;
    angle = MathUtils.random(MathUtils.PI2);
    calculateVelocity(angle);
  }

  public void update(Vector2 boundaries, Vector2 windowShrinkage) {

    //When at window edges, bounce!
    if (position.x <= -boundaries.x || position.x >= boundaries.x - size) {
      angle = (MathUtils.PI - angle);
      calculateVelocity(angle);
      if (windowShrinkage.x > 0) {
        position.x += (position.x < 0) ? windowShrinkage.x : -windowShrinkage.x;
      }
      position.x += velocity.x / 100; //prevent them getting stuck
    }

    if (position.y <= -boundaries.y || position.y >= boundaries.y - size) {
      angle = (MathUtils.PI2 - angle);
      calculateVelocity(angle);
      if (windowShrinkage.y > 0) {
        position.y += (position.y < 0) ? windowShrinkage.y : -windowShrinkage.y;
      }
      position.y += velocity.y / 100;
    }

    position.add(velocity);

  }

  public void render(SpriteBatch batch, TextureRegion textureRegion) {
    batch.draw(textureRegion, position.x, position.y);
  }

  private void calculateVelocity(float angle) {
    velocity.x = 3 * MathUtils.cos(angle);
    velocity.y = 3 * MathUtils.sin(angle);
  }

}
