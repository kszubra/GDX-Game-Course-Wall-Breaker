package com.mygdx.game.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.BrickBreakerGame;

public class ScoreController {

    // == constants ==
    private static final String HIGH_SCORE_KEY = "highScore";

    // == attributes ==
    private final Preferences prefs;

    private int score;
    private int highScore;

    // == constructors ==
    public ScoreController() {
        prefs = Gdx.app.getPreferences(BrickBreakerGame.class.getSimpleName());
        highScore = prefs.getInteger(HIGH_SCORE_KEY, 0);
    }

    // == public methods ==
    public void reset() {
        score = 0;
    }

    public void addScore(int toAdd) {
        score += toAdd;
    }

    public void updateHighScore() {
        if(score < highScore) {
            return;
        }

        highScore = score;
        prefs.putInteger(HIGH_SCORE_KEY, highScore);
        prefs.flush();
    }

    public String getScoreString() {
        return Integer.toString(score);
    }

    public String getHighScoreString() {
        return Integer.toString(highScore);
    }

    // == private methods ==
}
