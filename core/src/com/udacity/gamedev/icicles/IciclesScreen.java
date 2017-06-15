package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.udacity.gamedev.icicles.Constant.Difficulty;
import java.util.Set;
import static java.lang.Thread.getAllStackTraces;
import static sun.audio.AudioPlayer.player;

/**
 * Created by root on 15/04/17.
 */

public class IciclesScreen extends InputAdapter implements Screen {

    public static final String TAG = IciclesScreen.class.getName();

    ExtendViewport iciclesViewport;
    ShapeRenderer renderer;
    Icicle icicle;

    Player player;

    Icicles icicles;

    ScreenViewport hudViewport;
    SpriteBatch batch;
    BitmapFont font;

    int topScore;

    Difficulty difficulty;

    IciclesGame game;

    public IciclesScreen( IciclesGame game, Difficulty difficulty){
        this.difficulty = difficulty;
        this.game = game;

    }

    @Override
    public void show() {

        iciclesViewport = new ExtendViewport(Constant.WORLD_SIZE, Constant.WORLD_SIZE);
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        icicle = new Icicle(new Vector2(Constant.WORLD_SIZE / 2, Constant.WORLD_SIZE / 2));

        player = new Player(iciclesViewport);

        icicles = new Icicles(iciclesViewport, difficulty);

        hudViewport = new ScreenViewport();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        topScore = 0;
    }

    @Override
    public void resize(int width, int height) {

        iciclesViewport.update(width, height, true);

        player.init();

        icicles.init();

        hudViewport.update(width, height, true);
        font.getData().setScale(Math.min(width, height) / Constant.HUD_FONT_REFERENCE_SCREEN_SIZE );
    }

    @Override
    public void dispose() {
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }


    @Override
    public void render(float delta) {

        player.update(delta);
        icicles.update(delta);

        if (player.hitByIcicle(icicles)){
            icicles.init();
        }

        iciclesViewport.apply(true);
        Gdx.gl.glClearColor(Constant.BACKGROUND_COLOR.r, Constant.BACKGROUND_COLOR.g, Constant.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setProjectionMatrix(iciclesViewport.getCamera().combined);

        renderer.begin(ShapeType.Filled);
        icicles.render(renderer);


        player.render(renderer);
        renderer.end();


        topScore = Math.max(topScore, icicles.iciclesDodged);
        hudViewport.apply(true);
        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.begin();

        font.draw(batch, "Deaths: " + player.deaths + "\nDifficulty " + difficulty.label, Constant.HUD_MARGIN, hudViewport.getWorldHeight() - Constant.HUD_MARGIN);
        font.draw(batch, "Score: " + icicles.iciclesDodged + "\n Top Socre: " + topScore,
                hudViewport.getWorldWidth() - Constant.HUD_MARGIN, hudViewport.getWorldHeight() - Constant.HUD_MARGIN,
                0, Align.right, false);

        batch.end();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        renderer.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        game.showDifficultyScreen();
        return true;
    }
}
