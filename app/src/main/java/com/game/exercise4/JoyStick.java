package com.game.exercise4;

import android.content.Intent;
import android.content.res.Configuration;
import androidx.appcompat.app.AppCompatActivity;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class JoyStick extends AppCompatActivity implements ObserverI {

    private Client client;
    /**
     * initializing values while creating joystick
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);
        // getting the joystick activity
        Intent intent = getIntent();
        try
        {
            this.client = new Client();
            // getting ip and port from the main activity
            this.client.execute(intent.getStringExtra("ip"), intent.getStringExtra("port"), null);
        }

        catch(Exception e) {
            System.out.println(e.toString());
        }

        JoyStickView joyStickView = new JoyStickView(this);
        joyStickView.addToObserver(this);
        setContentView(joyStickView);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * when this class is destroyed we stop the tcp client
     */
    public void onDestroy()
    {
        this.client.Stop();
        super.onDestroy();
    }

    /**
     * sending aileron and elevator through tcp client
     * @param x,  y
     */
    public void update(float x, float y)
    {
        client.Send("set /controls/flight/aileron " + x);
        client.Send("set /controls/flight/elevator " + y);
    }


}
