package com.example.julieglasdam.gameengine;

/**
 * Created by julieglasdam on 12/09/2017.
 */

public class MultiTouchHandler implements TouchHandler
{

    @Override
    public boolean isTouchDown(int pointer) {
        return false;
    }

    @Override
    public int getTouchX(int pointer) {
        return 0;
    }

    @Override
    public int getTouchY(int pointer) {
        return 0;
    }
}
