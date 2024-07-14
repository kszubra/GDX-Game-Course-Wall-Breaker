package com.mygdx.game.screen.loading;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.assets.AssetDescriptors;
import com.mygdx.game.config.GameConfig;
import com.mygdx.game.screen.menu.MenuScreen;
import com.mygdx.game.util.game.GameBase;
import com.mygdx.game.util.screen.loading.LoadingScreenBase;

public class LoadingScreen extends LoadingScreenBase {

    // == constructors ==
    public LoadingScreen(GameBase game) {
        super(game);
    }

    // == protected methods ==
    @Override
    protected Array<AssetDescriptor> getAssetDescriptors() {
        return AssetDescriptors.ALL;
    }

    @Override
    protected void onLoadDone() {
        game.setScreen(new MenuScreen(game));
    }

    @Override
    protected float getHudWidth() {
        return GameConfig.HUD_WIDTH;
    }

    @Override
    protected float getHudHeight() {
        return GameConfig.HUD_HEIGHT;
    }
}
