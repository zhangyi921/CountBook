package com.example.customlistview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.util.Log;

public class ListActivity extends AppCompatActivity {

    //ListView listView;
    String[] states;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //listView = (ListView) findViewById(R.id.listView);
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            String CounterName = mBundle.getString("CounterName");

            Log.i("debug", CounterName);
            int currentValuer = mBundle.getInt("currentValuer");
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
                String result = "result";
                returnIntent.putExtra("result",result);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }

}
