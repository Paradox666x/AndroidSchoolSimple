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

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myproject.model.DatabaseHandler;

public class Recherchenotes extends AppCompatActivity {
EditText ed;
TextView tvnots;
Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherchenotes);

        DatabaseHandler dbh=new DatabaseHandler(this);
        Intent a=getIntent();
        String data=a.getStringExtra("userdata");
        String[]infos=data.split(" ");


        ed=findViewById(R.id.Recherchemodule);




        String email=infos[2];



        tvnots=findViewById(R.id.textView6);





        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String modulename=ed.getText().toString().toLowerCase();
                String notes=dbh.getModuleData(email,modulename);

                if(!TextUtils.isEmpty(modulename)){
                    tvnots.setText(notes);
                         ed.setError(null);}
                else
                    ed.setError("Veuillez inserer le nom de module correct");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void afterTextChanged(Editable s) {
                String modulename=ed.getText().toString().toLowerCase();
                String notes=dbh.getModuleData(email,modulename);
                if(!TextUtils.isEmpty(modulename)){
                    tvnots.setText(notes);
                    ed.setError(null);
                }
                else
                    ed.setError("Veuillez inserer le nom de module correct");

            }
        });



        bt=findViewById(R.id.homenotes);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }


}