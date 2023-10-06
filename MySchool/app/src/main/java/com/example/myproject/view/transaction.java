package com.example.myproject.view;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myproject.model.DatabaseHandler;
import com.example.myproject.view.MainActivity;
import com.example.myproject.view.Profil;
import com.example.myproject.view.Recherchenotes;

public class transaction extends AppCompatActivity {
    String emaillog;
    TextView tv;
    Button bt;
    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);















        Intent i =getIntent();
        DatabaseHandler dbh=new DatabaseHandler(this);
        emaillog= i.getStringExtra("emaillog");
        tv=findViewById(R.id.textView5);
        String data=dbh.getUser(emaillog);
        String[]infos=data.split(" ");
        tv.setText(infos[0]+" "+infos[1]);




            //intent to notes
        Intent a=new Intent(this, Recherchenotes.class);
        a.putExtra("userdata",data);
        bt=findViewById(R.id.affichernotes);
    bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(a);

            }
        });





    //menu





    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.profilmenu) {

            Intent profilintent=new Intent(this, Profil.class);
            profilintent.putExtra("mail",emaillog);
            startActivity(profilintent);


            return true;
        }
        if (id == R.id.infomenu) {
            // Handle the settings action
            return true;
        }

        //deconnexion
        if (id == R.id.dcmenu) {
            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}