package io.github.charlesanjos.listatarefas.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.github.charlesanjos.listatarefas.R;
import io.github.charlesanjos.listatarefas.fragments.AfazeresFragment;
import io.github.charlesanjos.listatarefas.fragments.CompletosFragment;

public class MainActivity extends AppCompatActivity {

    Button buttonAfazeres,buttonCompletos;
    AfazeresFragment afazeresFragment;
    CompletosFragment completosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //retirar sombra da ActionBar entre botões do fragments
        getSupportActionBar().setElevation(0);

        buttonAfazeres = findViewById(R.id.buttonAfazeres);
        buttonCompletos = findViewById(R.id.buttonCompletos);
        afazeresFragment = new AfazeresFragment();
        completosFragment = new CompletosFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameExemplo,afazeresFragment);
        transaction.commit();

        buttonAfazeres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameExemplo,afazeresFragment);
                transaction.commit();
            }
        });

        buttonCompletos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameExemplo,completosFragment);
                transaction.commit();
            }
        });

    }
}