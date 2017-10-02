package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
* when user clicked created new counter, user will be lead to this activity
* user are prompted to enter proper data for the new counter
* once user finished entering the data for a counter, this will return all data
* to main activity
* */
public class CreateInterface extends AppCompatActivity {
    private EditText name;
    private EditText initialValue;
    private EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_interface);
        name = (EditText) findViewById(R.id.name);
        initialValue = (EditText) findViewById(R.id.InitialValue);
        comment = (EditText) findViewById(R.id.comment);
        Button create = (Button) findViewById(R.id.add);
        /*
        * get data form interface
        * send them to main activity interface
        * */
        create.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //alertDialog.show();
                Intent returnIntent = new Intent();
                String Name = name.getText().toString();
                if (!Name.isEmpty()){
                    returnIntent.putExtra("name",Name);
                    String INITIA = initialValue.getText().toString();
                    if (INITIA.isEmpty()){
                        Toast.makeText(CreateInterface.this,"Initial value field cannot be empty",Toast.LENGTH_LONG).show();

                    }
                    else{
                        Integer temp = new Integer(INITIA);
                        returnIntent.putExtra("initialValue", temp);
                        returnIntent.putExtra("comment", comment.getText().toString());
                        setResult(Activity.RESULT_OK,returnIntent);
                        finish();
                    }

                }
                else{
                    Toast.makeText(CreateInterface.this,"Name field cannot be empty",Toast.LENGTH_LONG).show();

                }

            }
        });

    }
}
