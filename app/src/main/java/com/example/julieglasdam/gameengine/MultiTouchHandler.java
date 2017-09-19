package com.example.julieglasdam.gameengine;

import android.view.MotionEvent;
import android.view.View;

import java.util.List;

/**
 * Created by julieglasdam on 12/09/2017.
 */

public class MultiTouchHandler implements TouchHandler, View.OnTouchListener
{

    private boolean[] isTouched = new boolean[20];
    private int[] touchX = new int[20];
    private int[] touchY = new int[20];
    private List<TouchEvent> touchEventBuffer;
    private TouchEventPool touchEventPool;

    public MultiTouchHandler(View v, List<TouchEvent> touchEventBuffer, TouchEventPool touchEventPool)
    {
        v.setOnTouchListener(this);
        this.touchEventBuffer = touchEventBuffer;
        this.touchEventPool = touchEventPool;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        TouchEvent touchEvent = null;
        int action = motionEvent.getAction() & MotionEvent.ACTION_MASK;
        // Extract bits, and shift to right
        int pointerIndex = (motionEvent.getActionIndex() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        int pointerId = motionEvent.getPointerId(pointerIndex);
        return false;
    }

    @Override
    public boolean isTouchDown(int pointer)
    {
        return false;
    }

    @Override
    public int getTouchX(int pointer)
    {
        return 0;
    }

    @Override
    public int getTouchY(int pointer)
    {
        return 0;
    }


}
