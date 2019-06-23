package com.game.exercise4;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This is a listener functions, that aimed to start, when user
     * presses button "connect" on main activity.
     * @param view
     */
    public void Connect(View view){
        // taking values of ip and port
        EditText ip = (EditText) findViewById(R.id.ipInput);
        EditText port = (EditText) findViewById(R.id.portInput);
        String ipStr = ip.getText().toString();
        String portStr = port.getText().toString();

        if(ipStr.isEmpty() || portStr.isEmpty())
            return;

        // creating new activity window, and sending values to there
        Intent intent = new Intent(this, JoyStick.class);
        intent.putExtra("ip", ipStr);
        intent.putExtra("port", portStr);

        // start new activity window
        startActivity(intent);
    }

}
