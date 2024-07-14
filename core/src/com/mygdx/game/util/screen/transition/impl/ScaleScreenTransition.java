package com.mygdx.game.util.screen.transition.impl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.mygdx.game.util.GdxUtils;
import com.mygdx.game.util.Validate;
import com.mygdx.game.util.screen.transition.ScreenTransitionBase;


public class ScaleScreenTransition extends ScreenTransitionBase {

    // == attributes ==
    private boolean scaleOut;
    private Interpolation interpolation;

    // == constructors ==
    public ScaleScreenTransition(float duration) {
        this(duration, false);
    }

    public ScaleScreenTransition(float duration, boolean scaleOut) {
        this(duration, scaleOut, Interpolation.linear);
    }

    public ScaleScreenTransition(float duration, boolean scaleOut, Interpolation interpolation) {
        super(duration);

        Validate.notNull(interpolation, "interpolation is required");

        this.scaleOut = scaleOut;
        this.interpolation = interpolation;
    }

    // == public methods ==
    @Override
    public void render(SpriteBatch batch, Texture currentScreenTexture, Texture nextScreenTexture, float percentage) {
        // interpolate percentage
        percentage = interpolation.apply(percentage);

        // assume scale out is false (e.g. scaling in)
        float scale = 1 - percentage;

        if (scaleOut) {
            scale = percentage;
        }

        // drawing order depends on scale type (in or out)
        Texture topTexture = scaleOut ? nextScreenTexture : currentScreenTexture;
        Texture bottomTexture = scaleOut ? currentScreenTexture : nextScreenTexture;

        int topTextureWidth = topTexture.getWidth();
        int topTextureHeight = topTexture.getHeight();
        int bottomTextureWidth = bottomTexture.getWidth();
        int bottomTextureHeight = bottomTexture.getHeight();

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
                0, 0,
                topTextureWidth / 2f, topTextureHeight / 2f,
                topTextureWidth, topTextureHeight,
                scale, scale,
                0,
                0, 0,
                topTextureWidth, topTextureHeight,
                false, true
        );

        batch.end();
    }
}
