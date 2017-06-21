package com.anwesome.games.threepowers;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.games.threepowers.views.GameView;

public class MainActivity extends AppCompatActivity {
    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    public void onPause() {
        if(gameView != null) {
            gameView.pause();
        }
        super.onPause();
    }
    public void onResume() {
        if(gameView!= null) {
            gameView.resume();
        }
        super.onResume();
    }
}
