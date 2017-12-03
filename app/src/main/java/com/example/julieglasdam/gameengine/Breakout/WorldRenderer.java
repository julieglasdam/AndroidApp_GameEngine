package com.example.julieglasdam.gameengine.Breakout;

import android.graphics.Bitmap;

import com.example.julieglasdam.gameengine.GameEngine;

/**
 * Created by julieglasdam on 10/10/2017.
 */

public class WorldRenderer {
    GameEngine gameEngine;
    World world;
    Bitmap ballImage;
    Bitmap paddleImage;
    Bitmap blocksImage;

    public WorldRenderer(GameEngine ge, World w) {
        gameEngine = ge;
        world = w;
        ballImage = gameEngine.loadBitmap("breakoutassets/ball.png");
        paddleImage = gameEngine.loadBitmap("breakoutassets/paddle.png");
        blocksImage = gameEngine.loadBitmap("breakoutassets/blocks.png");
    }

    public void render() {
        gameEngine.drawBitMap(ballImage, world.ball.x, world.ball.y);
        gameEngine.drawBitMap(paddleImage, (int)world.paddle.x, (int)world.paddle.y);
        int stop = world.blocks.size();
        Block block = null;
        for (int i = 0; i < stop; i++) {
            block = world.blocks.get(i);
            gameEngine.drawBitmap(blocksImage, (int)block.x, (int)block.y, 0, block.type*(int)Block.HEIGHT, (int)Block.WIDTH, (int)Block.HEIGHT);
        }
    }
}
