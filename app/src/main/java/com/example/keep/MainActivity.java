package com.example.keep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    private String[] from = {"title", "num"};
    private int[] to = {R.id.item_title, R.id.num};
    private LinkedList<HashMap<String,String>> data = new LinkedList<>();
    private SimpleAdapter adapter;
    private EditText scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.list);
        initListView();
    }

    private void initListView(){
        HashMap<String,String> d0 = new HashMap<>();
        d0.put("title", "text1");
        d0.put("num", "" + (data.size()+1));
        data.add(d0);

        HashMap<String,String> d1 = new HashMap<>();
        d1.put("title", "text2");
        d1.put("num", "" + (data.size()+1));
        data.add(d1);

        HashMap<String,String> d2 = new HashMap<>();
        d2.put("title", "text3");
        d2.put("num", "" + (data.size()+1));
        data.add(d2);

        adapter = new SimpleAdapter(this, data, R.layout.item,from,to);
        listview.setAdapter(adapter);
    }

    public void add(View view) {
        HashMap<String,String> d0 = new HashMap<>();
        scan = findViewById(R.id.scan);
        d0.put("title", scan.getText().toString());
        d0.put("num", "" + (data.size()+1));
        data.add(d0);
        adapter.notifyDataSetChanged();
        scan.setText("");
        listview.smoothScrollToPosition(data.size()-1);
    }

    public void del(View view) {
        if(data.size()>0){
            data.remove(0);
        }
        adapter.notifyDataSetChanged();
    }
}