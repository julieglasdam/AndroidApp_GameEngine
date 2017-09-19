package com.example.julieglasdam.gameengine;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

/**
 * Created by julieglasdam on 05/09/2017.
 */

public class TestScreen extends Screen
{
    Bitmap bob = null;
    public TestScreen(GameEngine gameEngine) {
        super(gameEngine);
        bob = gameEngine.loadBitmap("bob.png");
    }
    @Override
    public void update(float deltaTime) {
        gameEngine.clearFrameBuffer(Color.BLUE);
        gameEngine.drawBitMap(bob, 10, 10);
       // gameEngine.drawBitmap(bob, 100, 200, 0, 0, 64, 64);
    }

    @Override
    public void pause() {
        Log.d("Testscreen", "the screen is paused");
    }

    @Override
    public void resume() {
        Log.d("Testscreen", "the screen is resumed");
    }

    @Override
    public void dispose() {
        Log.d("Testscreen", "the screen is disposed");
    }
}
