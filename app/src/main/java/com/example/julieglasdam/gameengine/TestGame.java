package com.example.julieglasdam.gameengine;

import java.util.Random;

/**
 * Created by julieglasdam on 05/09/2017.
 */

public class TestGame extends GameEngine
{

    @Override
    public Screen createScreen() {
        return new TestScreen(this);
    }


}
