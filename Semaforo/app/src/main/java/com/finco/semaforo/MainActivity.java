package com.finco.semaforo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnrojo;
    Button btnamarillo;
    Button btnverde;
    ImageView Resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnrojo=findViewById(R.id.btnRojo);
        btnamarillo=findViewById(R.id.btnAmarillo);
        btnverde=findViewById(R.id.btnVerde);

        Resultado=findViewById(R.id.imageSemaforo);

        btnrojo.setOnClickListener(v->{
           Resultado.setImageResource(R.drawable.semaforo_rojo);
            }
            );
        btnamarillo.setOnClickListener(v->{
                    Resultado.setImageResource(R.drawable.semaforo_amarillo);
                }
        );
        btnverde.setOnClickListener(v->{
                    Resultado.setImageResource(R.drawable.semaforo_verde);
                }
        );
    }
}