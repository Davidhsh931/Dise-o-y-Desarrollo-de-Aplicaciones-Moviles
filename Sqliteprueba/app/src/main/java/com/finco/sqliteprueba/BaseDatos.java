package com.finco.sqliteprueba;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;

public class BaseDatos  extends SQLiteOpenHelper {
    public BaseDatos(Context context){
        super (
                context,
                "senati.db",
                null,
                1
        );
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql=
                "CREATE TABLE alumnos ("+
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "nombre TEXT,"+
                        "edad INTEGER,"+
                        "carrera TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion){
        String otroSQL=
                "DROP TABLE IF EXISTS alumno";
        db.execSQL(otroSQL);
    }

    public long insertarAlumno(String nombre,
                               int edad,
                               String carrera
    ){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        valores.put("edad", edad);
        valores.put("carrera", carrera);

        return db.insert(
                "alumnos",
                null,
                valores
        );
    }

    public String listarAlumnos(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM alumnos",
                null
        );
        String resultado="";
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String edad = cursor.getString(2);
            String carrera = cursor.getString(3);

            resultado += id + "-"+
                    nombre+"-"+
                    edad+"años - "+
                    carrera+"\n";
        }
        cursor.close();

        return resultado;
    }
}
