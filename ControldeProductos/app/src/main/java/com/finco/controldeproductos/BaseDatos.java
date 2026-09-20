package com.finco.controldeproductos;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {
    public BaseDatos(Context context){
        super(
                context,
                "tienda.db",
                null,
                1
        );
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE productos (" +
                        "prodcodid INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "proddeno TEXT NOT NULL," +
                        "prodcate TEXT NOT NULL," +
                        "prodprec REAL NOT NULL,"+
                        "prodstock INTEGER NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {
        String otroSql=("DROP TABLE IF EXISTS productos");

        db.execSQL(otroSql);
    }

    public long insertarProducto (
            String nombre,
            String categoria,
            Double precio,
            int stock
    ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("proddeno", nombre);
        valores.put("prodcate", categoria);
        valores.put("prodprec", precio);
        valores.put("prodstock", stock);

        return db.insert(
                "productos",
                null,
                valores
        );
    }

    public String listarProductos() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM productos ORDER BY prodcodid",
                null
        );
        String resultado = "";
        while (cursor.moveToNext()) {
            int prodcodid = cursor.getInt(0);
            String proddeno = cursor.getString(1);
            String prodcate = cursor.getString(2);
            double prodprec = cursor.getDouble(3);
            int prodstock = cursor.getInt(4);

            resultado +=
                    "ID: " + prodcodid +
                            "\nProducto: " + proddeno +
                            "\nCategoria: " + prodcate +
                            "\nPrecio: " + prodprec +
                            "\nStock: " + prodstock +
                            "\n-----------------\n";
        }
        cursor.close();
        return resultado;
    }

    public Cursor buscarProductos(int prodcodid){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM productos WHERE prodcodid= ?",
                new String[]{
                        String.valueOf(prodcodid)
                }
        );
    }

    public int actualizarProducto(
            int prodcodid,
            String proddeno,
            String prodcate,
            double prodprec,
            int prodstock
    ){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("prodcodid", prodcodid);
        valores.put("proddeno", proddeno);
        valores.put("prodcate", prodcate);
        valores.put("prodprec", prodprec);
        valores.put("prodstock", prodstock);

        return db.update(
                "productos",
                valores,
                "prodcodid=?",
                new String[]{String.valueOf(prodcodid)}
        );
    }

    public int eliminarProducto(int prodcodid){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(
                "productos",
                "prodcodid=?",
                new String[]{
                        String.valueOf(prodcodid)
                }
        );
    }

}
