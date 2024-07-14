package com.mygdx.game.script;

import com.mygdx.game.config.GameConfig;
import com.mygdx.game.entity.Ball;
import com.mygdx.game.util.entity.script.EntityScriptBase;

public class BallSpeedUpScript extends EntityScriptBase<Ball> {

    // == attributes ==
    private float finalSpeed;

    // == public methods ==
    @Override
    public void added(Ball entity) {
        super.added(entity);

        float currentSpeed = entity.getSpeed();
        finalSpeed = currentSpeed + GameConfig.BALL_START_SPEED * GameConfig.BALL_SPEED_FACTOR;

        if(finalSpeed >= GameConfig.BALL_MAX_SPEED) {
            finalSpeed = GameConfig.BALL_MAX_SPEED;
        }
    }

    @Override
    public void update(float delta) {
        float angleDeg = entity.getAngleDeg();
        entity.setVelocity(angleDeg, finalSpeed);
        finish();
    }
}
