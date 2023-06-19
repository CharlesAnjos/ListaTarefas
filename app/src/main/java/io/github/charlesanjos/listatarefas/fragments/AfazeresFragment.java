package io.github.charlesanjos.listatarefas.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import io.github.charlesanjos.listatarefas.R;
import io.github.charlesanjos.listatarefas.activities.ListActivity;
import io.github.charlesanjos.listatarefas.enums.Categoria;

public class AfazeresFragment extends Fragment {

    View view;
    Button botaoCasa, botaoTrabalho, botaoEstudos, botaoFavoritos;

    public AfazeresFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_afazeres, container, false);

        botaoCasa = view.findViewById(R.id.botaoCasa);
        botaoTrabalho = view.findViewById(R.id.botaoTrabalho);
        botaoEstudos = view.findViewById(R.id.botaoEstudos);
        botaoFavoritos = view.findViewById(R.id.botaoFavoritos);

        botaoCasa.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),ListActivity.class);
            intent.putExtra("categoria", "casa");
            startActivity(intent);
        });

        botaoTrabalho.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),ListActivity.class);
            intent.putExtra("categoria", "trabalho");
            startActivity(intent);
        });

        botaoEstudos.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),ListActivity.class);
            intent.putExtra("categoria", "estudo");
            startActivity(intent);
        });

        botaoFavoritos.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),ListActivity.class);
            intent.putExtra("favoritos", true);
            startActivity(intent);
        });
        
        // Inflate the layout for this fragment (converte fragment-home em view para exibir ao usuário)
        return view;
    }
}