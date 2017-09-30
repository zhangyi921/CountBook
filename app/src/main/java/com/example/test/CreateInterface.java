package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateInterface extends AppCompatActivity {
    EditText name;
    EditText initialValue;
    EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_interface);
        name = (EditText) findViewById(R.id.name);
        initialValue = (EditText) findViewById(R.id.InitialValue);
        comment = (EditText) findViewById(R.id.comment);
        Button create = (Button) findViewById(R.id.add);
        create.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent returnIntent = new Intent();
                returnIntent.putExtra("name",name.getText().toString());
                Integer temp = new Integer(initialValue.getText().toString());
                returnIntent.putExtra("initialValue", temp);
                returnIntent.putExtra("comment", comment.getText().toString());
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

    }
}
