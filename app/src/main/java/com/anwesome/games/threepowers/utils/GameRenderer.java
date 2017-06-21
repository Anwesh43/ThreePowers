package com.anwesome.games.threepowers.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.anwesome.games.threepowers.gameobjects.GridContainer;

/**
 * Created by anweshmishra on 21/06/17.
 */

public class GameRenderer {
    private SurfaceHolder surfaceHolder;
    private int time = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private GridContainer gridContainer = new GridContainer();
    private GameRenderer(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }
    public void render() {
        if(surfaceHolder.getSurface().isValid()) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if(time == 0) {
                int w = canvas.getWidth(), h = canvas.getHeight();
                gridContainer.init(w);
                gridContainer.populateGrid();
            }
            time++;
            canvas.drawColor(Color.WHITE);
            gridContainer.draw(canvas,paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
            gridContainer.move();
        }
    }
    public static GameRenderer getInstance(SurfaceHolder surfaceHolder) {
        return new GameRenderer(surfaceHolder);
    }
    public void handleSwipeLeft() {
        gridContainer.handleSwipeLeft();
    }
    public void handleSwipeRight() {
        gridContainer.handleSwipeRight();
    }
    public void handleSwipeDown() {
        gridContainer.handleSwipeDown();
    }
    public void handleSwipeUp() {
        gridContainer.handleSwipeUp();
    }
}
