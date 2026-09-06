package com.finco.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends  AppCompatActivity{
    EditText txtPresupuesto;

    EditText txtAlimentacion;

    EditText txtPasaje;

    EditText txtOtros;
    Button btnCalcular;

    Button btnLimpiar;
    TextView lblResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPresupuesto=findViewById(R.id.txtPresupuesto);
        txtAlimentacion=findViewById(R.id.txtAlimentacion);
        txtPasaje=findViewById(R.id.txtPasaje);
        txtOtros=findViewById(R.id.txtOtros);

        btnCalcular=findViewById(R.id.btnCalcular);
        btnLimpiar=findViewById(R.id.btnLimpiar);
        lblResultado=findViewById(R.id.lblResultado);

        btnCalcular.setOnClickListener(v -> {
            // 1. Obtener el texto de los campos
            double presupuesto = Double.parseDouble(txtPresupuesto.getText().toString());
            double alimentacion = Double.parseDouble(txtAlimentacion.getText().toString());
            double pasaje = Double.parseDouble(txtPasaje.getText().toString());
            double otros = Double.parseDouble(txtOtros.getText().toString());

            // 2. Realizar las operaciones
            double totalGastos = alimentacion + pasaje + otros;
            double saldo = presupuesto - totalGastos;

            // 3. Mostrar el resultado
            lblResultado.setText("Te quedaste con: S/ " + saldo + " Ahorra hijo.");
        });
        btnLimpiar.setOnClickListener(v -> {
            txtPresupuesto.setText("");
            txtAlimentacion.setText("");
            txtPasaje.setText("");
            txtOtros.setText("");
            lblResultado.setText("");
            txtPresupuesto.requestFocus();
        });
    }
}