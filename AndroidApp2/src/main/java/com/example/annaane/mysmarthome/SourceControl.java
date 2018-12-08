package com.example.annaane.mysmarthome;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;


public class SourceControl extends MainActivity {


    ImageButton left, right, up, down, volup, volmin, mute, play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);

        CustomFont(R.id.textView2);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_left);

        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        up = findViewById(R.id.up);
        down = findViewById(R.id.down);
        play = findViewById(R.id.play);
        volup = findViewById(R.id.volup);
        volmin = findViewById(R.id.volmin);
        mute= findViewById(R.id.mute);


        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            //MakeNoise();
                            ConnectionEstablishment("UpArrow");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            //MakeNoise();
                            ConnectionEstablishment("RightArrow");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            //MakeNoise();
                            ConnectionEstablishment("Play");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            //MakeNoise();
                            ConnectionEstablishment("DownArrow");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            //MakeNoise();
                            ConnectionEstablishment("LeftArrow");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        volmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            //MakeNoise();
                            ConnectionEstablishment("VolumeDown");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        volup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            //MakeNoise();
                            ConnectionEstablishment("VolumeUp");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            //MakeNoise();
                            ConnectionEstablishment("Mute");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }


    public void ShakeIt(View view)
    {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        //MakeNoise();
        view.startAnimation(shake);
    }

    public void intentA(View view)
    {
        //MakeNoise();
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
    }

}
