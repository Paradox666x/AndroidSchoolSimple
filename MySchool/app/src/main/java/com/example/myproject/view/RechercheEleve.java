package com.example.myproject.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myproject.model.DatabaseHandler;

public class RechercheEleve extends AppCompatActivity {
EditText eleveemail;
    TextView eleveTV;
    Button ExitRecherecheeleve;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_eleve);

        Intent intent=getIntent();
        String name=intent.getStringExtra("data");
        DatabaseHandler dbh=new DatabaseHandler(this);
            eleveemail=(EditText)findViewById(R.id.RechercheEleveEmail);
        eleveTV=(TextView)findViewById(R.id.RechercheEleveTV);


        eleveemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Toast.makeText(getApplicationContext(),"Verification de base de données...",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {
                eleveTV.setText (dbh.getUserString(eleveemail.getText().toString()));
                Toast.makeText(getApplicationContext(),"Verification reussie",Toast.LENGTH_SHORT).show();

            }
        });

        ExitRecherecheeleve=(Button)findViewById(R.id.ExitRecherecheeleve);
        ExitRecherecheeleve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}