package com.example.listadetarefas;

import android.content.Intent;
import android.media.AudioDeviceInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private AudioHelper audioHelper;
    private TextView txtStatus;
    private ListView listView;
    private Button btnVerificar;
    private Button btnBluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioHelper = new AudioHelper(this);

        txtStatus = findViewById(R.id.txtStatus);
        listView = findViewById(R.id.listView);
        btnVerificar = findViewById(R.id.btnVerificar);
        btnBluetooth = findViewById(R.id.btnBluetooth);

        String[] itens = {
                "Verificar alto-falante",
                "Verificar Bluetooth A2DP",
                "Abrir configurações Bluetooth"
        };

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itens));

        btnVerificar.setOnClickListener(v -> {
            boolean speaker = audioHelper.audioOutputAvailable(AudioDeviceInfo.TYPE_BUILTIN_SPEAKER);
            boolean bluetooth = audioHelper.audioOutputAvailable(AudioDeviceInfo.TYPE_BLUETOOTH_A2DP);

            if (speaker) {
                txtStatus.setText("Alto-falante integrado disponível");
            } else if (bluetooth) {
                txtStatus.setText("Bluetooth A2DP conectado");
            } else {
                txtStatus.setText("Nenhuma saída de áudio encontrada");
            }
        });

        btnBluetooth.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
            startActivity(intent);
        });
    }
}