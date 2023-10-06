package com.example.myproject.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myproject.model.profDatabaseHandler;

public class profManagement extends AppCompatActivity {
    TextView tv;
    Button insererunenote,Modifienote,RechercheEleve,AfficherBult,returnHome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_management);



        Intent intent=getIntent();
        String name=intent.getStringExtra("pseudo");
        profDatabaseHandler profdbh=new profDatabaseHandler(this);

        String data=profdbh.getprof(name);
        String[]infos=data.split(" ");


        tv=findViewById(R.id.proftext);
        tv.setText(infos[0]+" "+infos[1]);


        insererunenote=findViewById(R.id.insererunenote);
        Modifienote=findViewById(R.id.Modifienote);
        RechercheEleve=findViewById(R.id.RechercheEleve);
        AfficherBult=findViewById(R.id.AfficherBult);
        returnHome=findViewById(R.id.returnHome);

        insererunenote.setOnClickListener(v -> {
            Intent intent1=new Intent(profManagement.this, com.example.myproject.view.insererunenote.class);
            intent1.putExtra("data",data);
            startActivity(intent1);
        });

        Modifienote.setOnClickListener(v -> {
            Intent intent2=new Intent(profManagement.this, com.example.myproject.view.Modifienote.class);
            intent2.putExtra("data",data);
            startActivity(intent2);
        });

        RechercheEleve.setOnClickListener(v -> {
            Intent intent3=new Intent(profManagement.this, com.example.myproject.view.RechercheEleve.class);
            intent3.putExtra("data",data);
            startActivity(intent3);
        });

            AfficherBult.setOnClickListener(v -> {
                Intent intent4=new Intent(profManagement.this, com.example.myproject.view.AfficherBult.class);
                intent4.putExtra("data",data);
                startActivity(intent4);
            });

            returnHome.setOnClickListener(v -> {
                finish();
            });



    }
}