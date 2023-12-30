package com.example.pikachu_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;



public class Activity_Start extends AppCompatActivity {

    private ImageView panel_IMG_background;
    private Button start_BTN_play, start_BTN_quit;
    private TextView start_TXT_pikachu;
    private Animation uptodown,downtoup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        MyScreenUtils.hideSystemUI(this);
        findViews();
        initViews();
    }

    private void findViews() {
        panel_IMG_background = findViewById(R.id.panel_IMG_start);
        Glide
                .with(this)
                .load(R.drawable.background_img)
                .centerCrop()
                .into(panel_IMG_background);
        start_TXT_pikachu = findViewById(R.id.start_TXT_pikachu);
        start_BTN_play = findViewById(R.id.start_BTN_play);
        start_BTN_quit = findViewById(R.id.start_BTN_quit);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodownanimation);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoupanimation);
        start_TXT_pikachu.setAnimation(uptodown);
        start_BTN_play.setAnimation(downtoup);
        start_BTN_quit.setAnimation(downtoup);

    }

    private void initViews() {

        start_BTN_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(false);
            }
        });

        start_BTN_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quitGame();
            }
        });

    }
    private void quitGame() {
        // Perform any cleanup or save game state if needed
        finish(); // Close the current activity (exit the game)
    }
    private void startGame(boolean sensorMode) {
        Intent myIntent = new Intent(this, Activity_Game.class);
        Bundle bundle = new Bundle();
        myIntent.putExtras(bundle);
        startActivity(myIntent);
        finish();
    }
}