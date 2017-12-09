package com.example.julieglasdam.gameengine.NotFlappyBird;

import android.util.Log;

/**
 * Created by julieglasdam on 05/12/2017.
 */

public class CollisionDetection
{
    public CollisionDetection() {}

    // Check collision for a ledges and the player
    public static void collisionDetectionLedges(Ledge ledge, Player player)
    {
        // BOTTOM
        if(player.getX()+player.getWIDTH()/2 > ledge.getX() && player.getX()+player.getWIDTH()/2<ledge.getX()+ledge.getWidth())
        {
           if(player.getY() < ledge.getY()+ledge.getHeight() && player.getY() > ledge.getY())
           {
              player.setIsDead(true);
           }
        }

        // TOP
        if(player.getX()+player.getWIDTH() > ledge.getX() && player.getX()<ledge.getX()+ledge.getWidth())
        {
           if(player.getY()+player.getHEIGHT() > ledge.getY() && player.getY() < ledge.getY())
           {
              player.setIsDead(true);
           }
        }

        // LEFT
        if(player.getY()+player.getHEIGHT() > ledge.getY() && player.getY()<ledge.getY()+ledge.getHeight())
        {
           if(player.getX()+player.getWIDTH() > ledge.getX() && player.getX() < ledge.getX())
           {
               player.setIsDead(true);
           }
        }
    }

    // Change the score every time the player passes a set of ledges
    public static void countScore(Ledge ledge, Player player)
    {
        // Count score
        if (player.getX() == ledge.getX() && ledge.getY() == 0)
        {
            player.setScore();
        }
    }
}
