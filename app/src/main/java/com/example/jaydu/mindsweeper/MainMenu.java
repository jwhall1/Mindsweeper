package com.example.jaydu.mindsweeper;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.jaydu.mindsweeper.R;


/**
 * Created by john hall on 11/16/2016.
 */

public class MainMenu extends AppCompatActivity {

    public MediaPlayer menu;
    public MediaPlayer select;
    public ImageButton imageButton1;
    public ImageButton imageButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        initButtons();
        initMedia();
        menu.start();
    }

    public void initMedia(){
        menu = MediaPlayer.create(this,R.raw.boss);
        select = MediaPlayer.create(this,R.raw.select);
    }

    public void initButtons(){
        imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton1.setOnClickListener(play);
        imageButton2.setOnClickListener(play);
    }
    public View.OnClickListener play = new View.OnClickListener(){
        public void onClick(View btn){
            String name = (String) btn.getContentDescription();
            if(name.contentEquals("tutorial")){
                menu.stop();
                select.start();
                select.stop();
                startActivity(new Intent(MainMenu.this,Splash.class));
            }
            else if(name.contentEquals("game")){
                menu.stop();
                select.start();
                startActivity(new Intent(MainMenu.this,MainActivity.class));
            }
        }
    };
}
