package com.example.julieglasdam.gameengine.NotFlappyBird;

import android.graphics.Bitmap;

import com.example.julieglasdam.gameengine.GameEngine;
import com.example.julieglasdam.gameengine.Screen;

/**
 * Created by julieglasdam on 07/11/2017.
 */

public class MainMenuScreen extends Screen {
    Bitmap mainMenu = null;
    Bitmap startGame = null;
    Bitmap title = null;


    public MainMenuScreen(GameEngine gameEngine) {
        super(gameEngine);
        mainMenu = gameEngine.loadBitmap("notflappybirdassets/mainmenu.png");
        startGame = gameEngine.loadBitmap("notflappybirdassets/startgame.png");
        title = gameEngine.loadBitmap("notflappybirdassets/title.png");
    }

    @Override
    public void update(float deltaTime) {
        // Check if user touches screen, and move on to game if they do
        if (gameEngine.isTouchDown(0)) {
            gameEngine.setScreen(new GameScreen(gameEngine));
            return;
        }
        // Draw the textures for the main menu screen
        gameEngine.drawBitMap(mainMenu, 0, 0);
        gameEngine.drawBitMap(title, 120, 90);
        gameEngine.drawBitMap(startGame, 190, 190);
     /*   passedTime = passedTime + deltaTime;
        if ((passedTime - (int)passedTime) > 0.5f) {
            gameEngine.drawBitMap(insertCoin, 169-(insertCoin.getWidth()/2), 350);
        }*/

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
