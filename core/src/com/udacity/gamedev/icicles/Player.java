package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;
import static com.badlogic.gdx.Input.Keys.T;

/**
 * Created by root on 15/04/17.
 */

public class Player {
    public static final String TAG = Player.class.getName();

    Vector2 position;

    Viewport viewport;

    int deaths;

    public Player(Viewport viewport) {
        this.viewport = viewport;
        init();
        deaths = 0;
    }

    public void init() {
        position = new Vector2(viewport.getWorldWidth() / 2, Constant.PLAYER_HEAD_HEIGHT);
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(LEFT)) {
            position.x -= delta * Constant.PLAYER_MOVEMENT_SPEED;
        }
        if (Gdx.input.isKeyPressed(RIGHT)) {
            position.x += delta * Constant.PLAYER_MOVEMENT_SPEED;
        }

        float accelerometerInput = -Gdx.input.getAccelerometerY() / (Constant.ACCELERETION_GRAVITY * Constant.ACCELEROMETER_SENSITIVITY);
        position.x += - delta * accelerometerInput * Constant.PLAYER_MOVEMENT_SPEED;

                ensureInBounds();
    }

    private void ensureInBounds() {
        if (position.x - Constant.PLAYER_HEAD_RADIUS < 0)
            position.x =Constant.PLAYER_HEAD_HEIGHT;
        if (position.x + Constant.PLAYER_HEAD_RADIUS > viewport.getWorldWidth())
            position.x = viewport.getWorldWidth() - Constant.PLAYER_HEAD_RADIUS;

    }

    public boolean hitByIcicle(Icicles icicles){
        boolean isHit = false;
        for (Icicle icicle : icicles.icicleList){
            if (icicle.position.dst(position) < Constant.PLAYER_HEAD_RADIUS){
                isHit = true;

            }
        }
            if (isHit){
                deaths += 1;
            }
        return isHit;
    }

    public void render(ShapeRenderer renderer) {

        renderer.setColor(Constant.PLAYER_COLOR);
        renderer.set(ShapeType.Filled);
        renderer.circle(position.x, position.y, Constant.PLAYER_HEAD_RADIUS, Constant.PLAYER_HEAD_SEGMENTS);

        Vector2 torsoTop = new Vector2(position.x, position.y - Constant.PLAYER_HEAD_RADIUS);
        Vector2 torsoBottom = new Vector2(torsoTop.x, torsoTop.y - 2 * Constant.PLAYER_HEAD_RADIUS);

        renderer.rectLine(torsoTop, torsoBottom, Constant.PLAYER_LIMB_WIDTH);

        renderer.rectLine(
                torsoTop.x, torsoTop.y,
                torsoTop.x + Constant.PLAYER_HEAD_RADIUS, torsoTop.y - Constant.PLAYER_HEAD_RADIUS, Constant.PLAYER_LIMB_WIDTH);

        renderer.rectLine(
                torsoTop.x, torsoTop.y,
                torsoTop.x - Constant.PLAYER_HEAD_RADIUS, torsoTop.y - Constant.PLAYER_HEAD_RADIUS, Constant.PLAYER_LIMB_WIDTH);

        renderer.rectLine(
                torsoBottom.x, torsoBottom.y,
                torsoBottom.x + Constant.PLAYER_HEAD_RADIUS, torsoBottom.y - Constant.PLAYER_HEAD_RADIUS, Constant.PLAYER_LIMB_WIDTH);

        renderer.rectLine(
                torsoBottom.x, torsoBottom.y,
                torsoBottom.x - Constant.PLAYER_HEAD_RADIUS, torsoBottom.y - Constant.PLAYER_HEAD_RADIUS, Constant.PLAYER_LIMB_WIDTH);


    }
}
