package io.github.charlesanjos.listatarefas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.github.charlesanjos.listatarefas.R;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }
}