package com.finco.contactos;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearResultados;
    FloatingActionButton floatingActionButton;

    ArrayList<Contacto> listaContactos = new ArrayList<>();
    int contador = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearResultados = findViewById(R.id.linearResultados);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(v -> {
            mostrarDialogAgregarContacto();
        });
    }

    private void mostrarDialogAgregarContacto() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar Nuevo Contacto");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 30, 50, 30);

        final EditText txtNombre = new EditText(this);
        txtNombre.setHint("Nombre del Contacto");
        layout.addView(txtNombre);

        final EditText txtTelefono = new EditText(this);
        txtTelefono.setHint("Teléfono");
        layout.addView(txtTelefono);

        final EditText txtEmail = new EditText(this);
        txtEmail.setHint("Email");
        layout.addView(txtEmail);

        builder.setView(layout);

        builder.setPositiveButton("Guardar", (dialog, which) -> {
            String nombre = txtNombre.getText().toString().trim();
            String telefono = txtTelefono.getText().toString().trim();
            String email = txtEmail.getText().toString().trim();

            if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "¡Completa todos los campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            guardarContacto(nombre, telefono, email);
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> {
            dialog.dismiss();
        });

        builder.show();
    }

    private void guardarContacto(String nombre, String telefono, String email) {
        Contacto nuevoContacto = new Contacto(nombre, telefono, email);
        listaContactos.add(nuevoContacto);

        Button btnContacto = new Button(this);
        btnContacto.setText(contador + ". " + nuevoContacto.getNombre());
        btnContacto.setTag(nuevoContacto);
        btnContacto.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        btnContacto.setTextColor(getResources().getColor(android.R.color.white));
        btnContacto.setPadding(20, 20, 20, 20);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 10, 0, 10);
        btnContacto.setLayoutParams(params);

        btnContacto.setOnClickListener(v -> {
            Contacto contacto = (Contacto) v.getTag();
            mostrarDialogEditarContacto(contacto, btnContacto);
        });

        linearResultados.addView(btnContacto);

        contador++;
        Toast.makeText(this, "¡Contacto guardado!", Toast.LENGTH_SHORT).show();
    }

    private void mostrarDialogEditarContacto(Contacto contacto, Button btnContacto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Editar Contacto");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 30, 50, 30);

        final EditText txtNombre = new EditText(this);
        txtNombre.setText(contacto.getNombre());
        txtNombre.setHint("Nombre del Contacto");
        layout.addView(txtNombre);

        final EditText txtTelefono = new EditText(this);
        txtTelefono.setText(contacto.getTelefono());
        txtTelefono.setHint("Teléfono");
        layout.addView(txtTelefono);

        final EditText txtEmail = new EditText(this);
        txtEmail.setText(contacto.getEmail());
        txtEmail.setHint("Email");
        layout.addView(txtEmail);

        builder.setView(layout);

        builder.setPositiveButton("Guardar", (dialog, which) -> {
            String nuevoNombre = txtNombre.getText().toString().trim();
            String nuevoTelefono = txtTelefono.getText().toString().trim();
            String nuevoEmail = txtEmail.getText().toString().trim();

            if (nuevoNombre.isEmpty() || nuevoTelefono.isEmpty() || nuevoEmail.isEmpty()) {
                Toast.makeText(this, "¡Completa todos los campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            contacto.setNombre(nuevoNombre);
            contacto.setTelefono(nuevoTelefono);
            contacto.setEmail(nuevoEmail);

            btnContacto.setText(nuevoNombre);

            Toast.makeText(this, "¡Contacto actualizado!", Toast.LENGTH_SHORT).show();
        });

        builder.setNeutralButton("Eliminar", (dialog, which) -> {
            new AlertDialog.Builder(this)
                    .setTitle("Eliminar Contacto")
                    .setMessage("¿Estás seguro de eliminar a " + contacto.getNombre() + "?")
                    .setPositiveButton("Sí, eliminar", (d, w) -> {
                        listaContactos.remove(contacto);
                        linearResultados.removeView(btnContacto);
                        Toast.makeText(this, "Contacto eliminado", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> {
            dialog.dismiss();
        });

        builder.show();
    }

    private static class Contacto {
        private String nombre, telefono, email;

        public Contacto(String nombre, String telefono, String email) {
            this.nombre = nombre;
            this.telefono = telefono;
            this.email = email;
        }

        public String getNombre() { return nombre; }
        public String getTelefono() { return telefono; }
        public String getEmail() { return email; }

        public void setNombre(String nombre) { this.nombre = nombre; }
        public void setTelefono(String telefono) { this.telefono = telefono; }
        public void setEmail(String email) { this.email = email; }
    }
}