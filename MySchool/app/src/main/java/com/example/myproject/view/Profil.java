package com.example.myproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myproject.model.DatabaseHandler;


public class Profil extends AppCompatActivity {
    TextView tv1,tv2,tv3;
    Button ret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil2);
        Intent i =getIntent();
        String email =i.getStringExtra("mail");



        DatabaseHandler dbh=new DatabaseHandler(this);
        String data=dbh.getUser(email);
        String[]infos=data.split(" ");

        tv1=findViewById(R.id.textView);
        tv2=findViewById(R.id.textView2);
        tv3=findViewById(R.id.textView3);



        tv1.setText(infos[0]);
        tv2.setText(infos[1]);
        tv3.setText(infos[2]);
        //Home
        ret=findViewById(R.id.returnHome);
        ret.setOnClickListener(this::gohome);




    }

    private void gohome(View view) {
        finish();
    }

}