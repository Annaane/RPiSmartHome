package com.example.annaane.mysmarthome;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CustomFont(R.id.textView);

    }

    public void IntentMediaRoom(View view)
    {
        Intent newActivity = new Intent(MainActivity.this, MediaRoom.class);
//        MakeNoise();
        startActivity(newActivity);
    }

    public void IntentProjection(View view)
    {
        Intent newActivity = new Intent(MainActivity.this, ProjectionActivity.class);
//        MakeNoise();
        startActivity(newActivity);
    }

    public void IntentLight(View view)
    {
        Intent newActivity = new Intent(MainActivity.this, LightActivity.class);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_right);
//        MakeNoise();
        startActivity(newActivity);
    }

    public void MakeNoise() {

        final MediaPlayer SoundEffect;
        SoundEffect = MediaPlayer.create(this, R.raw.blipsound);
        SoundEffect.start();
    }


    public void ConnectionEstablishment(String str) throws IOException
    {
        Socket socket;
        int Port=2009;
        String Server_add="192.168.1.5";
        PrintWriter printwriter;

        socket = new Socket(Server_add,Port);
        printwriter = new PrintWriter(socket.getOutputStream());
        printwriter.write(str);
        printwriter.flush();
        printwriter.close();
        socket.close();

    }

    public void CustomFont(int TextViewID)
    {
        // This is for changing the default font
        String fontPath= "CondLight.ttf";
        TextView textViewCustom = findViewById(TextViewID);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), fontPath);
        textViewCustom.setTypeface(myCustomFont);

    }
}
