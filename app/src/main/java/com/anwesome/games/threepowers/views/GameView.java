package com.anwesome.games.threepowers.views;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.anwesome.games.threepowers.runner.GameRunner;

/**
 * Created by anweshmishra on 21/06/17.
 */

public class GameView extends SurfaceView {
    private Thread gameThread;
    private GameRunner gameRunner;
    private GestureDetector gestureDetector;
    public GameView(Context context) {
        super(context);
        initGameThread();
        gestureDetector = new GestureDetector(context,new SwipeGestureListener());
    }
    public void initGameThread() {
        SurfaceHolder surfaceHolder = getHolder();
        gameRunner =  GameRunner.getInstance(surfaceHolder);
        gameThread = new Thread(gameRunner);
        gameThread.start();
    }
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
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
    private class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {
        public boolean onDown(MotionEvent event) {
            return true;
        }
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }
        public boolean onFling(MotionEvent e1,MotionEvent e2,float velx,float vely) {
            if (Math.abs(velx) > Math.abs(vely)) {
                if(e1.getX() < e2.getX()) {
                    gameRunner.handleSwipeRight();
                }
                else {
                    gameRunner.handleSwipeLeft();
                }
            }
            else {
                if(e1.getY() < e2.getY()) {
                    gameRunner.handleSwipeDown();
                }
                else {
                    gameRunner.handleSwipeUp();
                }
            }
            return true;
        }
    }
}
