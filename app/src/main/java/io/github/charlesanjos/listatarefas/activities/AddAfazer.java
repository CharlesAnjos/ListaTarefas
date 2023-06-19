package io.github.charlesanjos.listatarefas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDateTime;
import java.util.Objects;

import io.github.charlesanjos.listatarefas.R;
import io.github.charlesanjos.listatarefas.dbconnection.BDsqlite;
import io.github.charlesanjos.listatarefas.entities.Afazer;
import io.github.charlesanjos.listatarefas.enums.Categoria;

public class AddAfazer extends AppCompatActivity {

    BDsqlite bd;
    Categoria categoria;
    TextView tvTituloView;
    TextInputLayout tilTituloAfazer;
    TextInputEditText tietTituloAfazer;
    Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_afazer);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        String catString = getIntent().getStringExtra(
                "categoria");
        if(catString != null){
            categoria = Categoria.valueOf(catString);
            getSupportActionBar().setTitle("Novo afazer para "+categoria.toString());
        }

        tvTituloView = findViewById(R.id.tvTituloView);
        tilTituloAfazer = findViewById(R.id.tilTituloAfazer);
        tietTituloAfazer = findViewById(R.id.tietTituloAfazer);
        btSalvar = findViewById(R.id.btSalvar);

        bd = new BDsqlite(this);
        btSalvar.setOnClickListener(adicionarAfazer());

        Afazer afazer = (Afazer) getIntent().getSerializableExtra("afazer");
        if (afazer != null) {
            tvTituloView.setText("Editar Afazer");
            tietTituloAfazer.setText(afazer.getTitulo());
            btSalvar.setText("Editar");
            btSalvar.setOnClickListener(editarAfazer(afazer));
        }
    }

    private View.OnClickListener adicionarAfazer() {
        return new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tituloAfazer = tietTituloAfazer.getEditableText().toString();

                if (!tituloAfazer.equals("")) {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        Afazer afazer = new Afazer(
                                tituloAfazer,
                                LocalDateTime.now(),
                                categoria
                        );
                        tietTituloAfazer.setText("");
                        bd.inserirAfazer(afazer);
                        bd.close();
                    }
                    backToList();

                } else {
                    if (tituloAfazer.equals(""))
                        tilTituloAfazer.setError("Campo Obrigatorio!");
                }
            }
        };
    }

    private View.OnClickListener editarAfazer(Afazer afazer) {
        return new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tituloAfazer = tietTituloAfazer.getEditableText().toString();

                if (!tituloAfazer.equals("")) {
                    afazer.setTitulo(tituloAfazer);
                    bd.update(afazer.getId(), "TITULO", afazer.getTitulo());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        bd.update(afazer.getId(), "DATACRIADO",
                                String.valueOf(LocalDateTime.now()));
                    }
                    bd.close();
                    tietTituloAfazer.setText("");

                    backToList();
                } else {
                    if (tituloAfazer.equals(""))
                        tilTituloAfazer.setError("Campo Obrigatorio!");
                }
            }
        };
    }

    public void backToList(){
        Intent listActivityIntent = new Intent(getActivity(),
                ListActivity.class);
        if(categoria != null){
            listActivityIntent.putExtra("categoria",categoria.toString().toLowerCase());
        }
        startActivity(listActivityIntent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                backToList();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public Context getActivity() {
        return this;
    }
}