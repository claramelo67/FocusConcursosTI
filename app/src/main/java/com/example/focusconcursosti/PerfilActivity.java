package com.example.focusconcursosti;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    TextView btnVoltarPerfil, txtNome, txtCurso, txtMetaDiaria;
    Button btnEditarPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        btnVoltarPerfil = findViewById(R.id.btnVoltarPerfil);
        txtNome = findViewById(R.id.txtNome);
        txtCurso = findViewById(R.id.txtCurso);
        txtMetaDiaria = findViewById(R.id.txtMetaDiaria);
        btnEditarPerfil = findViewById(R.id.btnEditarPerfil);

        btnVoltarPerfil.setOnClickListener(v -> finish());

        btnEditarPerfil.setOnClickListener(v -> abrirEdicaoPerfil());
    }

    private void abrirEdicaoPerfil() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 20, 40, 10);

        EditText campoNome = new EditText(this);
        campoNome.setHint("Nome");
        campoNome.setText(txtNome.getText().toString());

        EditText campoCurso = new EditText(this);
        campoCurso.setHint("Curso");
        campoCurso.setText(txtCurso.getText().toString());

        EditText campoMeta = new EditText(this);
        campoMeta.setHint("Meta diária");
        campoMeta.setText(txtMetaDiaria.getText().toString());

        layout.addView(campoNome);
        layout.addView(campoCurso);
        layout.addView(campoMeta);

        new AlertDialog.Builder(this)
                .setTitle("Editar Perfil")
                .setView(layout)
                .setPositiveButton("Salvar", (dialog, which) -> {
                    txtNome.setText(campoNome.getText().toString());
                    txtCurso.setText(campoCurso.getText().toString());
                    txtMetaDiaria.setText(campoMeta.getText().toString());
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}