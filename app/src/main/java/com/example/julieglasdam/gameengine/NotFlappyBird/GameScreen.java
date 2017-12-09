package com.example.julieglasdam.gameengine.NotFlappyBird;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.Log;

import com.example.julieglasdam.gameengine.GameEngine;
import com.example.julieglasdam.gameengine.Screen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julieglasdam on 11/11/2017.
 */



public class GameScreen extends Screen
{
    private int globalTime = 0;
    private int deathCountdown = 0;
    private Bitmap background = null;

    private Player player;
    private Typeface font = null;
    private World world;

    private boolean isPaused = false;


    public GameScreen(GameEngine gameEngine, Player player)
    {
        super(gameEngine);
        this.player = player;
        background = gameEngine.loadBitmap("notflappybirdassets/background.png");
        gameEngine.loadFont("notflappybirdassets/Square.ttf");
        world = new World(gameEngine, player);
        world.createWorld();
    }

    // Graphics that needs to be updated during the game
    @Override
    public void update(float deltaTime)
    {
        globalTime++;

        // Change sprite for player
        if (globalTime % 8 == 0 && !player.getIsDead() && !isPaused)
        {
            // Keep changing the sprite until the end of the spritesheet
            if (player.getAnimFrame() < player.getNumberOfSprites()-1) // Minus one because it should never be set to the actual number of sprites (0 indexing)
            {
                player.setAnimFrame();
            }
            else
            {
                player.setAnimFrame(0);
            }
        }


        // Draw the background
        gameEngine.drawBitMap(background, 0, 0);


        // Draw the ledges. Needs to be updated because x positions keeps changing
        int size = world.ledges.size();

        for (int i = 0; i < size; i++)
        {
            drawLedge(world.ledges.get(i));
        }

        // Draw the player
        gameEngine.drawBitmap(
                            player.getSpritesheet(),
                            player.getX(),                               // X coordinate for placing image on screen
                            player.getY(),                               // Y coordinate for placing image on screen
                            player.getAnimFrame()*player.getWIDTH(),     // Which part of the x axis to start drawing from
                            0,                                           // Which part of the y axis to start drawing from
                            player.getWIDTH(),                           // Width of image (from source specified above)
                            player.getHEIGHT()                           // Height of image (from source specified above)
        );

        // Draw score
        gameEngine.drawText(font, ""+player.getScore(), 200, 100, 0, 60);


        // Move ledges, when they go past the screen, to reuse
        for (int i = 0; i < size; i++)
        {
            if (world.ledges.get(i).getX() == -200)
            {
                world.ledges.get(i).moveLedge(1200);
            }
        }

        if (!isPaused) {
            // Init game logic (move somewhere else)
            if (!player.getIsDead())
            {
                world.scrolling();
                world.gravity();
                world.handleTouch();
            }
            // If he dies, start countdown, reset variables and go back to menu screen
            else
            {
                deathCountdown++;
                if (deathCountdown == 50)
                {
                    player.setIsDead(false);
                    deathCountdown = 0;
                    player.setScore();
                    gameEngine.setScreen(new MainMenuScreen(gameEngine));
                }
            }
        }
        else {
            if (gameEngine.getTouchEvents().size() > 0) {
                isPaused = false;
            }

        }


    }

    @Override
    public void pause() {
        isPaused = true;
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }



    // Create each individual ledge, and add collision detection for it
    private void drawLedge(Ledge ledge) {
        gameEngine.drawBitMap(ledge.getBitmap(), ledge.getX(), ledge.getY());
        CollisionDetection.collisionDetectionLedges(ledge, player);
        CollisionDetection.countScore(ledge, player);
    }


}
