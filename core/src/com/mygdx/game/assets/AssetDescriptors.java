package com.mygdx.game.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public final class AssetDescriptors {

    // == constants ==
    public static final AssetDescriptor<BitmapFont> FONT =
            new AssetDescriptor<BitmapFont>(AssetPaths.SCORE_FONT, BitmapFont.class);

    public static final AssetDescriptor<TextureAtlas> GAME_PLAY =
            new AssetDescriptor<TextureAtlas>(AssetPaths.GAME_PLAY, TextureAtlas.class);

    public static final AssetDescriptor<ParticleEffect> FIRE =
            new AssetDescriptor<ParticleEffect>(AssetPaths.FIRE, ParticleEffect.class);

    public static final AssetDescriptor<ParticleEffect> STAR =
            new AssetDescriptor<ParticleEffect>(AssetPaths.STAR, ParticleEffect.class);

    public static final AssetDescriptor<Sound> HIT =
            new AssetDescriptor<Sound>(AssetPaths.HIT, Sound.class);

    public static final AssetDescriptor<Sound> LOST =
            new AssetDescriptor<Sound>(AssetPaths.LOST, Sound.class);

    public static final AssetDescriptor<Sound> PICKUP =
            new AssetDescriptor<Sound>(AssetPaths.PICKUP, Sound.class);

    public static final AssetDescriptor<Skin> SKIN =
            new AssetDescriptor<Skin>(AssetPaths.SKIN, Skin.class);

    // all descriptors
    public static final Array<AssetDescriptor> ALL = new Array<AssetDescriptor>();

    // static init
    static {
        ALL.addAll(
                FONT,
                GAME_PLAY,
                FIRE,
                STAR,
                HIT,
                LOST,
                PICKUP,
                SKIN
        );
    }

    // == constructors ==
    private AssetDescriptors() {
    }
}
