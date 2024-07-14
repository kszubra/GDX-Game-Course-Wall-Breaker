package com.mygdx.game.script;

import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.config.GameConfig;
import com.mygdx.game.entity.Ball;
import com.mygdx.game.util.entity.script.EntityScriptBase;


public class BallSlowDownScript extends EntityScriptBase<Ball> {

    // == constants ==
    private static final Logger log = new Logger(BallSlowDownScript.class.getName(), Logger.DEBUG);

    // == attributes ==
    private float finalSpeed;

    // == public methods ==
    @Override
    public void added(Ball entity) {
        super.added(entity);

        float currentSpeed = entity.getSpeed();
        finalSpeed = currentSpeed - GameConfig.BALL_START_SPEED * GameConfig.BALL_SPEED_FACTOR;

        if (finalSpeed <= GameConfig.BALL_MIN_SPEED) {
            finalSpeed = GameConfig.BALL_MIN_SPEED;
        }
    }

    @Override
    public void update(float delta) {
        float angleDeg = entity.getAngleDeg();

        log.debug("speed before update= " + entity.getSpeed());

        entity.setVelocity(angleDeg, finalSpeed);

        log.debug("speed after update= " + entity.getSpeed());

        finish();
    }
}
