package com.example.pikachu_game;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;



public class Activity_Game extends AppCompatActivity {

    final int DELAY = 500; // delay per 1sec
    final Handler handler = new Handler();

    private Game_Manager gameManager;
    private int timerSeconds = 0;
    private boolean gameOverFlag = false;
    private long lastUpdateTime = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyScreenUtils.hideSystemUI(this);
        gameManager = new Game_Manager(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startTicker();
    }

    protected void updateBackImage(int id, ImageView imageView) {
        Glide.with(this).load(id).centerCrop().into(imageView);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!gameOverFlag) {
                gameManager.runLogic();
                if (!gameManager.isAlive())
                    gameOver();
                timerSeconds++;
                gameManager.getTimer_TXT_field()
                        .setText(String.format("%03d", timerSeconds));
                handler.postDelayed(runnable, DELAY);
            }
        }
    };


    private void startTicker() {
        handler.postDelayed(runnable, DELAY);
    }

    protected void gameOver() {
        gameOverFlag = true;
        handler.removeCallbacksAndMessages(runnable);
        Intent intent = new Intent(getApplicationContext(), Activity_GameOver.class);
        intent.putExtra("message_key", timerSeconds);
        startActivity(intent);
        finish();
    }
}