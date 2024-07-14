package com.mygdx.game.screen.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.BrickBreakerGame;
import com.mygdx.game.common.ScoreController;
import com.mygdx.game.common.SoundController;
import com.mygdx.game.entity.EntityFactory;
import com.mygdx.game.input.PaddleInputController;
import com.mygdx.game.screen.menu.MenuScreen;
import com.mygdx.game.screen.transition.ScreenTransitions;
import com.mygdx.game.util.game.GameBase;
import com.mygdx.game.util.screen.ScreenBaseAdapter;


public class GameScreen extends ScreenBaseAdapter {

    // == attributes ==
    private final GameBase game;
    private final AssetManager assetManager;
    private final SpriteBatch batch;
    private final ScoreController scoreController;

    private GameWorld gameWorld; // model
    private GameController controller; // controller
    private GameRenderer renderer; // view

    private EntityFactory factory;
    private PaddleInputController paddleInputController;
    private SoundController soundController;

    // == constructors ==
    public GameScreen(GameBase game) {
        this.game = game;
        assetManager = game.getAssetManager();
        batch = game.getBatch();
        scoreController = ((BrickBreakerGame) game).getScoreController();

    }

    // == public methods ==
    @Override
    public void show() {
        factory = new EntityFactory(assetManager);
        soundController = new SoundController(assetManager);

        gameWorld = new GameWorld(soundController, scoreController, factory);
        renderer = new GameRenderer(gameWorld, batch, assetManager);
        controller = new GameController(gameWorld, renderer);

        paddleInputController = new PaddleInputController(gameWorld.getPaddle(), controller);
    }

    @Override
    public void render(float delta) {

        boolean gameOver = gameWorld.isGameOver();

        if (!gameOver) {
            paddleInputController.update(delta);
        }

        controller.update(delta);
        renderer.render(delta);

        if (gameOver) {
            game.setScreen(new MenuScreen(game), ScreenTransitions.SLIDE);
        }
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
