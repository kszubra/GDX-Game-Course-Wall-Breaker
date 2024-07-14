package com.mygdx.game.config;

public final class GameConfig {

    // == constants ==
    // NOTE: DESKTOP ONLY!
    public static final int WIDTH = 1024; // pixels
    public static final int HEIGHT = 768; // pixels

    public static final float HUD_WIDTH = 1024f; // world units
    public static final float HUD_HEIGHT = 768f; // world units

    public static final float WORLD_WIDTH = 32f; // world units
    public static final float WORLD_HEIGHT = 24f; // world units

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2f; // world units
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2f; // world units

    public static final float PADDLE_MIN_WIDTH = 1.2f;
    public static final float PADDLE_START_WIDTH = 3f; // world units
    public static final float PADDLE_MAX_WIDTH = 4.8f;
    public static final float PADDLE_HEIGHT = 1f; // world units
    public static final float PADDLE_START_X = (WORLD_WIDTH - PADDLE_START_WIDTH) / 2f; // world units
    public static final float PADDLE_START_Y = 1f; // world units
    public static final float PADDLE_VELOCITY_X = 15f; // world units
    public static final float PADDLE_RESIZE_FACTOR = 0.15f; // percentage
    public static final float PADDLE_EXPAND_SHRINK_SPEED = 6f;

    public static final float BRICK_WIDTH = 2.125f; // world units
    public static final float BRICK_HEIGHT = 1f; // world units

    public static final float LEFT_PAD = 0.5f; // world units
    public static final float TOP_PAD = 2.5f; // world units

    public static final float COLUMN_SPACING = 0.5f; // world units
    public static final int COLUMN_COUNT = 12; // count

    public static final float ROW_SPACING = 0.5f; // world units
    public static final int ROW_COUNT = 6; // count

    public static final float BALL_SIZE = 0.8f; // world units
    public static final float BALL_HALF_SIZE = BALL_SIZE / 2f;
    public static final float BALL_START_X = PADDLE_START_X + (PADDLE_START_WIDTH - BALL_SIZE) / 2f;
    public static final float BALL_START_Y = PADDLE_START_Y + PADDLE_HEIGHT;
    public static final float BALL_MIN_SPEED = 10f;
    public static final float BALL_START_SPEED = 16f;
    public static final float BALL_MAX_SPEED = 22f;
    public static final float BALL_SPEED_FACTOR = 0.15f; // 15%
    public static final float BALL_START_ANGLE = 60f; // degrees

    public static final int BRICK_SCORE = 10; // score

    public static final float PICKUP_SIZE = 1f; // world units
    public static final float PICKUP_SPAWN_TIME = 2f;
    public static final float PICKUP_VELOCITY_Y = -6f; // gravity

    public static final int LIVES_START = 3;

    public static final float LIFE_HUD_WIDTH = 40f; // world units (hud)
    public static final float LIFE_HUD_HEIGHT = 12f; // world units (hud)
    public static final float LIFE_HUD_SPACING = 10f; // world units (hud)

    public static final float BACKGROUND_SPEED = 1f;

    // == constructors ==
    private GameConfig() {
    }
}
