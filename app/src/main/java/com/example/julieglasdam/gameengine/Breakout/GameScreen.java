package com.example.julieglasdam.gameengine.Breakout;

import android.graphics.Bitmap;

import com.example.julieglasdam.gameengine.GameEngine;
import com.example.julieglasdam.gameengine.Screen;

/**
 * Created by julieglasdam on 03/10/2017.
 */

public class GameScreen extends Screen{
    Bitmap background = null;

    public GameScreen(GameEngine gameEngine) {
        super(gameEngine);
        background = gameEngine.loadBitmap("breakoutassets/background.png");
    }
    @Override
    public void update(float deltaTime) {
        gameEngine.drawBitMap(background, 0, 0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
