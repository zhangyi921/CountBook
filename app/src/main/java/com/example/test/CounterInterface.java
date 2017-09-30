package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CounterInterface extends AppCompatActivity {
    private int currentValuer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_interface);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            String CounterName = mBundle.getString("CounterName");

            Log.i("debug", CounterName);
            currentValuer = mBundle.getInt("currentValuer");
            String val;
            if (currentValuer == 0) {
                val = "0";
            } else
                val = "did not get current value";
            Log.i("debug", val);
        }
        Button goBack = (Button) findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.i("debug","button pressed");
                Intent returnIntent = new Intent();
                //String result = "result1";
                returnIntent.putExtra("counterIndex",currentValuer);
                returnIntent.putExtra("result2","result2");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}
