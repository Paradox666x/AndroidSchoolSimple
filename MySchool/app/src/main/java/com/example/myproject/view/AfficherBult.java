package com.example.myproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myproject.model.DatabaseHandler;

import java.text.DecimalFormat;

public class AfficherBult extends AppCompatActivity {
    TextView eleveTV;
    EditText eleveemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_bult);



        Intent intent=getIntent();
        String name=intent.getStringExtra("data");
        DatabaseHandler dbh=new DatabaseHandler(this);

         eleveemail = (EditText) findViewById(R.id.edBultain);
        eleveTV = (TextView) findViewById(R.id.TVBultain);


        eleveemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(eleveemail.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Entrer l'email d'eleve",Toast.LENGTH_SHORT).show();

                else {
                    String Result=dbh.getAllModule(eleveemail.getText().toString());
                    double note=Double.parseDouble(Result);

                    eleveTV.setText(new DecimalFormat("##.##").format(note));
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(eleveemail.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Entrer l'email d'eleve",Toast.LENGTH_SHORT).show();

                else {
                    String Result=dbh.getAllModule(eleveemail.getText().toString());
                    double note=Double.parseDouble(Result);
                    eleveTV.setText(new DecimalFormat("##.##").format(note));

                }

            }
        });






















    }
}