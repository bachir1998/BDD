package com.example.bdd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class FragmentPlanete extends DialogFragment
{

    private EditText nom,taille;
    private TextView label_nom,label_taille;
    private Button ajout_btn;
    public FragmentPlanete() {
        // le fragment est créé par la méthode newInstance
    }


    public static FragmentPlanete newInstance(String title) {

        FragmentPlanete frag = new FragmentPlanete();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.fragment_planete, container);

    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.fragment_carte_bancaire, container, false);
        super.onViewCreated(view, savedInstanceState);


        //payPalFragmentListener = (PayPalFragmentListener)getActivity();
        label_nom = view.findViewById(R.id.label_planete);
        label_taille = view.findViewById(R.id.label_taille);

        nom= (EditText) view.findViewById(R.id.nom);
        taille= (EditText) view.findViewById(R.id.taille);
        ajout_btn = view.findViewById(R.id.ajout_btn);

        MainActivity activity = (MainActivity)getActivity();

       ajout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Planete p = new Planete(123,nom.getText().toString(),taille.getText().toString(),R.drawable.uranus);
                activity.onInsertPlanete(p);

            }
        });



    }








}
