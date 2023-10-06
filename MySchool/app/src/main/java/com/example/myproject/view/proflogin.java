package com.example.myproject.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myproject.model.profDatabaseHandler;

public class proflogin extends AppCompatActivity {
    EditText login,pass;
    Button loginprof;
    TextView profError;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proflogin);
        getSupportActionBar().hide();
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#59c5cf"));
        actionBar.setBackgroundDrawable(colorDrawable);




        profDatabaseHandler profdbh=new profDatabaseHandler(this);
        profdbh.getprof("amimi","amimi123");


        login=(EditText)findViewById(R.id.profuser);
        pass=(EditText)findViewById(R.id.profpass);
        profError=(TextView)findViewById(R.id.proferror);




        loginprof=(Button)findViewById(R.id.login2);
        loginprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean profVerify=profdbh.verifyprof(login.getText().toString(),pass.getText().toString());

                if(profVerify)

                {
                    profError.setText("Bienvenue monsieur");
                    Intent intent=new Intent(proflogin.this, profManagement.class);
                    intent.putExtra("pseudo",login.getText().toString());

                    startActivity(intent);

                }


                else
                    profError.setText("Echec du connexion");



            }
        });





    }


}