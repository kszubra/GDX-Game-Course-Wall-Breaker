package com.mygdx.game.util.parallax;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.util.Direction;

public class ParallaxLayer {

    // == attributes ==
    private final Rectangle firstRegionBounds;
    private final Rectangle secondRegionBounds;

    private float speed = 1f;
    private Direction direction = Direction.LEFT;

    private float startX;
    private float startY;

    private float width = 1;
    private float height = 1;

    // == constructors ==
    public ParallaxLayer() {
        firstRegionBounds = new Rectangle(startX, startY, width, height);
        secondRegionBounds = new Rectangle(startX + width, startY, width, height);
    }

    // == public methods ==
    public void update(float delta) {
        if (boundsReached(delta)) {
            resetBounds();
            return;
        }

        float velocity = delta * speed;

        if (direction.isLeft()) {
            firstRegionBounds.x -= velocity;
            secondRegionBounds.x -= velocity;
        } else if (direction.isRight()) {
            firstRegionBounds.x += velocity;
            secondRegionBounds.x += velocity;
        } else if (direction.isUp()) {
            firstRegionBounds.y += velocity;
            secondRegionBounds.y += velocity;
        } else if (direction.isDown()) {
            firstRegionBounds.y -= velocity;
            secondRegionBounds.y -= velocity;
        }
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setStartPosition(float startX, float startY) {
        this.startX = startX;
        this.startY = startY;
        updateBounds();
    }

    public Rectangle getFirstRegionBounds() {
        return firstRegionBounds;
    }

    public Rectangle getSecondRegionBounds() {
        return secondRegionBounds;
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        updateBounds();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        updateBounds();
    }

    // == private methods ==
    private void updateBounds() {
        // assume left since left is default
        firstRegionBounds.set(startX, startY, width, height);
        secondRegionBounds.set(startX + width, startY, width, height);

        if (direction.isRight()) {
            secondRegionBounds.set(startX - width, startY, width, height);
        } else if (direction.isUp()) {
            secondRegionBounds.set(startX, startY - height, width, height);
        } else if (direction.isDown()) {
            secondRegionBounds.set(startX, startY + height, width, height);
        }
    }

    private boolean boundsReached(float delta) {
        float velocity = speed * delta;

        if (direction.isRight()) {
            float nextPos = secondRegionBounds.x + velocity;
            return nextPos >= 0f;
        } else if (direction.isUp()) {
            float nextPos = secondRegionBounds.y + velocity;
            return nextPos >= 0f;
        } else if (direction.isDown()) {
            float nextPos = secondRegionBounds.y - velocity;
            return nextPos <= 0f;
        }

        // left
        float nextPos = secondRegionBounds.x - velocity;
        return nextPos <= 0f;
    }

    private void resetBounds() {
        // assume left since left is default
        firstRegionBounds.setPosition(startX, startY);
        secondRegionBounds.setPosition(width, startY);

        if (direction.isRight()) {
            secondRegionBounds.setPosition(-width, startY);
        } else if (direction.isUp()) {
            secondRegionBounds.setPosition(startX, -height);
        } else if (direction.isDown()) {
            secondRegionBounds.setPosition(startX, height);
        }
    }
}
