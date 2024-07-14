package com.mygdx.game.util.screen.transition.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.mygdx.game.util.Direction;
import com.mygdx.game.util.GdxUtils;
import com.mygdx.game.util.Validate;
import com.mygdx.game.util.screen.transition.ScreenTransitionBase;

public class SlideScreenTransition extends ScreenTransitionBase {

    // == attributes ==
    private boolean slideIn;
    private Direction direction;
    private Interpolation interpolation;

    // == constructors ==
    public SlideScreenTransition(float duration) {
        this(duration, false);
    }

    public SlideScreenTransition(float duration, boolean slideIn) {
        this(duration, slideIn, Direction.LEFT);
    }

    public SlideScreenTransition(float duration, boolean slideIn, Direction direction) {
        this(duration, slideIn, direction, Interpolation.linear);
    }

    public SlideScreenTransition(float duration, boolean slideIn, Direction direction, Interpolation interpolation) {
        super(duration);

        Validate.notNull(direction, "direction is required.");
        Validate.notNull(interpolation, "interpolation is required.");

        this.slideIn = slideIn;
        this.direction = direction;
        this.interpolation = interpolation;
    }

    // == public methods ==
    @Override
    public void render(SpriteBatch batch, Texture currentScreenTexture, Texture nextScreenTexture, float percentage) {
        percentage = interpolation.apply(percentage);

        float x = 0;
        float y = 0;

        // drawing order depends on slide type (in or out)
        Texture bottomTexture = slideIn ? currentScreenTexture : nextScreenTexture;
        Texture topTexture = slideIn ? nextScreenTexture : currentScreenTexture;

        int bottomTextureWidth = bottomTexture.getWidth();
        int bottomTextureHeight = bottomTexture.getHeight();
        int topTextureWidth = topTexture.getWidth();
        int topTextureHeight = topTexture.getHeight();

        // calculate position offset
        if (direction.isHorizontal()) {
            float sign = direction.isLeft() ? -1 : 1; // sign always -1 or 1

            x = sign * topTextureWidth * percentage;

            if (slideIn) {
                sign = -sign; // reverse sign
                x += sign * topTextureWidth;
            }
        }

        if (direction.isVertical()) {
            float sign = direction.isUp() ? 1 : -1;

            y = sign * topTextureHeight * percentage;

            if (slideIn) {
                sign = -sign; // reverse sign
                y += sign * topTextureHeight;
            }
        }

        // drawing
        GdxUtils.clearScreen();
        batch.begin();

        // bottom texture
        batch.draw(bottomTexture,
                0, 0,
                0, 0,
                bottomTextureWidth, bottomTextureHeight,
                1, 1,
                0,
                0, 0,
                bottomTextureWidth, bottomTextureHeight,
                false, true
        );

        // top texture
        batch.draw(topTexture,
                x, y,
                0, 0,
                topTextureWidth, topTextureHeight,
                1, 1,
                0,
                0, 0,
                topTextureWidth, topTextureHeight,
                false, true
        );

        batch.end();
    }
}
