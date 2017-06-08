package com.example.jaydu.mindsweeper;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jaydu.mindsweeper.R;

/**
 * Created by john hall on 11/16/2016.
 */

public class Splash extends AppCompatActivity {
    public MediaPlayer mMediaPlayer;
    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMediaPlayer = MediaPlayer.create(this, R.raw.title);
        Thread thread = new Thread() {

            public void run() {
                try {
                    super.run();
                    mMediaPlayer.start();
                    sleep(3000);
                    mMediaPlayer.stop();
                } catch (Exception e) {
                } finally {
                    startActivity(new Intent(Splash.this, MainMenu.class));
                    finish();
                }
            }
        };
        thread.start();
    }

}
