package com.finco.contadordevisitas;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSumar;
    Button btnRestaurar;
    TextView txtContador;

    int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnSumar=findViewById(R.id.btnVisita);
        btnRestaurar=findViewById(R.id.btnRestaurar);
        txtContador=findViewById(R.id.NumVisitas);

        txtContador.setText(String.valueOf(contador));

        btnSumar.setOnClickListener(v->{

            contador++;
            txtContador.setText(String.valueOf(contador));
            Toast.makeText(this,
                    "Nueva visita",
                    Toast.LENGTH_LONG).show();
        });

        btnRestaurar.setOnClickListener(v->{

            contador=0;
            txtContador.setText(String.valueOf(contador));
            Toast.makeText(this,
                    "Contador Reiniciado",
                    Toast.LENGTH_LONG).show();
        });
    }
}