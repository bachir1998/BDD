package com.example.bdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentPlaneteListener {

    final String PREFS_NAME = "preferences_file";


    public PlaneteDao planeteDao;
    public ArrayList<Planete> planetes = new ArrayList<>();
    public RecyclerView mRecyclerView;
    private MonRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button button_ajout_planete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_ajout_planete = findViewById(R.id.newPlanete);

        button_ajout_planete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog();

            }
        });

       // tv = findViewById(R.id.tv);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        //mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager=new GridLayoutManager(this,1, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "planetesDB").build();

        planeteDao = db.planeteDao();

        loadData(planeteDao);
    }

    private void loadData(PlaneteDao planeteDao) {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        new Thread(new Runnable() {
            @Override
            public void run() {

                if (settings.getBoolean("is_data_loaded", true)) {
                    initData(planeteDao);
                    settings.edit().putBoolean("is_data_loaded", false).commit();
                }

                List<Planete> planetes = planeteDao.getAll();

                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run()
                    {
                        mAdapter = new MonRecyclerViewAdapter(planetes);
                        mRecyclerView.setAdapter(mAdapter);

                    }

                });

            }
        }).start();

    }

    public void initData(PlaneteDao planeteDao) {



        planetes.add(new Planete(1,"Mercure","4900",R.drawable.uranus));
        planetes.add(new Planete(2,"Venus","12000",R.drawable.uranus));
        planetes.add(new Planete(3,"Terre","12800",R.drawable.uranus));
        planetes.add(new Planete(4,"Mars","6800",R.drawable.uranus));
        planetes.add(new Planete(5,"Jupiter","144000",R.drawable.uranus));
        planetes.add(new Planete(6,"Saturne","120000",R.drawable.uranus));
        planetes.add(new Planete(7,"Uranus","52000",R.drawable.uranus));
        planetes.add(new Planete(8,"Neptune","50000",R.drawable.uranus));
        planetes.add(new Planete(9,"Pluton","2300",R.drawable.uranus));

        for (int index = 0; index < planetes.size(); index++) {
            Planete planete = planetes.get(index);
            planeteDao.insert(planete);
        }
    }

    private void showDialog() {

        FragmentManager fm = getSupportFragmentManager();
        FragmentPlanete pCarteFragment = FragmentPlanete.newInstance("Titre");
        pCarteFragment.show(fm, "nouvelle planète");

    }
    public void onInsertPlanete(Planete p)
    {

        new Thread(new Runnable() {
            @Override
            public void run() {

                planeteDao.insert(p);
                planetes.add(p);
                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run()
                    {

                       //mAdapter = new MonRecyclerViewAdapter(planetes);
                        //mRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();

                    }

                });

            }

    });
    }
}























/*
* public void run() {
                        tv.setText("Il y a [" + planetes.size() + "] Planètes dans la base de données" );
                        for (int i =0; i< planetes.size();i++) {
                            Toast.makeText(MainActivity.this, "Planete = " + planetes.get(i).getNom(), Toast.LENGTH_SHORT).show();
                        }
                    }
*
*
* */