package com.mygdx.game;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AssetPacker {

    // == constants ==
    private static final String RAW_ASSETS_PATH = "desktop/assets-raw";
    private static final String ASSETS_PATH = "android/assets";

    // == main ==
    public static void main(String[] args) {
        TexturePacker.process(
                RAW_ASSETS_PATH + "/gameplay",
                ASSETS_PATH + "/gameplay",
                "gameplay"
        );

        TexturePacker.process(
                RAW_ASSETS_PATH + "/ui",
                ASSETS_PATH + "/ui",
                "skin"
        );
    }

}
