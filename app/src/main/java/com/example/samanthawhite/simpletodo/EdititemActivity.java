package com.example.samanthawhite.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static com.example.samanthawhite.simpletodo.MainActivity.ITEM_POSITION;
import static com.example.samanthawhite.simpletodo.MainActivity.ITEM_TEXT;

public class EdititemActivity extends AppCompatActivity {

    EditText etItemText;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edititem);

        etItemText = (EditText) findViewById(R.id.etItemText);
        etItemText.setText(getIntent().getStringExtra(ITEM_TEXT));

        position = getIntent().getIntExtra(ITEM_POSITION, 0);

        getSupportActionBar().setTitle("Edit Item");


    }

    public void onSaveItem(View v){
        Intent i = new Intent();

        i.putExtra(ITEM_TEXT, etItemText.getText().toString());
        i.putExtra(ITEM_POSITION, position);
        setResult(RESULT_OK, i);
        finish();

    }
}
