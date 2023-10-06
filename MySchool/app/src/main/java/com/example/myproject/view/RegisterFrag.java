package com.example.myproject.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myproject.model.DatabaseHandler;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText nom,prenom,email,pass,passconf;
    private Button conexion;


    public RegisterFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFrag newInstance(String param1, String param2) {

        RegisterFrag fragment = new RegisterFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);




         conexion = view.findViewById(R.id.login2);
        conexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                nom = view.findViewById(R.id.Nom);
                prenom= view.findViewById(R.id.Prenom);
                email= view.findViewById(R.id.email);
                pass= view.findViewById(R.id.pass);
                passconf= view.findViewById(R.id.passconf);


                String n=nom.getText().toString();
                String p=prenom.getText().toString();
                String e=email.getText().toString();
                String pa=pass.getText().toString();
                String pc=passconf.getText().toString();



                if(TextUtils.isEmpty(n)) nom.setError("Le champ ne doit pas etre vide");
                else if(TextUtils.isEmpty(p)) prenom.setError("Le champ ne doit pas etre vide");
                else if(TextUtils.isEmpty(e)) email.setError("Le champ ne doit pas etre vide");
                else if(TextUtils.isEmpty(pa)) pass.setError("Le champ ne doit pas etre vide");
                else if(TextUtils.isEmpty(pc)) passconf.setError("Le champ ne doit pas etre vide");





                else if(!pa.equals(pc)) passconf.setError("le mot de pass est incompatibe avec le confirmation");
                else {

                    Intent intent = new Intent(getActivity(), MainActivity.class);

                    DatabaseHandler dbh=new DatabaseHandler(getActivity());
                    dbh.insertData(n,p,e,pa);


                    startActivity(intent);

                }



            }
        });


        // Inflate the layout for this fragment

        return view;
    }




}