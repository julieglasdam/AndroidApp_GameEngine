package com.example.julieglasdam.gameengine;

/**
 * Created by julieglasdam on 12/09/2017.
 */

public class TouchEventPool extends Pool<TouchEvent>
{
    @Override
    protected TouchEvent newItem() {
        return new TouchEvent();
    }
}
