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
      //  gameEngine.drawBitMap(bob, 10, 10);
       // gameEngine.drawBitmap(bob, 100, 200, 0, 0, 64, 64);

    /*    for (int pointer = 0; pointer < 5; pointer++)
        {
            if (gameEngine.isTouchDown(pointer)) {
                gameEngine.drawBitMap(bob, gameEngine.getTouchX(pointer), gameEngine.getTouchY(pointer));

            }
        }*/

        float accX = gameEngine.getAccelerometer()[0];
        float accY = gameEngine.getAccelerometer()[1];
       // accX = 0;
        // accY = 0;
        float x = gameEngine.getFrameBufferWidth()/2 + (accX/10) * gameEngine.getFrameBufferWidth();
        float y = gameEngine.getFrameBufferHeight()/2 + (accY/10) * gameEngine.getFrameBufferHeight();

        gameEngine.drawBitMap(bob, (int)(x - (bob.getHeight()/2)), (int)(y - (bob.getHeight()/2)));

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
