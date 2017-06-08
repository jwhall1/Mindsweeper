package com.example.jaydu.mindsweeper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jaydu.mindsweeper.R;

import static com.example.jaydu.mindsweeper.R.string.points;
import static com.example.jaydu.mindsweeper.R.string.record;
import static java.lang.Thread.currentThread;


/**
 * Created by john hall on 11/16/2016.
 */

public class Continue extends AppCompatActivity {
    public MediaPlayer mContinue;
    public MediaPlayer mChoice;
    public String PLAYER_SCORE;
    public Button yes;
    public Button no;
    public TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_continue);
        score = (TextView) findViewById(R.id.textview1);
        update();
        initSounds();
        initButtons();

        mContinue.start();

    }

    public void update(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("data", MODE_PRIVATE);
        score.setText(String.valueOf(preferences.getInt(PLAYER_SCORE, points)));
    }

    public void initButtons(){
        yes = (Button) findViewById(R.id.yes);
        no =  (Button) findViewById(R.id.no);
        yes.setOnClickListener(play);
        no.setOnClickListener(play);
    }

    public void initSounds(){
        mContinue = MediaPlayer.create(this,R.raw.password);
        mChoice = MediaPlayer.create(this,R.raw.up);
    }

    public View.OnClickListener play = new View.OnClickListener(){
        public void onClick(View btn){
            String name = (String) btn.getContentDescription();
            if(name.contentEquals("Yes")){
                mContinue.stop();
                mChoice.start();

                startActivity(new Intent(Continue.this,MainActivity.class));
            }
            else if(name.contentEquals("No")){
                mContinue.stop();
                mChoice.start();

                startActivity(new Intent(Continue.this, Splash.class));
            }
        }
    };


}
