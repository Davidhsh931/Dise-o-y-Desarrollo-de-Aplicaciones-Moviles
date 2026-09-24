package com.finco.appusuariosapi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;


public class MainActivity extends AppCompatActivity {

    Button btnConsultar;
    TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConsultar=findViewById(R.id.btnConsultar);
        txtResultado=findViewById(R.id.txtResultado);

        btnConsultar.setOnClickListener(v->{
            consultarUsuarios();
        });
    }
    private void consultarUsuarios(){
        ApiService apiService=RetrofitClient
                .getRetrofit()
                .create(ApiService.class);
        apiService.obtenerUsuarios().enqueue(
                new Callback<List<Usuario>>() {
                    @Override
                    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                        if(response.isSuccessful() && response.body()!=null){
                            List<Usuario> usuarios=response.body();
                            String resultado="";
                            for (Usuario usuario : usuarios){
                                resultado+=
                                        "Usuario: "+usuario.getName()
                                        + "\n"
                                        +"Correo: "+usuario.getEmail()
                                        + "\n\n";
                            }
                            txtResultado.setText(resultado);

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Usuario>> call, Throwable throwable) {
                        Toast.makeText(
                                MainActivity.this,
                                "Error: "+throwable.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );
    }
}