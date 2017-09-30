package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    ListView listView;
    private ArrayList<Counter> counters = new ArrayList<>();
    private ArrayAdapter<Counter> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*counters.add(new Counter("counter1", 0, "test!!!"));
        counters.add(new Counter("counter2", 0));
        counters.add(new Counter("counter3", 0));
        counters.add(new Counter("counter4", 0));
        counters.add(new Counter("counter5", 0));
        counters.add(new Counter("counter6", 0));
*/
        listView = (ListView) findViewById(R.id.listView);

        /*ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.countries));*/


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, CounterInterface.class);
                //intent.putExtra("CountryName", listView.getItemAtPosition(i).toString());
                intent.putExtra("index",i);
                intent.putExtra("initialValue", counters.get(i).getInitialValue());
                intent.putExtra("date", counters.get(i).getDate());
                intent.putExtra("comment", counters.get(i).getComment());
                intent.putExtra("CounterName", counters.get(i).getName());
                intent.putExtra("currentValuer", counters.get(i).getCurrentValue());
                startActivityForResult(intent,1);
            }
        });

        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, CreateInterface.class);
                startActivityForResult(intent,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                int counterIndex = data.getIntExtra("counterIndex",-1);
                String comment = data.getStringExtra("comment");
                counters.get(counterIndex).setCurrentValue(data.getIntExtra("currentValuer",-1));
                counters.get(counterIndex).setInitialValue(data.getIntExtra("initialValue", -1));
                counters.get(counterIndex).setComment(comment);
                counters.get(counterIndex).setDate();
                saveInFile();

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //delete current counter
                counters.remove(data.getIntExtra("counterIndex",-1));
                mAdapter.notifyDataSetChanged();
                saveInFile();
            }
        }
        else if (requestCode == 2){
            if (resultCode == Activity.RESULT_OK){
                counters.add(new Counter(data.getStringExtra("name"),
                        data.getIntExtra("initialValue",-1),
                        data.getStringExtra("comment")));
                mAdapter.notifyDataSetChanged();
                saveInFile();
            }
        }
    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        mAdapter = new ArrayAdapter<Counter>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                counters);
        listView.setAdapter(mAdapter);
    }
    private void loadFromFile() {
        //ArrayList<String> tweets = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Counter>>() {}.getType();
            counters = gson.fromJson(in, listType);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            counters = new ArrayList<Counter>();
            //e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        //return tweets.toArray(new String[tweets.size()]);
    }
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counters, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
            //e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
            //e.printStackTrace();
        }
    }

}
