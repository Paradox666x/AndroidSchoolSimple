package com.example.myproject.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myproject.model.DatabaseHandler;

public class Modifienote extends AppCompatActivity {
    EditText email,nomdemodule,ednote1,ednote2,ednote3;
    TextView Affichernotes;
    Button ModifilanoteMod;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifienote);

        Intent intent=getIntent();
        String name=intent.getStringExtra("data");



        DatabaseHandler dbh=new DatabaseHandler(this);








        email=findViewById(R.id.eleveemailmodifiemod);
        nomdemodule=findViewById(R.id.elevemoduleinsertmod);
        Affichernotes=findViewById(R.id.AffichernotesMod);


        nomdemodule.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String modulename=nomdemodule.getText().toString().toLowerCase();
                String emailmail=email.getText().toString().toLowerCase();
                String notes=dbh.getModuleData(emailmail,modulename);

                if(!TextUtils.isEmpty(modulename)){
                    Affichernotes.setText(notes);
                    nomdemodule.setError(null);}
                else
                    nomdemodule.setError("Veuillez inserer le nom de module correct");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                String modulename=nomdemodule.getText().toString().toLowerCase();
                String emailmail=email.getText().toString().toLowerCase();
                String notes=dbh.getModuleData(emailmail,modulename);

                if(!TextUtils.isEmpty(modulename)){
                    Affichernotes.setText(notes);
                    nomdemodule.setError(null);}
                else
                    nomdemodule.setError("Veuillez inserer le nom de module correct");

            }
        });










        //eleve modfie


        ednote1=findViewById(R.id.modifilanote1mod);
        ednote2=findViewById(R.id.modifilanote2mod);
        ednote3=findViewById(R.id.modifilanote3mod);

        ModifilanoteMod=findViewById(R.id.modifilanotemod);
        ModifilanoteMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(email) && isEmpty(nomdemodule) && isEmpty(email) && isEmpty(ednote1) && isEmpty(ednote2) && isEmpty(ednote3))
                    ModifilanoteMod.setError("Iserer tous les notes");

                else   {    dbh.updateModule(nomdemodule.getText().toString(),
                        Double.parseDouble(ednote1.getText().toString()),
                        Double.parseDouble(ednote2.getText().toString()),
                        Double.parseDouble(ednote3.getText().toString()),
                        email.getText().toString().toLowerCase());
                        Toast.makeText(getApplicationContext(),"note modifiée",Toast.LENGTH_SHORT).show();
                }

            }
        });















    }


    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}