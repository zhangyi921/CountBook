package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private ArrayList<Counter> counters = new ArrayList<>();
    private ArrayAdapter<Counter> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counters.add(new Counter("counter1", 0, "test!!!"));
        counters.add(new Counter("counter2", 0));
        counters.add(new Counter("counter3", 0));
        counters.add(new Counter("counter4", 0));
        counters.add(new Counter("counter5", 0));
        counters.add(new Counter("counter6", 0));

        listView = (ListView) findViewById(R.id.listView);

        /*ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.countries));*/
        mAdapter = new ArrayAdapter<Counter>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                counters);

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
        listView.setAdapter(mAdapter);
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
//                if (counters.get(counterIndex).getCurrentValue() == 5){
//                    Log.i("debug","current value has been reset");
//                }

                //Log.i("debug", result2);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //delete current counter
                counters.remove(data.getIntExtra("counterIndex",-1));
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}