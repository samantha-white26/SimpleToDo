package com.example.samanthawhite.simpletodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lvItems = (ListView) findViewById(R.id.lvItems);

        readItems();

        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        lvItems.setAdapter(itemsAdapter);


        setupListViewListerner();






    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");

        //display a notifcation to the user
        Toast.makeText(getApplicationContext(), "Item added to list", Toast.LENGTH_SHORT).show();


    }

    private void setupListViewListerner(){
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();

                writeItems();

                Log.i("MainActivity", "Removed item " + position);
                return true;
            }
        });
    }

    private File getDatafile() {
        return new File(getFilesDir(), "todo.txt");
    }

    private void readItems() {
        try {
            items = new ArrayList<String>(FileUtils.readLines(getDatafile(), Charset.defaultCharset()));
        } catch (IOException e) {
            e.printStackTrace();
            items = new ArrayList<>();
        }
    }

    private void writeItems() {
        try {
            FileUtils.writeLines(getDatafile(), items);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
