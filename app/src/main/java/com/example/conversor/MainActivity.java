package com.example.conversor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
     *@author:<Fabiola Rodrigues dos Santos / RA: 1110482313011>
     */

    private Spinner spTipos;
    private Button btnGerar;
    private TextView txtResultado;
    private EditText txtValor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGerar = findViewById(R.id.btnGerar);
        txtResultado = findViewById(R.id.txtResultado);
        txtValor = findViewById(R.id.txtValor);
        spTipos = findViewById(R.id.spTipos);

        preencherSpinner();
        btnGerar.setOnClickListener(op -> gerar());
    }

    private void preencherSpinner() {
        List<String> lista = new ArrayList<>();
        lista.add("Bytes");
        lista.add("KB");
        lista.add("MB");
        lista.add("GB");
        lista.add("TB");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipos.setAdapter(adapter);
    }

    private void gerar() {
        DecimalFormat df = new DecimalFormat("#.00");
        long bits = Long.parseLong(txtValor.getText().toString().trim());
        String unidade = (String) spTipos.getSelectedItem();
        double resultado = converterBits(bits, unidade);
        String resultadoFormatado = df.format(resultado);
        txtResultado.setText(resultadoFormatado + " "+unidade);
    }

    public static double converterBits(long bits, String unidadeDestino) {
        double bytes = bits / 8.0;

        switch (unidadeDestino) {
            case "Bytes":
                return bytes;
            case "KB":
                return bytes / 1024;
            case "MB":
                return bytes / (1024 * 1024);
            case "GB":
                return bytes / (1024 * 1024 * 1024);
            case "TB":
                return bytes / (1024L * 1024 * 1024 * 1024);
            default:
                throw new IllegalArgumentException("Unidade inválida.");
        }
    }

}