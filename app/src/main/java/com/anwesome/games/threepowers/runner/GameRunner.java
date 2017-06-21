package com.anwesome.games.threepowers.runner;

import android.view.SurfaceHolder;

/**
 * Created by anweshmishra on 21/06/17.
 */

public class GameRunner implements Runnable{
    private boolean isRunning = true;
    private GameRunner gameRunner;
    private final long timeDiff = 50;
    private long prevTime = System.currentTimeMillis();
    private GameRunner(SurfaceHolder surfaceHolder) {

    }
    public void run() {
        while(isRunning) {
            long currTime = System.currentTimeMillis();
            if(currTime - prevTime > timeDiff) {

            }
        }
    }
    public static GameRunner getInstance(SurfaceHolder surfaceHolder) {
        GameRunner gameRunner = new GameRunner(surfaceHolder);
        return gameRunner;
    }
    public void pause() {
        if(isRunning) {
            isRunning = false;
        }
    }
    public void resume() {
        if(!isRunning) {
            isRunning = true;
        }
    }
}
