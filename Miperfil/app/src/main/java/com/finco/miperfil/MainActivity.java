package com.finco.miperfil;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText sunombre;
    EditText sucarrera;
    EditText suciclo;
    Button btnCrearPerfil;
    TextView txtMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sunombre=findViewById(R.id.edtNombre);
        sucarrera=findViewById(R.id.edtCarrera);
        suciclo=findViewById(R.id.edtCiclo);

        btnCrearPerfil=findViewById(R.id.btnCrear);

        txtMensaje=findViewById(R.id.txtResultado);

        btnCrearPerfil.setOnClickListener(v->{

            String nombre = sunombre.getText().toString().trim();
            String carrera = sucarrera.getText().toString().trim();
            String ciclo = suciclo.getText().toString().trim();

            if((nombre).isEmpty()){
                Toast.makeText(
                        MainActivity.this,
                        "Completa el campo nombre",
                        Toast.LENGTH_LONG
                ).show();
                return;
            }

            if((carrera).isEmpty()){
                Toast.makeText(
                        MainActivity.this,
                        "Completa el campo carrea",
                        Toast.LENGTH_LONG
                ).show();
                return;
            }

            if((ciclo).isEmpty()){
                Toast.makeText(
                        MainActivity.this,
                        "Completa el campo ciclo",
                        Toast.LENGTH_LONG
                ).show();
                return;
            }

            txtMensaje.setText(
                    "Nombre: "+ nombre +
                    "\nCarera: "+ carrera +
                            "\nCiclo: "+ ciclo
            );


        });

        }
    }