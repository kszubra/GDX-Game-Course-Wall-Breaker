package com.mygdx.game.entity;

import com.mygdx.game.util.entity.EntityBase;
import com.mygdx.game.util.shape.ShapeUtils;

public class Ball extends EntityBase {

    // == constructors ==
    public Ball() {
    }

    // == protected methods ==
    @Override
    protected float[] createVertices() {
        return ShapeUtils.createOctagon(
                width / 2f, // origin x or center r
                height / 2f, // origin y or center y
                width / 2f // radius
        );
    }
}
