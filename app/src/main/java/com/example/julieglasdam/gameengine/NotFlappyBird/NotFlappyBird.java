package com.example.julieglasdam.gameengine.NotFlappyBird;

import com.example.julieglasdam.gameengine.GameEngine;
import com.example.julieglasdam.gameengine.Screen;

/**
 * Created by julieglasdam on 07/11/2017.
 */

public class NotFlappyBird extends GameEngine {
    // Initialize the menu screen as the first screen in the game
    @Override
    public Screen createScreen() {
        return new MainMenuScreen(this);
    }
}
