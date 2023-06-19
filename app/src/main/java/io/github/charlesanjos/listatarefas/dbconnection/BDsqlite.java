package io.github.charlesanjos.listatarefas.dbconnection;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.github.charlesanjos.listatarefas.entities.Afazer;
import io.github.charlesanjos.listatarefas.enums.Categoria;

public class BDsqlite extends SQLiteOpenHelper {

    public static final String BD_NAME = "afazeres";
    //If you change the database schema, you must increment the database version.
    public static final int BD_VERSAO = 1;

    public BDsqlite(@Nullable Context context) {
        super(context, BD_NAME, null, BD_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE AFAZER(");
        query.append(" ID INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" TITULO TEXT NOT NULL,");
        query.append(" DATACRIADO DATETIME NOT NULL,");
        query.append(" CATEGORIA TEXT NOT NULL,");
        query.append(" ISFAVORITO TINYINT,");
        query.append(" ISCOMPLETO TINYINT,");
        query.append(" DATACOMPLETO DATETIME)");

        db.execSQL(query.toString());
    }

    /**
     * Called when the database needs to be upgraded. The implementation should use this method to drop tables,
     * add tables, or do anything else it needs to upgrade to the new schema version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserirDados(){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("NOME", "Afazer 1"); // COLUNA / VALOR
        values.put("IDADE", "22");
        db.insert("PESSOA",null,values);

    }

    public void inserirAfazer(Afazer afazer){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("TITULO", afazer.getTitulo()); // COLUNA / VALOR
        values.put("DATACRIADO", afazer.getDataCriado().toString());
        values.put("CATEGORIA", afazer.getCategoria().toString());
        values.put("ISFAVORITO", afazer.isFavorito());
        values.put("ISCOMPLETO", afazer.isCompleto());
        if(afazer.isCompleto()){
            values.put("DATACOMPLETO", afazer.getDataCompleto().toString());
        }else{
            values.put("DATACOMPLETO", "");
        }
        db.insert("AFAZER",null,values);

    }

    @SuppressLint("Range")
    public List<Afazer> consultarDados(Categoria categoria){
        SQLiteDatabase dbselec = getReadableDatabase();

        String[] colunas = {
                "ID",
                "TITULO",
                "DATACRIADO",
                "CATEGORIA",
                "ISFAVORITO",
                "ISCOMPLETO",
                "DATACOMPLETO"
        };

        String where = null;
        String[] dadosWhere = null;


        if(categoria != null){
            where = "CATEGORIA = '" + categoria.name() + "'";
        } else {
            where = "ISFAVORITO = 1";
        }

        Cursor cursor = dbselec.query(
                "AFAZER",  // The table to query
                colunas,        // The array of columns to return (pass null to get all)
                where,    // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,          // don't group the rows
                null,          // don't filter by row groups
                null           // The sort order
        );

        List<Afazer> afazeres = new ArrayList<>();
        while(cursor.moveToNext()) {
            Afazer a=new Afazer();
            a.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            a.setTitulo(cursor.getString(cursor.getColumnIndex("TITULO")));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                a.setDataCriado(LocalDateTime.parse(cursor.getString(cursor.getColumnIndex(
                        "DATACRIADO"))));
            }
            a.setCategoria(Categoria.valueOf(cursor.getString(cursor.getColumnIndex("CATEGORIA"))));
            a.setFavorito(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("ISFAVORITO"))));
            a.setCompleto(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("ISCOMPLETO"))));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if(a.isCompleto()){
                    a.setDataCompleto(LocalDateTime.parse(cursor.getString(cursor.getColumnIndex(
                            "DATACOMPLETO"))));
                } else {
                    a.setDataCompleto(null);
                }
            }
            afazeres.add(a);
        }

        cursor.close();
        return afazeres;
    }

    public void excluir(int id){
        SQLiteDatabase db = getReadableDatabase();
        // Define 'where' part of query.
        String selection = "ID LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(id) };
        // Issue SQL statement.
        db.delete("AFAZER", selection, selectionArgs);
    }

    public void update(int id, String coluna, String valor){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(coluna, valor);

        // Which row to update, based on the title
        String selection = "ID LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };

        int count = db.update(
                "AFAZER",
                values,
                selection,
                selectionArgs);
    }
}