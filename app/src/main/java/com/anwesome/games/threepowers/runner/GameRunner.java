package com.anwesome.games.threepowers.runner;

import android.view.SurfaceHolder;

import com.anwesome.games.threepowers.utils.GameRenderer;

/**
 * Created by anweshmishra on 21/06/17.
 */

public class GameRunner implements Runnable{
    private boolean isRunning = true;
    private GameRenderer gameRenderer;
    private final long timeDiff = 50;
    private long prevTime = System.currentTimeMillis();
    private GameRunner(SurfaceHolder surfaceHolder) {
        gameRenderer = GameRenderer.getInstance(surfaceHolder);
    }
    public void run() {
        while(isRunning) {
            long currTime = System.currentTimeMillis();
            if(currTime - prevTime > timeDiff) {
                gameRenderer.render();
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
