package com.example.focusconcursosti;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PomodoroActivity extends AppCompatActivity {

    TextView txtTempo, txtStatusPomodoro, txtSessoesHoje, txtTempoTotal, btnVoltarPomodoro;
    Button btnIniciar, btnPausar, btnReiniciar;
    CardView cardPerfil;

    CountDownTimer timer;

    long tempoInicial = 25 * 60 * 1000;
    long tempoRestante = tempoInicial;

    boolean rodando = false;
    int sessoesHoje = 3;
    int tempoTotalMinutos = 75;
    int minutosAtuais = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        txtTempo = findViewById(R.id.txtTempo);
        txtStatusPomodoro = findViewById(R.id.txtStatusPomodoro);
        txtSessoesHoje = findViewById(R.id.txtSessoesHoje);
        txtTempoTotal = findViewById(R.id.txtTempoTotal);

        btnVoltarPomodoro = findViewById(R.id.btnVoltarPomodoro);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnPausar = findViewById(R.id.btnPausar);
        btnReiniciar = findViewById(R.id.btnReiniciar);
        cardPerfil = findViewById(R.id.cardPerfil);

        btnVoltarPomodoro.setOnClickListener(v -> finish());

        btnIniciar.setOnClickListener(v -> iniciarTimer());

        btnPausar.setOnClickListener(v -> pausarTimer());

        btnReiniciar.setOnClickListener(v -> reiniciarTimer());

        txtTempo.setOnClickListener(v -> editarTempo());

        cardPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(PomodoroActivity.this, PerfilActivity.class);
            startActivity(intent);
        });

        atualizarTela();
    }

    private void iniciarTimer() {
        if (!rodando) {
            timer = new CountDownTimer(tempoRestante, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    tempoRestante = millisUntilFinished;
                    atualizarTela();
                }

                @Override
                public void onFinish() {
                    rodando = false;
                    tempoRestante = 0;
                    txtTempo.setText("00:00");
                    txtStatusPomodoro.setText("Sessão concluída!");

                    sessoesHoje++;
                    tempoTotalMinutos += minutosAtuais;

                    txtSessoesHoje.setText(String.valueOf(sessoesHoje));
                    txtTempoTotal.setText(formatarTempoTotal(tempoTotalMinutos));
                }
            }.start();

            rodando = true;
            txtStatusPomodoro.setText("Sessão em andamento");
        }
    }

    private void pausarTimer() {
        if (rodando && timer != null) {
            timer.cancel();
            rodando = false;
            txtStatusPomodoro.setText("Sessão pausada");
        }
    }

    private void reiniciarTimer() {
        if (timer != null) {
            timer.cancel();
        }

        tempoRestante = tempoInicial;
        rodando = false;
        txtStatusPomodoro.setText("Modo foco • " + minutosAtuais + " min");
        atualizarTela();
    }

    private void editarTempo() {
        if (rodando) {
            pausarTimer();
        }

        EditText campoTempo = new EditText(this);
        campoTempo.setHint("Digite os minutos");
        campoTempo.setInputType(InputType.TYPE_CLASS_NUMBER);
        campoTempo.setText(String.valueOf(minutosAtuais));

        new AlertDialog.Builder(this)
                .setTitle("Editar tempo de foco")
                .setView(campoTempo)
                .setPositiveButton("Salvar", (dialog, which) -> {
                    String valor = campoTempo.getText().toString();

                    if (!valor.isEmpty()) {
                        int minutos = Integer.parseInt(valor);

                        if (minutos > 0) {
                            minutosAtuais = minutos;
                            tempoInicial = minutos * 60 * 1000;
                            tempoRestante = tempoInicial;

                            txtStatusPomodoro.setText("Modo foco • " + minutos + " min");
                            atualizarTela();
                        }
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void atualizarTela() {
        int minutos = (int) (tempoRestante / 1000) / 60;
        int segundos = (int) (tempoRestante / 1000) % 60;

        String tempoFormatado = String.format("%02d:%02d", minutos, segundos);
        txtTempo.setText(tempoFormatado);
    }

    private String formatarTempoTotal(int minutosTotais) {
        int horas = minutosTotais / 60;
        int minutos = minutosTotais % 60;

        return horas + "h" + minutos;
    }
}