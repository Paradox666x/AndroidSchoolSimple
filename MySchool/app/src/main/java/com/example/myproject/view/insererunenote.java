package com.example.myproject.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myproject.model.DatabaseHandler;

public class insererunenote extends AppCompatActivity {
    EditText email,nomdemodule,note1,note2,note3;
    Button insertnote ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insererunenote);

        Intent intent=getIntent();
        String name=intent.getStringExtra("data");
        DatabaseHandler dbh=new DatabaseHandler(this);


        email=findViewById(R.id.Eleveemailinsert);
        nomdemodule=findViewById(R.id.elevemoduleinsertmod);
        note1=findViewById(R.id.EleveNOTEinsert);
            note2=findViewById(R.id.EleveNOTEinsert2);
            note3=findViewById(R.id.EleveNOTEinsert3);


         String emailEleve=nomdemodule.getText().toString();





        insertnote=findViewById(R.id.insererlanote);
            insertnote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!emailEleve.equals("")){
                        nomdemodule.setError("Veuillez remplir tous les champs");
                    }
                    else{
                        dbh.insertModule(emailEleve,StringDouble(note1),StringDouble(note2),StringDouble(note3),emailEleve);
                        Toast.makeText(getApplicationContext(),"note insérée ",Toast.LENGTH_LONG).show();
                    }

                }
            });






    }

    private double StringDouble(EditText editText){
        String result=editText.getText().toString();
        return Double.parseDouble(result);

    }
}