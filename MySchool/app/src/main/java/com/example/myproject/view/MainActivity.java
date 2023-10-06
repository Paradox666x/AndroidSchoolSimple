package com.example.myproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.R;
import com.example.myproject.model.DatabaseHandler;
import com.example.myproject.model.profDatabaseHandler;

public class MainActivity extends AppCompatActivity {
        Button login,register,prof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setIcon(R.drawable.tech);
        getSupportActionBar().setTitle("Your School");

        DatabaseHandler dbh= new DatabaseHandler(this);

        dbh.insertData("Ayman","Lfaquir","aymanfqr@gmail.com","ayman123");


        profDatabaseHandler profdbh=new profDatabaseHandler(this);
        profdbh.insertprof("Amimi","abderahman","Amimi","amimi123");

        dbh.insertModule("python",18,19,19,"aymanfqr@gmail.com");
        dbh.insertModule("java",18,19,19,"aymanfqr@gmail.com");
        dbh.insertModule("javascript",18,19,19,"aymanfqr@gmail.com");
        dbh.insertModule("html",18,19,19,"aymanfqr@gmail.com");
        dbh.insertModule("php",18,19,19,"aymanfqr@gmail.com");
        dbh.insertModule("mysql",18,19,19,"aymanfqr@gmail.com");




     //   Toast.makeText(this,result,Toast.LENGTH_SHORT).show();

       // String result=dbh.getUser("aymanfqr@gmail.com");














        login=findViewById(R.id.login);
        register=findViewById(R.id.registerc);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.fragmentContainerView2, loginfrag.class,null)
                        .setReorderingAllowed(true).addToBackStack("login").commit();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.fragmentContainerView2, RegisterFrag.class,null)
                        .setReorderingAllowed(true).addToBackStack("Register").commit();


            }
        });

         prof=findViewById(R.id.prof);
         prof.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {
                 Intent intent=new Intent();
                 intent.setClass(MainActivity.this, proflogin.class);
                 startActivity(intent);
             }
         });





    }
}