package com.example.julieglasdam.gameengine.NotFlappyBird;

import android.graphics.Bitmap;

import com.example.julieglasdam.gameengine.GameEngine;
import com.example.julieglasdam.gameengine.Screen;

/**
 * Created by julieglasdam on 07/11/2017.
 */

public class MainMenuScreen extends Screen {
    private int globalTime = 0;
    private Bitmap mainMenu = null;
    private Bitmap startGame = null;
    private Bitmap spritesheet = null;
    private Player player;


    public MainMenuScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        mainMenu = gameEngine.loadBitmap("notflappybirdassets/background.png");
        spritesheet = gameEngine.loadBitmap("notflappybirdassets/birdsprite.png");
        startGame = gameEngine.loadBitmap("notflappybirdassets/startgame.png");
        player = new Player(spritesheet, 100);
    }

    @Override
    public void update(float deltaTime)
    {
        globalTime++;

        // Change sprite for player
        if (globalTime % 8 == 0 && !player.getIsDead())
        {
            // Keep changing the sprite until the end of the spritesheet
            if (player.getAnimFrame() < player.getNumberOfSprites()-1) // Minus one because it should never be set to the actual number of sprites (0 indexing)
            {
                player.setAnimFrame();
            }
            else {
                player.setAnimFrame(0);
            }
        }

        // Check if user touches screen, and move on to game if they do
        if (gameEngine.isTouchDown(0))
        {
            gameEngine.setScreen(new GameScreen(gameEngine, player));
            return;
        }
        // Draw the textures for the main menu screen
        gameEngine.drawBitMap(mainMenu, 0, 0);
        gameEngine.drawBitMap(startGame, 190, 190);

        // Draw the player
        gameEngine.drawBitmap(
                spritesheet,
                player.getX(),                               // X coordinate for placing image on screen
                player.getY(),                               // Y coordinate for placing image on screen
                player.getAnimFrame()*player.getWIDTH(),     // Which part of the x axis to start drawing from
                0,                                           // Which part of the y axis to start drawing from
                player.getWIDTH(),                           // Width of image (from source specified above)
                player.getHEIGHT()                           // Height of image (from source specified above)
        );

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
