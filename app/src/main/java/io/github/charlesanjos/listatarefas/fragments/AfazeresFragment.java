package io.github.charlesanjos.listatarefas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.charlesanjos.listatarefas.R;
public class AfazeresFragment extends Fragment {

    public AfazeresFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment (converte fragment-home em view para exibir ao usuário)
        return inflater.inflate(R.layout.fragment_afazeres, container, false);

    }
}