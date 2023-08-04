package com.example.financeiro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class CadastrarContas extends AppCompatActivity {

    private SQLiteDatabase bancoDados;

    private TextView tvHeader;

    private EditText tvConta;
    private EditText valor;
    private EditText parcelas;
    private EditText valorParcelas;

    private Button btnSalvar;
    private Button btnVisualizar;

    private Double vlParcela;

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadatro_contas);

        criarBancoDados();

        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        tvHeader = findViewById(R.id.tvHeader);
        tvConta = findViewById(R.id.tvConta);
        valor = findViewById(R.id.tvValor);
        parcelas = findViewById(R.id.tvParcelas);
        valorParcelas = findViewById(R.id.tvValorDaParcela);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnVisualizar = findViewById(R.id.btnVoltar);

        tvHeader.setTextColor(Color.WHITE);
        tvHeader.setBackgroundColor(Color.parseColor("#611a5f"));

        btnSalvar.setBackgroundColor(Color.parseColor("#611a5f"));
        btnVisualizar.setBackgroundColor(Color.parseColor("#611a5f"));

        parcelas.setOnFocusChangeListener(focusListenerParcela);
        valor.setOnFocusChangeListener(focusListenerParcela);
        btnSalvar.setOnClickListener(onClickListener);
        btnVisualizar.setOnClickListener(voltarlick);
    }

    private void criarBancoDados() {
        try {
            SQLiteDatabase bancoDados = conexaoBanco();

            bancoDados.execSQL("Create table if not exists contas ( " +
                    " id Integer primary key autoincrement," +
                    " descricao varchar(100), " +
                    " valor numeric(15,2)," +
                    "qtdeParcelas Integer," +
                    "vlParcela Numeric(15,2) )");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    View.OnFocusChangeListener focusListenerParcela = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if(!hasFocus){
                if ( !parcelas.getText().toString().isEmpty() && !valor.getText().toString().isEmpty() ){
                    vlParcela = Double.valueOf(valor.getText().toString()) / Double.valueOf(parcelas.getText().toString());

                    valorParcelas.setText(vlParcela.toString());
                }
            }
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            try {

                SQLiteDatabase bancoDados = conexaoBanco();

                String insert = "insert into contas(descricao, valor, qtdeParcelas, vlParcela) values(?,?,?,?)";
                SQLiteStatement stmt = bancoDados.compileStatement(insert);

                stmt.bindString(1, tvConta.getText().toString());
                stmt.bindDouble(2, Double.parseDouble(valor.getText().toString()));
                stmt.bindLong(3, Integer.parseInt(parcelas.getText().toString()));
                stmt.bindDouble(4, Double.parseDouble(valorParcelas.getText().toString()));
                stmt.executeInsert();

                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Salvo com sucesso",
                                Snackbar.LENGTH_SHORT)
                        .setTextColor(Color.WHITE)
                        .setBackgroundTint(Color.parseColor("#742373"));

                View snackbarView = snackbar.getView();
                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) snackbarView.getLayoutParams();
                params.gravity = Gravity.TOP;
                snackbarView.setLayoutParams(params);

                snackbar.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    View.OnClickListener voltarlick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(CadastrarContas.this, Main.class));
        }
    };

    private SQLiteDatabase conexaoBanco() {
         return openOrCreateDatabase("bancoTeste", MODE_PRIVATE, null);
    }


}