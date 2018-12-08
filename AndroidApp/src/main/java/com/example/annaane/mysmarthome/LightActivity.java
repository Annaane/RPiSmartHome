package com.example.annaane.mysmarthome;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.UnknownHostException;

public class LightActivity extends MainActivity {

    Button Bright, Mid, Dim, Off;
    ConstraintLayout LightActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        CustomFont(R.id.textView3);


        Bright = findViewById(R.id.lighton);
        Mid = findViewById(R.id.lightmid);
        Dim = findViewById(R.id.lightdim);
        Off = findViewById(R.id.lightoff);
        LightActivity = findViewById(R.id.RelativeLayout);



        Bright.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //MakeNoise();
                //This is for the background change !
                LightActivity.setBackground(getResources().getDrawable(R.drawable.background_lighton));

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try
                        {
                            ConnectionEstablishment("Bright");
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        Mid.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //MakeNoise();
                LightActivity.setBackground(getResources().getDrawable(R.drawable.background_lightmid));
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try
                        {
                            ConnectionEstablishment("Mid");
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });

        Dim.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //MakeNoise();
                LightActivity.setBackground(getResources().getDrawable(R.drawable.background_lightdim));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ConnectionEstablishment("Dim");
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        Off.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //MakeNoise();
                LightActivity.setBackground(getResources().getDrawable(R.drawable.background_lightoff));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try
                        {
                            ConnectionEstablishment("Off");
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

}
