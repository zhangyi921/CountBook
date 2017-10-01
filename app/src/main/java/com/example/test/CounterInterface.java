package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CounterInterface extends AppCompatActivity {
    private int currentValuer;
    private String comment;
    private int counterIndex;
    private int initialValue;
    private EditText InitialVal;
    private EditText CurrentVal;
    private EditText Comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_interface);
        TextView name = (TextView) findViewById(R.id.name);
        TextView date = (TextView) findViewById(R.id.Date);
        InitialVal = (EditText) findViewById(R.id.InitialVal);
        CurrentVal = (EditText) findViewById(R.id.CurrentVal);
        Comment = (EditText) findViewById(R.id.Comment);


        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            String CounterName = mBundle.getString("CounterName");
            name.setText("Name: "+CounterName);
            String Date = mBundle.getString("date");
            date.setText("Date: "+Date);
            counterIndex = mBundle.getInt("index");
            initialValue = mBundle.getInt("initialValue");
            Integer temp = new Integer(initialValue);
            InitialVal.setText(temp.toString());
            comment = mBundle.getString("comment");
            Comment.setText(comment);
            currentValuer = mBundle.getInt("currentValuer");
            temp = new Integer(currentValuer);
            CurrentVal.setText(temp.toString());

        }

        Button goBack = (Button) findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.i("debug","button pressed");
                Intent returnIntent = new Intent();
                //String result = "result1";
                returnIntent.putExtra("counterIndex",counterIndex);
                Integer temp = new Integer(InitialVal.getText().toString());
                returnIntent.putExtra("initialValue", temp);
                returnIntent.putExtra("currentValuer", currentValuer);
                returnIntent.putExtra("comment",Comment.getText().toString());
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
        Button delet = (Button) findViewById(R.id.delet);
        delet.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.i("debug","button pressed");
                Intent returnIntent = new Intent();
                returnIntent.putExtra("counterIndex",counterIndex);
                setResult(Activity.RESULT_CANCELED,returnIntent);
                finish();
            }
        });
        Button increa = (Button) findViewById(R.id.increa);
        increa.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                currentValuer = currentValuer+1;
                Integer temp = new Integer(currentValuer);
                CurrentVal.setText(temp.toString());

            }
        });
        Button decrea = (Button) findViewById(R.id.decrea);
        decrea.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (currentValuer > initialValue){
                    currentValuer = currentValuer-1;
                    Integer temp = new Integer(currentValuer);
                    CurrentVal.setText(temp.toString());
                }
                else{
                    Toast.makeText(CounterInterface.this,"Cannot decrement any more!",Toast.LENGTH_LONG).show();

                }

            }
        });
        Button reset = (Button) findViewById(R.id.Rest);
        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                currentValuer = initialValue;
                Integer temp = new Integer(currentValuer);
                CurrentVal.setText(temp.toString());
            }
        });
    }

}
