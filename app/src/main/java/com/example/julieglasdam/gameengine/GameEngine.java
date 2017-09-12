package com.example.julieglasdam.gameengine;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/*
* We use events because, we want to save battery, so the program only runs when something happens
* we use threads so we can change the code that runs, so everything doesn't run at once*/

public abstract class GameEngine extends Activity implements Runnable
{

    private Thread mainLoopThread;
    private State state = State.Paused;
    private List<State> stateChanges = new ArrayList<>(); // Use Arraylist because we only delete from end

    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    private Screen screen;
    private Canvas canvas;
    private Bitmap virtualScreen;

    Rect src = new Rect();
    Rect dst = new Rect();

    private TouchHandler touchHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Has to call super because it's overriding method in super class
        super.onCreate(savedInstanceState);
        // Add flags (true or false)
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN |
                                  WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        surfaceView = new SurfaceView(this);
        setContentView(surfaceView);
        surfaceHolder = surfaceView.getHolder();

        screen = createScreen();
        // Check if display is horizontal or vertical
        if (surfaceView.getWidth() > surfaceView.getHeight())
        {
            setVirtualScreen(480, 320);
        }
        else
        {
            setVirtualScreen(320, 480);
        }
        touchHandler =

    }

    public void setVirtualScreen(int width, int height)
    {
        if (virtualScreen != null)
        {
            virtualScreen.recycle(); // reuse if there's already a screen
        }
        virtualScreen = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        canvas = new Canvas(virtualScreen);
    }

    public int getFrameBufferWidth()
    {
        return virtualScreen.getWidth();
    }

    public int getFrameBufferHeight()
    {
        return virtualScreen.getHeight();
    }

    public abstract Screen createScreen();

    public void setScreen(Screen screen)
    {
        if (this.screen != null) {
            this.screen.dispose();
            this.screen = screen;
        }
    }

    // Load some texture. Parameter is the filename of the image we want to load
    public Bitmap loadBitmap(String filename)
    {
        InputStream  in = null;
        Bitmap bitmap = null;

        try {
            // Get the image from the resource folder and return it
            in = getAssets().open(filename);
            bitmap = BitmapFactory.decodeStream(in);
            if (bitmap == null) {
                throw new RuntimeException(" Could not find image " + filename);
            }
            return bitmap;
        } catch (IOException e) {
            throw new RuntimeException("Could not open image " + filename);
        }
        // Close input stream
        finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

  //  public Music loadMusic(String filename) {return null;}

   // public Sound loadSound(String filename) {return null;}

    public void clearFrameBuffer(int color) {
        canvas.drawColor(color);
    }


    // Draw whole bitmap
    public void drawBitMap(Bitmap bitmap, int x, int y)
    {
        if (canvas != null) {
            canvas.drawBitmap(bitmap, x, y, null);
        }
    }


    // Draw part of the bitmap (for spritesheets)
    public void drawBitmap(Bitmap bitmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight)
    {
        Rect src = new Rect();
        Rect dst = new Rect();
        if (canvas == null) return;
        src.left = srcX;
        src.top = srcY;
        src.right = srcX + srcWidth;
        src.bottom = srcY + srcHeight;
        dst.left = x;
        dst.top = y;
        dst.right = x + srcWidth;
        dst.bottom = y + srcHeight;
        canvas.drawBitmap(bitmap, src, dst, null);
    }

    // Methods for when the screen is touched
    public boolean isTouchDown(int pointer) {return false;}
    public int getTouchX(int pointer) {return 0;}
    public int getTouchY(int pointer) {return 0;}

  //  public List<TouchEvent> getTouchEvents() {return null;}
    public float[] getAccelerometer(){return null;}

    public void onPause()
    {
        super.onPause();
        synchronized (stateChanges) {
            // Check is the app is shutting down or just paused
            if (isFinishing()) // Method inherited from Activity
            {
                stateChanges.add(State.Disposed);
            }
            else
                {
                stateChanges.add(State.Paused);
            }
        }
        try
        {
            mainLoopThread.join();
        }
        catch (Exception e)
        {
            Log.d("GameEngine", "error when waiting for Mainloop thread to die");
        }
    }


    public void onResume()
    {
        super.onResume();
        // Syncronize before creating thread
        synchronized (stateChanges){
            stateChanges.add(State.Resumed);
        }

        mainLoopThread = new Thread(this); // Create new thread
        mainLoopThread.start();
    }

    @Override
    /* stateChanges is like buffer. Check if something needs to be handled, and iterate
    through array to figure out what needs to be done */
    public void run()
    {
        while(true)
        {
            synchronized (stateChanges) // Lock stateChanges, so it can't be accesed by multiple threads at once
            {
                int stopValue = stateChanges.size(); // Do this so loop doesn't need to check array size for every iteration
                for(int i = 0; i < stopValue; i++)
                {
                    state = stateChanges.get(i);
                    if (state == State.Disposed)
                    {
                        if (screen != null) screen.dispose();
                        Log.d("GameEngine", "disposed");
                        stateChanges.clear();
                        return; // Break out of the method
                    }
                    if (state == State.Paused)
                    {
                        if (screen != null) screen.pause();
                        Log.d("GameEngine", "paused");
                        stateChanges.clear();
                        return;
                    }
                    if (state == State.Resumed)
                    {
                        if (screen != null) screen.resume();
                        Log.d("GameEngine", "resumed");
                        state = State.Running;
                    }
                }
                stateChanges.clear();
            }
            // After syncronizing, do the actual work of the thread
            if (state == State.Running)
            {
                // Check if there's connection to the actual screen
                if (!surfaceHolder.getSurface().isValid())
                {
                   continue; // Start from the beginning of the loop
                }
                Canvas canvas = surfaceHolder.lockCanvas(); // gets a canvas and locks it
                // canvas.drawColor(Color.RED);
                if (screen != null) {
                    screen.update(0); // parameter doesn't matter now
                }
                // copy objects from virtual screen to actual screen
                src.left = 0;
                src.top = 0;
                src.right = virtualScreen.getWidth() - 1;
                src.bottom = virtualScreen.getHeight() - 1;
                dst.left = 0;
                dst.top = 0;
                dst.right = surfaceView.getWidth();
                dst.bottom = surfaceView.getHeight();
                canvas.drawBitmap(virtualScreen, src, dst, null);

                surfaceHolder.unlockCanvasAndPost(canvas); // post updates
            }
        }
    }
}
