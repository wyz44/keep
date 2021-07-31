package com.example.keep;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    private String[] from = {"num", "item", "cost"};
    private int[] to = {R.id.num, R.id.item_name, R.id.cost_number};
    private LinkedList<HashMap<String,String>> data = new LinkedList<>();
    private SimpleAdapter adapter;
    private EditText cost, item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.list);
        initListView();

        //長按出現刪除對話框
        setListItemLongClick();

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


    private void setListItemLongClick() {
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {
                //定義AlertDialog.Builder物件，當長按列表項的時候彈出確認刪除對話方塊
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("確定刪除?");
                builder.setTitle("提示");

                //新增AlertDialog.Builder物件的setPositiveButton()方法
                builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(data.remove(position)!=null){
                            System.out.println("success");
                        }else {
                            System.out.println("failed");
                        }
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getBaseContext(), "刪除列表項", Toast.LENGTH_SHORT).show();
                    }
                });

                //新增AlertDialog.Builder物件的setNegativeButton()方法
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.create().show();
                return true;
            }
        });
    }


    public void add(View view) {
        HashMap<String,String> d0 = new HashMap<>();
        item = findViewById(R.id.item);
        cost = findViewById(R.id.cost);
        d0.put("num", "" + (data.size()+1));
        d0.put("item", item.getText().toString());
        d0.put("cost", cost.getText().toString());
        data.add(d0);
        adapter.notifyDataSetChanged();
        item.setText("");
        cost.setText("");
        listview.smoothScrollToPosition(data.size()-1);
    }
}