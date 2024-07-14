package com.mygdx.game;

import com.mygdx.game.common.ScoreController;
import com.mygdx.game.screen.loading.LoadingScreen;
import com.mygdx.game.util.ads.AdController;
import com.mygdx.game.util.game.GameBase;

public class BrickBreakerGame extends GameBase {

	// == attributes ==
	private ScoreController scoreController;

	// == constructors ==
	public BrickBreakerGame(AdController adController) {
		super(adController);
	}

	// == public methods ==
	@Override
	public void postCreate() {
		scoreController = new ScoreController();
		setScreen(new LoadingScreen(this));
	}

	public ScoreController getScoreController() {
		return scoreController;
	}
}
