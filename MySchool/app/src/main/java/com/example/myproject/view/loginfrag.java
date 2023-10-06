package com.example.myproject.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myproject.model.DatabaseHandler;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link loginfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class loginfrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText mail,password;
    private TextView error;
    private Button login;

    public loginfrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment loginfrag.
     */
    // TODO: Rename and change types and number of parameters
    public static loginfrag newInstance(String param1, String param2) {
        loginfrag fragment = new loginfrag();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_loginfrag, container, false);


        error=view.findViewById(R.id.error);
        mail= view.findViewById(R.id.email);
        password= view.findViewById(R.id.pass);
        login=view.findViewById(R.id.login2);
        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String email=mail.getText().toString();
                String pass=password.getText().toString();

                DatabaseHandler dbh=new DatabaseHandler(getActivity());
                boolean ver=dbh.verifyUser(email,pass);


                if(ver){
                Intent n=new Intent(getActivity(), transaction.class);
                n.putExtra("emaillog",email);

                startActivity(n);}

                else {

                    password.setError("le mot de pass ou email incorrect");
                   error.setText("Erreur de connexion !");

                }


            }
        });



        return view;
    }
}