package com.example.focusconcursosti;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DashboardActivity extends AppCompatActivity {

    // TEXTOS
    TextView txtLembrete, txtMaterias, txtMetas;

    // CARDS
    CardView cardLembrete, cardMaterias, cardMetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // CONECTANDO ELEMENTOS DA TELA
        txtLembrete = findViewById(R.id.txtLembrete);
        txtMaterias = findViewById(R.id.txtMaterias);
        txtMetas = findViewById(R.id.txtMetas);

        cardLembrete = findViewById(R.id.cardLembrete);
        cardMaterias = findViewById(R.id.cardMaterias);
        cardMetas = findViewById(R.id.cardMetas);

        // ABRIR TELA DE MATÉRIAS
        cardMaterias.setOnLongClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, MateriasActivity.class);
            startActivity(intent);
            return true;
        });

        // EDITAR LEMBRETE
        cardLembrete.setOnClickListener(v -> {
            EditText campoTexto = new EditText(this);
            campoTexto.setHint("Digite o novo lembrete");
            campoTexto.setText(txtLembrete.getText().toString());

            new AlertDialog.Builder(this)
                    .setTitle("Editar lembrete")
                    .setView(campoTexto)
                    .setPositiveButton("Salvar", (dialog, which) -> {
                        txtLembrete.setText(campoTexto.getText().toString());
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });

        // EDITAR MATÉRIAS
        cardMaterias.setOnClickListener(v -> {
            EditText campoTexto = new EditText(this);
            campoTexto.setHint("Digite quantidade de matérias");

            new AlertDialog.Builder(this)
                    .setTitle("Editar matérias")
                    .setView(campoTexto)
                    .setPositiveButton("Salvar", (dialog, which) -> {

                        String valor = campoTexto.getText().toString();

                        txtMaterias.setText("📚\n" + valor + "\nMatérias");
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });

        // EDITAR METAS
        cardMetas.setOnClickListener(v -> {
            EditText campoTexto = new EditText(this);
            campoTexto.setHint("Digite quantidade de metas");

            new AlertDialog.Builder(this)
                    .setTitle("Editar metas")
                    .setView(campoTexto)
                    .setPositiveButton("Salvar", (dialog, which) -> {

                        String valor = campoTexto.getText().toString();

                        txtMetas.setText("🎯\n" + valor + "\nMetas");
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }
}