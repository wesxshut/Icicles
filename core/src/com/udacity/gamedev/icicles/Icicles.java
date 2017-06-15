package com.udacity.gamedev.icicles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.CountdownEventAction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.gamedev.icicles.Constant.Difficulty;


/**
 * Created by root on 15/04/17.
 */

public class Icicles {
    public static final String TAG = Icicles.class.getName();

    DelayedRemovalArray<Icicle> icicleList;
    Viewport viewport;

    int iciclesDodged;

    Difficulty difficulty;


    public Icicles(Viewport viewport, Difficulty difficulty) {
       this.difficulty = difficulty;
        this.viewport = viewport;
        init();
    }

    public void init(){
        icicleList = new DelayedRemovalArray<Icicle>(false, 100);
        iciclesDodged = 0;
    }

    public void update(float delta){

       if (MathUtils.random() < delta * difficulty.SpawnRate){
           Vector2 newIciclePosition = new Vector2(
                   MathUtils.random() * viewport.getWorldWidth(),
                   viewport.getWorldHeight()
           );
           Icicle newIcicle = new Icicle(newIciclePosition);
           icicleList.add(newIcicle);
       }
        for (Icicle icicle : icicleList){
            icicle.update(delta);
        }

        icicleList.begin();

        for (int i = 0; i < icicleList.size; i ++){
            if (icicleList.get(i).position.y < -Constant.ICICLE_HEIGHT){
                iciclesDodged += 1;
                icicleList.removeIndex(i);
            }
        }

        icicleList.end();

    }
    public void render(ShapeRenderer renderer){
        renderer.setColor(Constant.ICICLE_COLOR);

        for (Icicle icicle : icicleList){
            icicle.render(renderer);
        }
    }
}
