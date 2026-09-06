package com.finco.miprimerapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends  AppCompatActivity{
    EditText txtNombre;
    Button btnSaludar;
    TextView lblResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNombre=findViewById(R.id.txtNombre);
        btnSaludar=findViewById(R.id.btnSaludar);
        lblResultado=findViewById(R.id.lblResultado);

        btnSaludar.setOnClickListener(v->{
            String nombre=txtNombre.getText().toString();

            lblResultado.setText("Hola " + nombre + ", bienvenido a mi App");

            }
        );
    }
}