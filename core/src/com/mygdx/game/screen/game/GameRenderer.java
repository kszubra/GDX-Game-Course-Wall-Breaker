package com.mygdx.game.screen.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.assets.AssetDescriptors;
import com.mygdx.game.assets.RegionNames;
import com.mygdx.game.config.GameConfig;
import com.mygdx.game.entity.Ball;
import com.mygdx.game.entity.Brick;
import com.mygdx.game.entity.Paddle;
import com.mygdx.game.entity.Pickup;
import com.mygdx.game.util.GdxUtils;
import com.mygdx.game.util.Validate;
import com.mygdx.game.util.ViewportUtils;
import com.mygdx.game.util.debug.DebugCameraController;
import com.mygdx.game.util.debug.ShapeRendererUtils;
import com.mygdx.game.util.entity.EntityBase;
import com.mygdx.game.util.parallax.ParallaxLayer;


public class GameRenderer implements Disposable {

    // == attributes ==
    private final GameWorld gameWorld;
    private final SpriteBatch batch;
    private final AssetManager assetManager;
    private final GlyphLayout layout = new GlyphLayout();

    private OrthographicCamera camera;
    private Viewport viewport;
    private Viewport hudViewport;
    private ShapeRenderer renderer;
    private DebugCameraController debugCameraController;

    private BitmapFont scoreFont;

    private TextureRegion backgroundRegion;
    private TextureRegion paddleRegion;
    private TextureRegion ballRegion;
    private TextureRegion brickRegion;

    private TextureRegion expandRegion;
    private TextureRegion shrinkRegion;
    private TextureRegion slowDownRegion;
    private TextureRegion speedUpRegion;

    // == constructors ==
    public GameRenderer(GameWorld gameWorld, SpriteBatch batch, AssetManager assetManager) {
        this.gameWorld = gameWorld;
        this.batch = batch;
        this.assetManager = assetManager;
        init();
    }

    // == init ==
    private void init() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);
        renderer = new ShapeRenderer();

        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);

        scoreFont = assetManager.get(AssetDescriptors.FONT);

        TextureAtlas gamePlayAtlas = assetManager.get(AssetDescriptors.GAME_PLAY);
        backgroundRegion = gamePlayAtlas.findRegion(RegionNames.BACKGROUND);
        paddleRegion = gamePlayAtlas.findRegion(RegionNames.PADDLE);
        ballRegion = gamePlayAtlas.findRegion(RegionNames.BALL);
        brickRegion = gamePlayAtlas.findRegion(RegionNames.BRICK);

        expandRegion = gamePlayAtlas.findRegion(RegionNames.EXPAND);
        shrinkRegion = gamePlayAtlas.findRegion(RegionNames.SHRINK);
        slowDownRegion = gamePlayAtlas.findRegion(RegionNames.SLOW_DOWN);
        speedUpRegion = gamePlayAtlas.findRegion(RegionNames.SPEED_UP);
    }

    // == public methods ==
    public void render(float delta) {
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        // clear screen
        GdxUtils.clearScreen();

        // render game play
        renderGamePlay();

        // render debug
        renderDebug();

        // render hud
        renderHud();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
        ViewportUtils.debugPixelsPerUnit(viewport);
    }

    public Vector2 screenToWorld(Vector2 screenCoordinates) {
        return viewport.unproject(screenCoordinates);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    // == private methods ==
    private void renderGamePlay() {
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        drawGamePlay();

        batch.end();
    }

    private void drawGamePlay() {
        // background
//        batch.draw(backgroundRegion, 0, 0, GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);
        ParallaxLayer background = gameWorld.getBackground();
        Rectangle firstRegionBounds = background.getFirstRegionBounds();
        Rectangle secondRegionBounds = background.getSecondRegionBounds();

        batch.draw(backgroundRegion, firstRegionBounds.x, firstRegionBounds.y,
                firstRegionBounds.width, firstRegionBounds.height);

        batch.draw(backgroundRegion, secondRegionBounds.x, secondRegionBounds.y,
                secondRegionBounds.width, secondRegionBounds.height);

        // paddle
        Paddle paddle = gameWorld.getPaddle();
        drawEntity(batch, paddleRegion, paddle);

        // ball
        Ball ball = gameWorld.getBall();
        batch.draw(ballRegion, ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());

        // bricks
        Array<Brick> bricks = gameWorld.getBricks();

        for (int i = 0; i < bricks.size; i++) {
            Brick brick = bricks.get(i);
            drawEntity(batch, brickRegion, brick);
        }

        // pickups
        Array<Pickup> pickups = gameWorld.getPickups();

        for (int i = 0; i < pickups.size; i++) {
            Pickup pickup = pickups.get(i);
            TextureRegion pickupRegion = findPickupRegion(pickup);
            drawEntity(batch, pickupRegion, pickup);
        }

        // effects
        Array<ParticleEffectPool.PooledEffect> effects = gameWorld.getEffects();

        for (int i = 0; i < effects.size; i++) {
            ParticleEffectPool.PooledEffect effect = effects.get(i);
            effect.draw(batch);
        }
    }

    private void renderDebug() {
        viewport.apply();

        if (gameWorld.isDrawGrid()) {
            ViewportUtils.drawGrid(viewport, renderer);
        }

        if (gameWorld.isDrawDebug()) {
            renderer.setProjectionMatrix(camera.combined);
            renderer.begin(ShapeRenderer.ShapeType.Line);

            drawDebug();

            renderer.end();
        }
    }

    private void drawDebug() {
        // save old color
        Color oldColor = renderer.getColor().cpy();
        renderer.setColor(Color.RED);

        // background
        ParallaxLayer background = gameWorld.getBackground();
        Rectangle first = background.getFirstRegionBounds();
        Rectangle second = background.getSecondRegionBounds();
        ShapeRendererUtils.rect(renderer, first);
        ShapeRendererUtils.rect(renderer, second);

        // paddle
        Polygon paddleBounds = gameWorld.getPaddle().getBounds();
        ShapeRendererUtils.polygon(renderer, paddleBounds);

        // brick
        for (Brick brick : gameWorld.getBricks()) {
            ShapeRendererUtils.polygon(renderer, brick.getBounds());
        }

        // ball
        Polygon ballBounds = gameWorld.getBall().getBounds();
        ShapeRendererUtils.polygon(renderer, ballBounds);

        // pickups
        Array<Pickup> pickups = gameWorld.getPickups();
        for (int i = 0; i < pickups.size; i++) {
            Pickup pickup = pickups.get(i);
            Polygon pickupBounds = pickup.getBounds();
            ShapeRendererUtils.polygon(renderer, pickupBounds);
        }

        // revert to old color
        renderer.setColor(oldColor);
    }

    private void renderHud() {
        hudViewport.apply();

        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.begin();

        drawHud();

        batch.end();
    }

    private void drawHud() {
        String scoreString = "SCORE: " + gameWorld.getScoreString();
        layout.setText(scoreFont, scoreString);
        scoreFont.draw(batch, layout,
                0, GameConfig.HUD_HEIGHT - layout.height);

//        String livesString = "LIVES: " + gameWorld.getLives();
//        layout.setText(scoreFont, livesString);
//        scoreFont.draw(batch, layout,
//                GameConfig.HUD_WIDTH - layout.width,
//                GameConfig.HUD_HEIGHT - layout.height);

        int lives = gameWorld.getLives();
        Color oldColor = batch.getColor().cpy();
        float offsetX = GameConfig.LIVES_START * (GameConfig.LIFE_HUD_WIDTH + GameConfig.LIFE_HUD_SPACING);
        float offsetY = 36f;
        float spacing = GameConfig.LIFE_HUD_WIDTH + GameConfig.LIFE_HUD_SPACING;

        float x = GameConfig.HUD_WIDTH - offsetX; // move left by offset
        float y = GameConfig.HUD_HEIGHT - offsetY; // move down by offset

        for (int i = 0; i < GameConfig.LIVES_START; i++) {
            if (lives <= i) {
                batch.setColor(1, 1, 1, 0.5f);
            }

            batch.draw(paddleRegion,
                    x + i * spacing, y,
                    GameConfig.LIFE_HUD_WIDTH, GameConfig.LIFE_HUD_HEIGHT
            );
        }

        batch.setColor(oldColor);
    }

    private TextureRegion findPickupRegion(Pickup pickup) {
        TextureRegion region = null;

        if (pickup.isExpand()) {
            region = expandRegion;
        } else if (pickup.isShrink()) {
            region = shrinkRegion;
        } else if (pickup.isSpeedUp()) {
            region = speedUpRegion;
        } else if (pickup.isSlowDown()) {
            region = slowDownRegion;
        }

        if (region == null) {
            throw new IllegalArgumentException("cant find region for pickupType= "
                    + pickup.getType());
        }

        return region;
    }

    // == static methods ==
    private static void drawEntity(SpriteBatch batch, TextureRegion region, EntityBase entity) {
        Validate.notNull(batch);
        Validate.notNull(region);
        Validate.notNull(entity);

        batch.draw(region, entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
    }
}
