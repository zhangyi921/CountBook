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
/*
* this is the interface that user can do all the interaction with a counter
* including increment, decrement, reset,delete the counter
* */
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

        /*
        * here extract all data fom bundle
        * */
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
        /*
        * goBack button means save and go back
        * it put all data in a bundle and return it to main activity
        * */
        Button goBack = (Button) findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.i("debug","button pressed");
                Intent returnIntent = new Intent();
                //String result = "result1";
                returnIntent.putExtra("counterIndex",counterIndex);
                Integer temp = new Integer(InitialVal.getText().toString());
                returnIntent.putExtra("initialValue", temp);
                temp = new Integer(CurrentVal.getText().toString());
                returnIntent.putExtra("currentValuer", temp);
                returnIntent.putExtra("comment",Comment.getText().toString());
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
        /*
        * delete the current conter
        * */
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
        /*
        * increment the current value, based on the current value in the
        * textView box
        * */
        Button increa = (Button) findViewById(R.id.increa);
        increa.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Integer temp = new Integer(CurrentVal.getText().toString());
                temp = temp+1;
                currentValuer = temp.intValue();
                CurrentVal.setText(temp.toString());

            }
        });
        /*
        * decrement the current value based on the current value in the
        * textView box
        * */
        Button decrea = (Button) findViewById(R.id.decrea);
        decrea.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (currentValuer > 0){
                    Integer temp = new Integer(CurrentVal.getText().toString());
                    temp = temp-1;
                    currentValuer = temp.intValue();
                    CurrentVal.setText(temp.toString());
                }
                else{
                    Toast.makeText(CounterInterface.this,"Cannot decrement any more!",Toast.LENGTH_LONG).show();

                }

            }
        });
        /*
        * reset the current value to its initial value
        * */
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
