package com.mygdx.game.util.shape;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.util.Validate;

public class RectangleUtils {

    // == constructors ==
    private RectangleUtils() {}

    // == public methods ==
    public static Vector2 getBottomLeft(Rectangle rectangle) {
        Validate.notNull(rectangle);

        return new Vector2(rectangle.x, rectangle.y);
    }
    public static Vector2 getBottomRight(Rectangle rectangle) {
        Validate.notNull(rectangle);

        return new Vector2(rectangle.x + rectangle.width, rectangle.y);
    }

    public static Vector2 getTopLeft(Rectangle rectangle) {
        Validate.notNull(rectangle);

        return new Vector2(rectangle.x, rectangle.y + rectangle.height);
    }

    public static Vector2 getTopRight(Rectangle rectangle) {
        Validate.notNull(rectangle);

        return new Vector2(rectangle.x + rectangle.width, rectangle.y + rectangle.height);
    }
}
