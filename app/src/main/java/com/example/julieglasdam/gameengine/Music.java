package com.example.julieglasdam.gameengine;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by julieglasdam on 03/10/2017.
 */

public class Music implements MediaPlayer.OnCompletionListener{
    private MediaPlayer mediaPlayer;
    private boolean isPrepared = false;

    public Music(AssetFileDescriptor assetFileDescriptor){
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),   // Filedescriptor
                                      assetFileDescriptor.getStartOffset(),      // Where to start
                                      assetFileDescriptor.getLength());          // Length
            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load music");
        }
    }

    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public boolean isStopped() {
        return !isPrepared;
    }

    public void dispose() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void play() {
        if (mediaPlayer.isPlaying()) {
            return;
        }
        try {
            synchronized (this) {
                if (!isPrepared) {
                    mediaPlayer.prepare();
                }
                mediaPlayer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLooping(boolean isLooping) {
        mediaPlayer.setLooping(isLooping);
    }

    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume, volume);
    }

    public void stop() {
        synchronized (this) {
            if (!isPrepared) return;
            mediaPlayer.stop();
            isPrepared = false;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        synchronized (this) {
            isPrepared = false;
        }
    }
}
