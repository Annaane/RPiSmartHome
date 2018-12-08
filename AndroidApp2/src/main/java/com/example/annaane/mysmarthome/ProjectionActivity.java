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


public class ProjectionActivity extends MainActivity{

    Button ProjOn, ProjOff;
    ConstraintLayout ProjectionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projection);

        CustomFont(R.id.textView5);

        ProjOn = findViewById(R.id.projon);
        ProjOff = findViewById(R.id.projoff);
        ProjectionLayout= findViewById(R.id.ProjectionLayout);


        ProjOn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //MakeNoise();
                ProjectionLayout.setBackground(getResources().getDrawable(R.drawable.projectionon));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try
                        {
                            ConnectionEstablishment("ON");
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });

        ProjOff.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //MakeNoise();
                ProjectionLayout.setBackground(getResources().getDrawable(R.drawable.projectionoff));
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


    public void IntentProjectionControl(View view)
    {
        Intent newActivity = new Intent(ProjectionActivity.this, SourceControl.class);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
        startActivity(newActivity);
    }
}
