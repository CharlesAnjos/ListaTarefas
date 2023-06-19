package io.github.charlesanjos.listatarefas.activities;

import static io.github.charlesanjos.listatarefas.enums.Categoria.*;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import io.github.charlesanjos.listatarefas.R;
import io.github.charlesanjos.listatarefas.dbconnection.BDsqlite;
import io.github.charlesanjos.listatarefas.entities.Afazer;
import io.github.charlesanjos.listatarefas.enums.Categoria;
import io.github.charlesanjos.listatarefas.fragments.AfazeresFragment;

public class ListActivity extends AppCompatActivity {

    BDsqlite bd;
    ListView listaAfazeres;
    ArrayAdapter<Afazer> adapter;
    Categoria categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String cat = getIntent().getStringExtra(
                "categoria");
        if(cat != null){
            switch(cat){
                case "casa":
                    Objects.requireNonNull(getSupportActionBar()).setTitle("Afazeres Casa");
                    categoria = CASA;
                    break;
                case "trabalho":
                    Objects.requireNonNull(getSupportActionBar()).setTitle("Afazeres Trabalho");
                    categoria = TRABALHO;
                    break;
                case "estudo":
                    Objects.requireNonNull(getSupportActionBar()).setTitle("Afazeres Estudo");
                    categoria = ESTUDO;
                    break;
            }
        } else {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Favoritos");
        }

        listaAfazeres = findViewById(R.id.listaAfazeres);

        bd = new BDsqlite(this);
        List<Afazer> afazeres = bd.consultarDados(categoria);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, afazeres);
        listaAfazeres.setAdapter(adapter);
        listaAfazeres.setOnItemClickListener(this::onItemClick);
        listaAfazeres.setOnItemLongClickListener(this::onItemLongClick);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Afazer afazer = (Afazer) parent.getAdapter().getItem(position);
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("Detalhes");
        dialog.setMessage("Nome: " + afazer.getTitulo() + "\nIdade: " + afazer.getCategoria());
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Deletar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAlertDialog(afazer);
                    }
                });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Editar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent addAfazerIntent = new Intent(getActivity(),
                                AddAfazer.class);
                        addAfazerIntent.putExtra("afazer", afazer);
                        startActivity(addAfazerIntent);
                    }
                });
        dialog.show();
    }

    public void deleteAlertDialog(Afazer afazer) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.setTitle("Deletando " + afazer.getTitulo());
        dialog.setMessage("Esta acao e irreversivel. Tem certeza que deseja " +
                "prosseguir?");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Deletar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bd.excluir(afazer.getId());
                        adapter.remove(afazer);
                        adapter.notifyDataSetChanged();
                    }
                });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        dialog.show();
    }

    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Afazer afazer = (Afazer) parent.getAdapter().getItem(position);
        Toast.makeText(this, afazer.getTitulo(), Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_activity_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent mainActivityIntent = new Intent(getActivity(),
                        MainActivity.class);
                startActivity(mainActivityIntent);
                finish();
                break;

            case R.id.add:
                Intent addAfazerIntent = new Intent(getActivity(),
                        AddAfazer.class);
                addAfazerIntent.putExtra("categoria",categoria.toString());
                startActivity(addAfazerIntent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addAfazerDialog(){
        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Mensagem")
                .setTitle("Título");

// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        AlertDialog dialog = builder.create();
    }

    public Context getActivity() {
        return this;
    }
}