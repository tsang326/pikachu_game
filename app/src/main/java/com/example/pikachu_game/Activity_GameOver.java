package com.example.pikachu_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;




public class Activity_GameOver extends AppCompatActivity {

    private ImageView panel_IMG_gameOver;
    private Button game_over_BTN_back;
    private TextView timerSecondsMSG;
    private Button restart;


    public Activity_GameOver() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        MyScreenUtils.hideSystemUI(this);
        findViews();
        initViews();
        //Intent intent = getIntent();
    }

    private void findViews() {

        panel_IMG_gameOver = findViewById(R.id.panel_IMG_gameOver);
        Glide
                .with(this)
                .load(R.drawable.background_img)
                .centerCrop()
                .into(panel_IMG_gameOver);

        timerSecondsMSG =(TextView)findViewById(R.id.game_over_TXT_time);
        restart = (Button)findViewById(R.id.game_over_BTN_restart);
        game_over_BTN_back = findViewById(R.id.game_over_BTN_back);


    }

    private void initViews() {
        // start the Intent
        Intent intent = getIntent();
        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        int time = intent.getIntExtra("message_key",0);
        // display the string into textView
        timerSecondsMSG.setText(String.valueOf(time));

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
            }
        });

        game_over_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Activity_GameOver.this, Activity_Start.class);
                startActivity(myIntent);
                finish();
            }
        });
    }

    private void playAgain() {
        Intent intent = new Intent(getApplicationContext(), Activity_Game.class);
        startActivity(intent);
        finish();
    }
}
