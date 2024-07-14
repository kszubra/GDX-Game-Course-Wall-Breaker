package com.mygdx.game.util.entity.script;

import com.mygdx.game.util.entity.EntityBase;

public interface EntityScript<T extends EntityBase> {

    void added(T entity);

    void removed(T removed);

    void update(float delta);

    boolean isFinished();
}
