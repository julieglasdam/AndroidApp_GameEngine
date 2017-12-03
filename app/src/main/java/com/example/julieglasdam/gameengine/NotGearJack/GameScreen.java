package com.example.julieglasdam.gameengine.NotGearJack;

import android.graphics.Bitmap;

import com.example.julieglasdam.gameengine.GameEngine;
import com.example.julieglasdam.gameengine.Screen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julieglasdam on 11/11/2017.
 */


public class GameScreen extends Screen {
    private final int GRAVITY = 10;

    private int scroll = 0;
    private List<Ledge> ledges = new ArrayList<>();
    private Bitmap background = null;
    private Bitmap ledge01 = null; // w: 300
    private Bitmap ledge02 = null; // w: 200
    private Bitmap spritesheet = null;

    private Player player;



    // Load the textures
    public GameScreen(GameEngine gameEngine) {
        super(gameEngine);
        background = gameEngine.loadBitmap("notgearjackassets/background.png");
        ledge01 = gameEngine.loadBitmap("notgearjackassets/ledge01.png");
        ledge02 = gameEngine.loadBitmap("notgearjackassets/ledge02.png");
        spritesheet = gameEngine.loadBitmap("notgearjackassets/spritesheet.png");


        // Initialize all the ledges
        createWorld();

        // Initialize player
        player = new Player(spritesheet, 200);


    }

    // Things that needs to be updated during the game
    @Override
    public void update(float deltaTime) {

        // Draw the background
        gameEngine.drawBitMap(background, 0, 0);

        // Draw the ledges. Needs to be updated because x positions keeps changing
        int size = ledges.size();

        for (int i = 0; i < size; i++) {
            drawLedge(ledges.get(i));
        }

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

        // Scrolling. Change the x position for all ledges at a constant rate
        for (int i = 0; i < size; i++) {
            ledges.get(i).setX();
        }

        // Gravity
        player.setY(player.getY()+GRAVITY);

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

    // Draw all the ledges
    private void createWorld() {
        // Add ledges to list, with the correct attributes
        ledges.add(new Ledge(ledge01, 0, 270, 300, 63));
        ledges.add(new Ledge(ledge02, 400, 250, 200, 90));
        ledges.add(new Ledge(ledge02, 700, 270, 400, 63));
        ledges.add(new Ledge(ledge02, 1000, 270, 400, 63));
        ledges.add(new Ledge(ledge01, 1300, 270, 300, 63));
        ledges.add(new Ledge(ledge01, 1700, 270, 300, 63));
    }

    // Create each individual ledge, and add collision detection for it
    private void drawLedge(Ledge ledge) {
        gameEngine.drawBitMap(ledge.getBitmap(), ledge.getX(), ledge.getY());
        collisionDetectionLedges(ledge); // needs some parameters
    }

    // Check collision for a ledges and the player
    private void collisionDetectionLedges(Ledge ledge) {

        // If players x coordinate + players width is more than the x position of the top of the path
        if(player.getX()+player.getWIDTH() > ledge.getX() && player.getX()<ledge.getX()+ledge.getWidth()){

        /* If players y coordinate + players height is more than the top of the path and the players y coordinate
         is less than the top of the path and the player is moving */
            if(player.getY()+player.getHEIGHT() > ledge.getY() && player.getY() < ledge.getY()) {
                player.setY(ledge.getY()-player.getHEIGHT()); // Players y position is y coordinate of ledge - mans height
            }
        }

    }
}
