package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class MainApp extends AppCompatActivity {

    TextView tv2;
    Button b1;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        Date currentTime = Calendar.getInstance().getTime();

        tv2 = findViewById(R.id.user2name);
        b1 = findViewById(R.id.button2);
        lv = findViewById(R.id.listView);
        ArrayList<String> msg = new ArrayList<>();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.activity_list_item, android.R.id.text1,msg);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str;
                str = tv2.getText().toString() +" "+currentTime.toString();
                msg.add(str);
                lv.setAdapter(adapter);
                tv2.setText(" ");
            }
        });
    }
}