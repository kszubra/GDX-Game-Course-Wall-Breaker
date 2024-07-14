package com.mygdx.game.util.screen.transition;

public abstract class ScreenTransitionBase implements ScreenTransition {

    // == attributes ==
    protected final float duration;

    // == constructors ==
    public ScreenTransitionBase(float duration) {
        this.duration = duration;
    }

    // == public methods ==
    @Override
    public float getDuration() {
        return duration;
    }
}
