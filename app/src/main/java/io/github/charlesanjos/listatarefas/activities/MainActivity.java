package io.github.charlesanjos.listatarefas.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.github.charlesanjos.listatarefas.R;
import io.github.charlesanjos.listatarefas.fragments.HomeFragment;
import io.github.charlesanjos.listatarefas.fragments.SobreFragment;

public class MainActivity extends AppCompatActivity {

    Button buttonHome,buttonSobre;
    HomeFragment homeFragment;
    SobreFragment sobreFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //retirar sombra da ActionBar entre botões do fragments
        getSupportActionBar().setElevation(0);

        buttonHome = findViewById(R.id.buttonHome);
        buttonSobre = findViewById(R.id.buttonSobre);
        homeFragment = new HomeFragment();
        sobreFragment = new SobreFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameExemplo,homeFragment);
        transaction.commit();

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameExemplo,homeFragment);
                transaction.commit();
            }
        });

        buttonSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameExemplo,sobreFragment);
                transaction.commit();
            }
        });

    }
}