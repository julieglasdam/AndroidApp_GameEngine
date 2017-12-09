package com.example.julieglasdam.gameengine.NotFlappyBird;

import android.graphics.Bitmap;
import com.example.julieglasdam.gameengine.GameEngine;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by julieglasdam on 13/11/2017.
 */

public class World
{
    private GameEngine gameEngine;
    private Bitmap ledge = null; // w: 200
    private Player player;
    private final int GRAVITY = 3;
    public List<Ledge> ledges = new ArrayList<>();


    public World(GameEngine gameEngine, Player player)
    {
        this.gameEngine = gameEngine;
        this.player = player;
        ledge = gameEngine.loadBitmap("notflappybirdassets/ledge.jpg");
    }


    // Draw all the ledges and add them to list, with the correct attributes
    public void createWorld()
    {
        // x, y, w, h
        ledges.add(new Ledge(ledge, 600, 0, 50, 100));
        ledges.add(new Ledge(ledge, 600, 250, 50, 100));

        ledges.add(new Ledge(ledge, 900, 0, 50, 100));
        ledges.add(new Ledge(ledge, 900, 270, 50, 100));

        ledges.add(new Ledge(ledge, 1200, 0, 50, 100));
        ledges.add(new Ledge(ledge, 1200, 270, 50, 100));
    }

    // Scrolling. Change the x position for all ledges at a constant rate
    public void scrolling()
    {
        int size = this.ledges.size();
        for (int i = 0; i < size; i++)
        {
            ledges.get(i).setX();
        }
    }

    public void gravity(){
        player.setY(player.getY()+GRAVITY);
    }

    public void handleTouch()
    {
        if (gameEngine.getTouchEvents().size() > 0)
        {
            player.jump();
        }
    }

}
