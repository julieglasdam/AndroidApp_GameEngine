package com.example.julieglasdam.gameengine.Breakout;

import com.example.julieglasdam.gameengine.GameEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julieglasdam on 31/10/2017.
 */

public class WorldLevel2 {
    public static final float MIN_X = 0;
    public static final float MAX_X = 319;
    public static final float MIN_Y = 37;
    public static final float MAX_Y = 479;
    Paddle paddle = new Paddle();
    Ball ball = new Ball();
    List<Block> blocks = new ArrayList<>();
    GameEngine gameEngine;
    boolean gameOver = false;
    int lives = 3;

    public WorldLevel2(GameEngine ge, CollisionListener listener) {
        this.gameEngine = ge;
        generateBlocks();
    }

    private void generateBlocks() {
        blocks.clear();
        for (int y = 50, type = 0; y < 50 + 8*Block.HEIGHT; y = y+(int)Block.HEIGHT, type++) { // for each row
            for(int x = 20; x < MAX_X - Block.WIDTH/2; x = x + (int)Block.WIDTH) { // for each column
                blocks.add(new Block(x, y, type));
            }
        }
    }

    public void update(float deltatime, float accelX) {

        ball.x = (int)(ball.x + ball.vx * deltatime);
        ball.y = (int)(ball.y + ball.vy * deltatime);
        if (ball.x < MIN_X) {
            ball.vx = -ball.vx;
            ball.x = (int)MIN_X;
        }
        if (ball.x > MAX_X - ball.WIDTH) {
            ball.vx = -ball.vx;
            ball.x = (int)(MAX_X - ball.WIDTH);
        }
        if (ball.y < MIN_Y) {
            ball.vy = -ball.vy;
            ball.y = (int)(MIN_Y );
        }


        if (ball.y > MAX_Y - ball.HEIGHT) {
            gameOver = true;
            return;
        }

        paddle.x = paddle.x + accelX * deltatime * 10;
        if (paddle.x < MIN_X) {
            paddle.x = MIN_X;
        }
        if (paddle.x + paddle.WIDTH > MAX_X) {
            paddle.x = MAX_X - paddle.WIDTH;
        }

        // Drag paddle with mouse
        if (gameEngine.isTouchDown(0)) {
            if (gameEngine.getTouchY(0) > 450) {
                paddle.x = gameEngine.getTouchX(0);
            }
        }
        collideBallPaddle();
        collideBallBlocks();

        // If all blocks are removed, regenerate or start a new level
        if (blocks.size() == 0) {
            generateBlocks();
        }
    }

    private void collideBallBlocks() {
        Block block = null;
        for (int i = 0; i < blocks.size(); i++) {
            block = blocks.get(i);
            if (collideRects(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x, block.y, Block.WIDTH, Block.HEIGHT)) {
                blocks.remove(i);
                i--;
            }
        }
    }

    private boolean collideRects(float x1, float y1, float width1, float height1,
                                 float x2, float y2, float width2, float height2) {
        return x1 < x2+width2 && x1 + width1 > x2 && y1 + height1 > y2 && y1 < y2 + height2;
    }

    private void collideBallPaddle() {
        if (ball.y + Ball.HEIGHT >= paddle.y && ball.x < paddle.x + Paddle.WIDTH && ball.x + Ball.WIDTH > paddle.x) {
            ball.y = (int)(paddle.y - Ball.HEIGHT - 2);
            ball.vy = -ball.vy;
        }
    }
}
