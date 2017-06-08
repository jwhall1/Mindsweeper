package com.example.jaydu.mindsweeper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jaydu.mindsweeper.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;

import static com.example.jaydu.mindsweeper.R.color.*;
import static com.example.jaydu.mindsweeper.R.id.table;
import static com.example.jaydu.mindsweeper.R.string.b1;
import static com.example.jaydu.mindsweeper.R.string.level;
import static com.example.jaydu.mindsweeper.R.string.points;
import static com.example.jaydu.mindsweeper.R.string.record;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.holdsLock;
import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    public final String STATE_POINTS = "points";
    public final String STATE_LEVEL = "level";
    public String PLAYER_SCORE;
    public int points;
    public int level;
    public TextView score;
    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;
    public Button button6;
    public Button button7;
    public Button button8;
    public Button button9;
    public Button button10;
    public Button button11;
    public Button button12;
    public Button button13;
    public Button button14;
    public Button button15;
    public Button button16;
    public Button button17;
    public Button button18;
    public Button button19;
    public Button button20;
    private MediaPlayer mSelect;
    private MediaPlayer mVanish;
    private MediaPlayer mDefeat;
    public ArrayList<Button> seq;
    public ArrayList<Button> ai;
    public ArrayList<Button> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState != null){
            level = savedInstanceState.getInt(STATE_LEVEL);
            points = savedInstanceState.getInt(STATE_POINTS);
            load();
        }
        else{
            level = 1;
            points = 0;
            load();
        }

    }

    
    public void load(){
        score = (TextView) findViewById(R.id.textview1);
        score.setText(String.valueOf(points));
        mSelect = MediaPlayer.create(this, R.raw.warp);
        mVanish = MediaPlayer.create(this, R.raw.vanish);
        mDefeat = MediaPlayer.create(this, R.raw.defeat);
        ai = new ArrayList<>();
        seq = new ArrayList<>();
        list = new ArrayList<>();
        initButtons();
        initListeners();
        loadAi();
    }

    @Override
    protected void onStart() {
        super.onStart();
        start();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mSelect.stop();
        mVanish.stop();
        mDefeat.stop();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(STATE_LEVEL, level);
        savedInstanceState.putInt(STATE_POINTS, points);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        level = savedInstanceState.getInt(STATE_LEVEL);
        points = savedInstanceState.getInt(STATE_POINTS);
    }

    public void update(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if(points > preferences.getInt(PLAYER_SCORE, points)) {
            editor.putInt(PLAYER_SCORE, points);
            editor.commit();
        }
    }

   //initialize buttons
    public void initButtons(){
        list.add(button1 = (Button) findViewById(R.id.button1));
        list.add(button2 = (Button) findViewById(R.id.button2));
        list.add(button3 = (Button) findViewById(R.id.button3));
        list.add(button4 = (Button) findViewById(R.id.button4));
        list.add(button5 = (Button) findViewById(R.id.button5));
        list.add(button6 = (Button) findViewById(R.id.button6));
        list.add(button7 = (Button) findViewById(R.id.button7));
        list.add(button8 = (Button) findViewById(R.id.button8));
        list.add(button9 = (Button) findViewById(R.id.button9));
        list.add(button10 = (Button) findViewById(R.id.button10));
        list.add(button11 = (Button) findViewById(R.id.button11));
        list.add(button12 = (Button) findViewById(R.id.button12));
        list.add(button13 = (Button) findViewById(R.id.button13));
        list.add(button14 = (Button) findViewById(R.id.button14));
        list.add(button15 = (Button) findViewById(R.id.button15));
        list.add(button16 = (Button) findViewById(R.id.button16));
        list.add(button17 = (Button) findViewById(R.id.button17));
        list.add(button18 = (Button) findViewById(R.id.button18));
        list.add(button19 = (Button) findViewById(R.id.button19));
        list.add(button20 = (Button) findViewById(R.id.button20));
    }

   //initialize listeners to buttons
    public void initListeners(){

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button1);
                compare(button1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button2);
                compare(button2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button3);
                compare(button3);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button4);
                compare(button4);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button5);
                compare(button5);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button6);
                compare(button6);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button7);
                compare(button7);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button8);
                compare(button8);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button9);
                compare(button9);
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button10);
                compare(button10);
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button11);
                compare(button11);
            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button12);
                compare(button12);
            }
        });
        button13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button13);
                compare(button13);
            }
        });
        button14.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button14);
                compare(button14);
            }
        });
        button15.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button15);
                compare(button15);
            }
        });
        button16.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button16);
                compare(button16);
            }
        });
        button17.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button17);
                compare(button17);
            }
        });
        button18.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button18);
                compare(button18);

            }
        });
        button19.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button19);
                compare(button19);
            }
        });
        button20.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn){
                recolor(button20);
                compare(button20);
            }
        });
    }

    //repaints color of selected button
    public void recolor(Button button){
        /**
        ColorDrawable color = (ColorDrawable) button.getBackground();

        if(color.getColor() == Color.BLACK) {
            button.setBackgroundColor(Color.BLUE);
        }
        else if(color.getColor() == Color.BLUE){
            button.setBackgroundColor(Color.RED);
        }
        else if(color.getColor() == Color.RED){
            button.setBackgroundColor(Color.YELLOW);
        }
        else if(color.getColor() == Color.YELLOW){
            button.setBackgroundColor(Color.GREEN);
        }
        else{
            button.setBackgroundColor(Color.BLUE);
        }
        */
        button.setBackgroundColor(Color.BLACK);
}

    //compares selected button to sequence
    public void compare(Button button) {
        mSelect.start();

        if (seq.contains(button)) {
            points = points + 10;
            score.setText(String.valueOf(points));
            seq.remove(button);

            if (seq.isEmpty()) {
                level = level +1;
                recreate();
            }
        } else {

            mDefeat.start();
            update();
            try {
                currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(MainActivity.this, Continue.class));
        }
    }

    //creates a sequence for player to follow
    public void loadAi() {

        Collections.shuffle(list);
        for (int i = 0; i < level; i++) {
            ai.add(list.get(i));
            seq.add(list.get(i));
        }
    }

    //paints a block for the sequence
    public void paint(int i) {

        if (i%4 == 0) {
            ai.get(i).setBackgroundColor(Color.BLUE);
        } else if (i%4== 1) {
            ai.get(i).setBackgroundColor(Color.RED);
        } else if (i%4 == 2) {
            ai.get(i).setBackgroundColor(Color.YELLOW);
        } else if (i%4 == 3){
            ai.get(i).setBackgroundColor(Color.GREEN);
        }
        else{
            ai.get(i).setBackgroundColor(Color.WHITE);
        }
    }

    //creates a new thread for sequence
    public void start() {
        for(int i = 0; i < level; i++) {
            mVanish.start();
            paint(i);
        }
    }
}

