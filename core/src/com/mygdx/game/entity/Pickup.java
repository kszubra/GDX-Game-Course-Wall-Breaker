package com.mygdx.game.entity;

import com.badlogic.gdx.utils.Pool;
import com.mygdx.game.util.entity.EntityBase;

public class Pickup extends EntityBase implements Pool.Poolable {

    // == attributes ==
    private PickupType type;

    // == constructors ==
    public Pickup() {
        type = PickupType.random();
    }

    // == public methods ==
    public void setType(PickupType type) {
        this.type = type;
    }


    public boolean isExpand() {
        return type.isExpand();
    }

    public boolean isShrink() {
        return type.isShrink();
    }

    public boolean isSlowDown() {
        return type.isSlowDown();
    }

    public boolean isSpeedUp() {
        return type.isSpeedUp();
    }


    public PickupType getType() {
        return type;
    }

    @Override
    public void reset() {
        type = null;
        velocity.setZero();
    }

}
