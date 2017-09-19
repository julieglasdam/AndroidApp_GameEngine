package com.example.julieglasdam.gameengine;

/**
 * Created by julieglasdam on 12/09/2017.
 */

public class TouchEvent
{
    public enum TouchEventType
    {
        Down,
        Up,
        Dragged
    }

    public TouchEventType type;

    public int x;       // Coordinate x
    public int y;       // Coordinate y
    public int pointer; // Pointer id from Android system
}
