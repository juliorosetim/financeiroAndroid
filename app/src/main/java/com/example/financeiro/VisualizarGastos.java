package com.example.financeiro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VisualizarGastos extends AppCompatActivity {

    private TextView tvHeaderGastos;
    private Button btnVoltarGastos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.visualizar_gastos_layout);

        tvHeaderGastos = findViewById(R.id.tvHeaderGastos);
        btnVoltarGastos = findViewById(R.id.btnVoltarGastos);

        btnVoltarGastos.setTextColor(Color.WHITE);
        btnVoltarGastos.setBackgroundColor(Color.parseColor("#611a5f"));

        tvHeaderGastos.setTextColor(Color.WHITE);
        tvHeaderGastos.setBackgroundColor(Color.parseColor("#611a5f"));

        btnVoltarGastos.setOnClickListener(voltarGastosClick);
    }


    View.OnClickListener voltarGastosClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(VisualizarGastos.this, Main.class));
        }
    };


}
