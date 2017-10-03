package com.example.julieglasdam.gameengine.Breakout;

import android.graphics.Bitmap;

import com.example.julieglasdam.gameengine.GameEngine;
import com.example.julieglasdam.gameengine.Screen;

/**
 * Created by julieglasdam on 03/10/2017.
 */

public class MainMenuScreen extends Screen {
    Bitmap mainMenu = null;
    Bitmap insertCoin = null;
    float passedTime = 0;
    long startTime = System.nanoTime();

    public MainMenuScreen(GameEngine gameEngine) {
        super(gameEngine);
        mainMenu = gameEngine.loadBitmap("breakoutassets/mainmenu.png");
        insertCoin = gameEngine.loadBitmap("breakoutassets/insertcoin.png");
    }

    @Override
    public void update(float deltaTime) {
        if (gameEngine.isTouchDown(0)) {
            gameEngine.setScreen(new GameScreen(gameEngine));
            return;
        }
        gameEngine.drawBitMap(mainMenu, 0, 0);
        passedTime = passedTime + deltaTime;
        if ((passedTime - (int)passedTime) > 0.5f) {
            gameEngine.drawBitMap(insertCoin, 169-(insertCoin.getWidth()/2), 350);
        }
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
