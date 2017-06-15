package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.udacity.gamedev.icicles.Constant.Difficulty;
import java.awt.Color;

import static com.badlogic.gdx.Input.Keys.C;


/**
 * Created by root on 15/04/17.
 */

public class DifficultyScreen extends InputAdapter implements Screen {

    public static final String TAG = DifficultyScreen.class.getName();
    IciclesGame game;
    ShapeRenderer renderer;
    SpriteBatch batch;
    FitViewport viewport;
    BitmapFont font;

    public DifficultyScreen(IciclesGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        viewport = new FitViewport(Constant.DIFFICULTY_WORLD, Constant.DIFFICULTY_WORLD);
        Gdx.input.setInputProcessor(this);
        font = new BitmapFont();
        font.getData().setScale(Constant.DIFFICULTY_LABEL);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(Constant.BACKGROUND_COLOR.r, Constant.BACKGROUND_COLOR.g, Constant.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(Constant.COLD);
        renderer.circle(Constant.EASY_CENTER.x, Constant.EASY_CENTER.y,Constant.DIFFICULTY_RADIUS );

        renderer.setColor(Constant.COLDER);
        renderer.circle(Constant.MEDIUM_CENTER.x, Constant.MEDIUM_CENTER.y, Constant.DIFFICULTY_RADIUS);

        renderer.setColor(Constant.COLDEST);
        renderer.circle(Constant.HARD_CENTER.x,Constant.HARD_CENTER.y, Constant.DIFFICULTY_RADIUS);

        renderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        final GlyphLayout easyLayout = new GlyphLayout(font, Constant.EASY_LABEL);
        font.draw(batch, Constant.EASY_LABEL, Constant.EASY_CENTER.x, Constant.EASY_CENTER.y + easyLayout.height / 2, 0, Align.center, false);

        final GlyphLayout mediumLayout = new GlyphLayout(font, Constant.MEDIUM_LABEL);
        font.draw(batch, Constant.MEDIUM_LABEL, Constant.MEDIUM_CENTER.x, Constant.MEDIUM_CENTER.y + mediumLayout.height / 2, 0, Align.center, false );

        final GlyphLayout hardLayout = new GlyphLayout(font, Constant.HARD_LABEL);
        font.draw(batch, Constant.HARD_LABEL, Constant.HARD_CENTER.x, Constant.HARD_CENTER.y + hardLayout.height /2, 0, Align.center, false );

        batch.end();
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        batch.dispose();
        font.dispose();
        renderer.dispose();
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){

       Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

        if (worldTouch.dst(Constant.EASY_CENTER) < Constant.DIFFICULTY_RADIUS){
            game.showIciclesScreen(Difficulty.EASY);
        }
        if (worldTouch.dst(Constant.MEDIUM_CENTER) < Constant.DIFFICULTY_RADIUS){
            game.showIciclesScreen(Difficulty.MEDIUM);
        }
        if (worldTouch.dst(Constant.HARD_CENTER) < Constant.DIFFICULTY_RADIUS){
            game.showIciclesScreen(Difficulty.HARD);
        }
        return true;
    }
}
