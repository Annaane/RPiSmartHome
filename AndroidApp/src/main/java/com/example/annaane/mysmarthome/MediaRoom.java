package com.example.annaane.mysmarthome;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.UnknownHostException;


public class MediaRoom extends MainActivity {

    Button ON, OFF, Source;
    ConstraintLayout MediaRoom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_room);

        //This Function is for customizing the default font
        CustomFont(R.id.textView4);


        ON = findViewById(R.id.ON);
        OFF = findViewById(R.id.OFF);
        Source = findViewById(R.id.Source);
        MediaRoom = findViewById(R.id.MediaLayout);



        ON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //This is for the background change !
                MediaRoom.setBackground(getResources().getDrawable(R.drawable.mediaroomon));
                //MakeNoise();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            ConnectionEstablishment("MediaON");
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });


        OFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaRoom.setBackground(getResources().getDrawable(R.drawable.mediaroomoff));
                //MakeNoise();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            ConnectionEstablishment("MediaOFF");
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });


    }

    public void IntentSourceControl(View view)
    {
        Intent newActivity = new Intent(MediaRoom.this, SourceControl.class);
        //MakeNoise();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
        startActivity(newActivity);
    }


}
