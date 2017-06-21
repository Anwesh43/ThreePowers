package com.anwesome.games.threepowers.views;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by anweshmishra on 21/06/17.
 */

public class GameView extends SurfaceView {
    private Thread gameThread;
    public GameView(Context context) {
        super(context);
        initGameThread();
    }
    public void initGameThread() {
        SurfaceHolder surfaceHolder = getHolder();
    }
}
