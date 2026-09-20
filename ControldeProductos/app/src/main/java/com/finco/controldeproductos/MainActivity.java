package com.finco.controldeproductos;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editid;
    EditText editnombre;
    EditText editcategoria;
    EditText editprecio;
    EditText editstock;
    Button btnRegistrar;
    Button btnId;
    Button btnActualizar;
    Button btnEliminar;
    Button btnListar;
    Button btnLimpiar;
    TextView txtRegistrados;

    BaseDatos baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editid=findViewById(R.id.tdtID);
        editnombre=findViewById(R.id.tdtNombre);
        editcategoria=findViewById(R.id.tdtCategoría);
        editprecio=findViewById(R.id.tdtPrecio);
        editstock=findViewById(R.id.tdtStock);

        btnRegistrar=findViewById(R.id.btnRegistrar);
        btnId=findViewById(R.id.btnID);
        btnActualizar=findViewById(R.id.btnActualizar);
        btnEliminar=findViewById(R.id.btnEliminar);
        btnListar=findViewById(R.id.btnListar);
        btnLimpiar=findViewById(R.id.btnLimpiar);
        txtRegistrados=findViewById(R.id.txtRegistrados);

        baseDatos = new BaseDatos(this);

        btnRegistrar.setOnClickListener(v->{

            String name = editnombre.getText().toString().trim();
            String categoria = editcategoria.getText().toString().trim();
            String price = editprecio.getText().toString().trim();
            String existencias = editstock.getText().toString().trim();

            double precio = Double.parseDouble(price);
            int stock = Integer.parseInt(existencias);

            long resultado = baseDatos.insertarProducto(
                    name, categoria, precio, stock
            );

            if (resultado !=-1){
                Toast.makeText(
                        this,
                        "Producto Registrado",
                        Toast.LENGTH_SHORT
                ).show();
                limpiarDatos()
            }

        });

        btnId.setOnClickListener(v-> {
            String idprodcodi = editid.getText().toString().trim();

            int prodcodibusca = Integer.parseInt(idprodcodi);

            Cursor cursordevuelto = baseDatos.buscarProductos(prodcodibusca);

            if (cursordevuelto.moveToFirst()) {

                editnombre.setText(cursordevuelto.getString(1));
                editcategoria.setText(cursordevuelto.getString(2));
                editprecio.setText(String.valueOf(cursordevuelto.getString(3)));
                editstock.setText(String.valueOf(cursordevuelto.getString(4)));
            }
        });

        btnActualizar.setOnClickListener(v->{

            String IdStr = editid.getText().toString().trim();
            String name = editnombre.getText().toString().trim();
            String categoria = editcategoria.getText().toString().trim();
            String price = editprecio.getText().toString().trim();
            String existencias = editstock.getText().toString().trim();

            Double precio = Double.parseDouble(price);
            int stock = Integer.parseInt(existencias);
            int Id = Integer.parseInt(IdStr);
            int filas = baseDatos.actualizarProducto(
                    Id, name, categoria, precio, stock
            );

            if (filas>0){
                Toast.makeText(
                        this,
                        "Producto Actualizado",
                        Toast.LENGTH_SHORT
                ).show();
                limpiarDatos();

            }



        });

        btnEliminar.setOnClickListener(v->{

            String idStr = editid.getText().toString().trim();

            int Id = Integer.parseInt(idStr);

            new AlertDialog.Builder(this)
                    .setTitle("Eliminar Producto")
                    .setMessage("¿Esta seguro de eliminar este producto?")
                    .setPositiveButton(
                            "Eliminar",
                            (dialog, witch)-> {
                                int filas = baseDatos.eliminarProducto(Id);
                                if (filas > 0) {
                                    Toast.makeText(
                                            this,
                                            "Producto Eliminado",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                    limpiarDatos();
                                } else {
                                    Toast.makeText(
                                            this,
                                            "Producto no encontrado",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }
                            }
                    )
                    .setNegativeButton(
                            "Cancelar",
                            null
                    ).show();

            });

        btnLimpiar.setOnClickListener(v->{
            limpiarDatos();

        });

        btnListar.setOnClickListener(v->{
            String productos = baseDatos.listarProductos();
            txtRegistrados.setText(productos);

        });

    }
    private void limpiarDatos(){
        editid.setText("");
        editnombre.setText("");
        editcategoria.setText("");
        editprecio.setText("");
        editstock.setText("");

    }

}