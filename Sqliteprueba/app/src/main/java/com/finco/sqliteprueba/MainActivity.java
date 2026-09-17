package com.finco.sqliteprueba;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNombre;
    EditText edtEdad;
    EditText edtCarrera;
    Button btnGuardar;
    Button btnMostrar;
    TextView txtLista;
    BaseDatos baseDatos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        edtNombre=findViewById(R.id.tdtNombre);
        edtEdad=findViewById(R.id.tdtEdad);
        edtCarrera=findViewById(R.id.tdtCarrera);
        btnGuardar=findViewById(R.id.btnGuardar);
        btnMostrar=findViewById(R.id.btnMostrar);
        txtLista=findViewById(R.id.txtLista);

        baseDatos = new BaseDatos(this);

        btnGuardar.setOnClickListener(v->{

            String nombre = edtNombre.getText().toString().trim();
            String edadTexto = edtEdad.getText().toString().trim();
            String carrera = edtCarrera.getText().toString().trim();

            int edad = Integer.parseInt(edadTexto);

            long resultado = baseDatos.insertarAlumno(
                    nombre, edad, carrera
            );

            if (resultado != -1){
                Toast.makeText(
                        this,
                        "Alumno Registrado",
                        Toast.LENGTH_SHORT
                ).show();
                edtNombre.setText("");
                edtEdad.setText("");
                edtCarrera.setText("");
            }

        });

        btnMostrar.setOnClickListener(v->{
            String alumnos = baseDatos.listarAlumnos();
            if (alumnos.isEmpty()) {
                txtLista.setText("No hay datos, para el otro será");
            } else {
                txtLista.setText(alumnos);
            }
        });
    }
}