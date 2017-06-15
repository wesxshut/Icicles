package com.udacity.gamedev.icicles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by root on 15/04/17.
 */

public class Constant {

    public static final float WORLD_SIZE = 10.0f;
    public static final Color BACKGROUND_COLOR = Color.BLUE;

    public static final float PLAYER_HEAD_RADIUS = 0.5f;
    public static final float PLAYER_HEAD_HEIGHT = 4.0f * PLAYER_HEAD_RADIUS;
    public static final float PLAYER_LIMB_WIDTH = 0.1f;
    public static final int PLAYER_HEAD_SEGMENTS = 20;
    public static final Color PLAYER_COLOR = Color.BLACK;

    public static final float ICICLE_WIDTH = 0.5f;
    public static final float ICICLE_HEIGHT = 1.0f;
    public static final Color ICICLE_COLOR = Color.WHITE;
    public static final Vector2  ICICLE_ACCELERATION = new Vector2(0, -5.0f);
    public static final float ICICLE_SPAWNS_PER_SECOND = 10.0f;

    public static final float PLAYER_MOVEMENT_SPEED = 10.0f;

    public static final float ACCELEROMETER_SENSITIVITY = 0.5f;
    public static final float ACCELERETION_GRAVITY = 9.8f;

    public static final float HUD_FONT_REFERENCE_SCREEN_SIZE  = 480.0f;
    public static final float HUD_MARGIN            =    20.0f;

    public static final String EASY_LABEL = "Cold";
    public static final String MEDIUM_LABEL = "Colder";
    public static final String HARD_LABEL = "Coldest";

    public static final Color COLD = Color.valueOf("#0d47a1");
    public static final Color COLDER = Color.valueOf("#2196f3");
    public static final Color COLDEST = Color.valueOf("#bbdefb");
    public static final float DIFFICULTY_WORLD = 480.0f;
    public static final float DIFFICULTY_RADIUS = DIFFICULTY_WORLD / 9;
    public static final float DIFFICULTY_LABEL = 1.5f;
    public static final Vector2 EASY_CENTER = new Vector2(DIFFICULTY_WORLD / 4,DIFFICULTY_WORLD / 2 );
    public static final Vector2 MEDIUM_CENTER = new Vector2(DIFFICULTY_WORLD / 2, DIFFICULTY_WORLD / 2);
    public static final Vector2 HARD_CENTER = new Vector2(DIFFICULTY_WORLD   * 3/4,DIFFICULTY_WORLD / 2);


    public static final float EASY_SPAWNS_PER_SECOND = 5;
    public static final float MEDIUM_SPAWNS_PER_SECOND = 15;
    public static final float HARD_SPAWNS_PER_SECOND = 25;


    public  enum Difficulty{
        EASY (EASY_SPAWNS_PER_SECOND, EASY_LABEL),
        MEDIUM(MEDIUM_SPAWNS_PER_SECOND, MEDIUM_LABEL),
        HARD(HARD_SPAWNS_PER_SECOND, HARD_LABEL);

        float SpawnRate;
        String label;

        Difficulty (float SpawnRate, String label){
            this.SpawnRate = SpawnRate;
            this.label = label;
        }
    }


}

