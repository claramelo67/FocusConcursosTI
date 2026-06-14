package com.example.focusconcursosti;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MateriasActivity extends AppCompatActivity {

    TextView btnVoltar, txtObservacao, btnAdicionar;
    CardView cardPomodoro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        btnVoltar = findViewById(R.id.btnVoltar);
        txtObservacao = findViewById(R.id.txtObservacao);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        cardPomodoro = findViewById(R.id.cardPomodoro);

        btnVoltar.setOnClickListener(v -> {
            finish();
        });

        txtObservacao.setOnClickListener(v -> {
            EditText campoTexto = new EditText(this);
            campoTexto.setHint("Digite uma observação");
            campoTexto.setText(txtObservacao.getText().toString());

            new AlertDialog.Builder(this)
                    .setTitle("Editar observação")
                    .setView(campoTexto)
                    .setPositiveButton("Salvar", (dialog, which) -> {
                        txtObservacao.setText(campoTexto.getText().toString());
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });

        btnAdicionar.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Adicionar matéria")
                    .setMessage("Funcionalidade simulada para representar a inclusão de novas matérias.")
                    .setPositiveButton("OK", null)
                    .show();
        });

        cardPomodoro.setOnClickListener(v -> {
            Intent intent = new Intent(MateriasActivity.this, PomodoroActivity.class);
            startActivity(intent);
        });
    }
}