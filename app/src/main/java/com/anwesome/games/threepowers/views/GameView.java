package com.anwesome.games.threepowers.views;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.anwesome.games.threepowers.runner.GameRunner;

/**
 * Created by anweshmishra on 21/06/17.
 */

public class GameView extends SurfaceView {
    private Thread gameThread;
    private GameRunner gameRunner;
    public GameView(Context context) {
        super(context);
        initGameThread();
    }
    public void initGameThread() {
        SurfaceHolder surfaceHolder = getHolder();
        gameRunner =  GameRunner.getInstance(surfaceHolder);
        gameThread = new Thread(gameRunner);
        gameThread.start();
    }
    public void pause() {
        if(gameRunner!=null && gameThread!=null) {
            gameRunner.pause();
            while(true) {
                try {
                    gameThread.join();
                    break;
                }
                catch (Exception ex) {

                }
            }
        }
    }
    public void resume() {
        if(gameRunner != null) {
            gameRunner.resume();
            gameThread = new Thread(gameThread);
            gameThread.start();
        }
    }
}
