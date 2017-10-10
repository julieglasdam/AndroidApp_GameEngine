package com.example.julieglasdam.gameengine.Breakout;

/**
 * Created by julieglasdam on 10/10/2017.
 */

public class World {
    public static final float MIN_X = 0;
    public static final float MAX_X = 319;
    public static final float MIN_Y = 37;
    public static final float MAX_Y = 479;

    Ball ball = new Ball();

    public void update(float deltatime) {
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
            ball.vy = -ball.vy;
            ball.y = (int)(MAX_Y - ball.HEIGHT);
        }
    }
}
