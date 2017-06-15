package com.udacity.gamedev.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by root on 15/04/17.
 */

public class Icicle {

    public static final String TAG = Icicle.class.getName();

    Vector2 position;
    Vector2 velocity;

    public Icicle(Vector2 position) {

        this.position = position;
        this.velocity = new Vector2();
    }

    public void update(float delta){
       velocity.mulAdd(Constant.ICICLE_ACCELERATION, delta);
        position.mulAdd(velocity, delta);
    }

    public void render(ShapeRenderer renderer) {


        renderer.setColor(Constant.ICICLE_COLOR);

        renderer.set(ShapeType.Filled);

        renderer.triangle(
                position.x, position.y,
                position.x - Constant.ICICLE_WIDTH / 2, position.y + Constant.ICICLE_HEIGHT,
                position.x + Constant.ICICLE_WIDTH / 2, position.y + Constant.ICICLE_HEIGHT
        );
    }

}
