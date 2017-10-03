package com.example.julieglasdam.gameengine.Breakout;

import com.example.julieglasdam.gameengine.GameEngine;
import com.example.julieglasdam.gameengine.Screen;

/**
 * Created by julieglasdam on 03/10/2017.
 */

public class Breakout extends GameEngine {
    @Override
    public Screen createScreen() {
        return new MainMenuScreen(this);
    }
}
