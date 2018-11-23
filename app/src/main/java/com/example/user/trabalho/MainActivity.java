package com.example.user.trabalho;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    //media player
    private MediaPlayer backgroundPlayer;
    private MediaPlayer buttonPlayer;
    static public Uri[] sounds;
    private int current_sound = 0;
    public int i=0;


    //colour part
    public int [] colors ={Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.BLACK};
    public int position=0;
    public static final String COLOR_ID = "color id";
    Intent intent;
    public Button buttonBackground;
    public Button buttonBt;
    public Button buttonFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //media player

        buttonPlayer = new MediaPlayer();
        buttonPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Reset the player
                if(i==0){
                    backgroundPlayer.pause();
                    i=1;
                }
                else{
                    backgroundPlayer.start();
                    i=0;
                }

            }
        });

        //Intent and buttons -> for the listener
        intent= new Intent(getApplicationContext(),SecondActivity.class);
        buttonBackground = (Button) findViewById(R.id.bt_background);
        buttonBt = (Button) findViewById(R.id.bt_buttons);
        buttonFont = (Button) findViewById(R.id.bt_font);

        buttonBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("val", buttonBackground.getId());
                startActivityForResult(intent,1);
                Toast.makeText(MainActivity.this, "background", Toast.LENGTH_SHORT).show();
            }
        });

        buttonBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("val", buttonBt.getId());
                startActivityForResult(intent,2);
                //Toast.makeText(MainActivity.this, "change", Toast.LENGTH_SHORT).show();
            }
        });

        buttonFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("val", buttonFont.getId());
                startActivityForResult(intent,3);
                //Toast.makeText(MainActivity.this, "font", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onActivityResult(int RequestCode,int ResultCode,Intent data) {
        if (ResultCode == RESULT_OK) {
        // Make sure the request was successful
            if (RequestCode == 1) {
                position=data.getIntExtra(COLOR_ID,position);
                getWindow().getDecorView().setBackgroundColor(colors[position]);
            }
            else if (RequestCode == 2) {
                position = (int) data.getIntExtra(COLOR_ID, position);
                buttonBackground.setBackgroundColor(colors[position]);
                buttonBt.setBackgroundColor(colors[position]);
                buttonFont.setBackgroundColor(colors[position]);
            }
            else if (RequestCode == 3) {
                position = (int) data.getIntExtra(COLOR_ID, position);
                buttonBackground.setTextColor(colors[position]);
                buttonBt.setTextColor(colors[position]);
                buttonFont.setTextColor(colors[position]);

            }

        }

    }

    //control mediaplayer
    @Override
    protected void onPause() {
        super.onPause();
            //Send the background player to the paused state
        backgroundPlayer.pause();
        buttonPlayer.pause();
    }
    @Override
    protected void onResume() {
        super.onResume();
                    //Create and prepare MediaPlayer with R.raw.mario as the data streamsource
        backgroundPlayer = MediaPlayer.create(this, R.raw.mario);
                    //Define a procedure that will be executed when the MediaPlayer goes to
                    //the prepared state
        backgroundPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //Set the looping parameter to true
                mp.setLooping(true);
                //Start the playback.
                //By placing the start method in the onPrepared event
                //we are always certain that the audio stream is prepared.
                mp.start();
            }
        });
    }
    @Override
    protected void onStop(){
        super.onStop();
            //Release the background player when we don’t need it.
        backgroundPlayer.release();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
