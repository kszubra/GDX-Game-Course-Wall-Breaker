package com.mygdx.game.screen.transition;

import com.badlogic.gdx.math.Interpolation;
import com.mygdx.game.util.Direction;
import com.mygdx.game.util.screen.transition.ScreenTransition;
import com.mygdx.game.util.screen.transition.impl.FadeScreenTransition;
import com.mygdx.game.util.screen.transition.impl.ScaleScreenTransition;
import com.mygdx.game.util.screen.transition.impl.SlideScreenTransition;

public final class ScreenTransitions {

    public static final ScreenTransition FADE = new FadeScreenTransition(1.5f);

    public static final ScreenTransition SCALE = new ScaleScreenTransition(1.5f, false, Interpolation.swingIn);

    public static final ScreenTransition SLIDE = new SlideScreenTransition(2f, true, Direction.DOWN, Interpolation.bounceOut);

    // == constructors ==
    private ScreenTransitions() {
    }
}
