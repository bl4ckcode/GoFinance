package carlos.gofinance.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import carlos.gofinance.R;
import carlos.gofinance.enums.TipoCartao;
import carlos.gofinance.enums.TipoConta;
import carlos.gofinance.enums.TipoCor;
import carlos.gofinance.interfaces.Colunas;
import carlos.gofinance.interfaces.Constantes;
import carlos.gofinance.util.Utility;

public class CriarCartaoContaActivity extends AppCompatActivity {
    public static String REQUEST_CONFIRMACAO_CONTA = "CONTA";
    public static String REQUEST_CONFIRMACAO_CARTAO = "CARTAO";

    private RelativeLayout rlCor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        final Bundle extras = getIntent().getExtras();
        final boolean isCartao = extras != null;

        rlCor = (RelativeLayout) findViewById(R.id.rl_cor);
        assert rlCor != null;

        ArrayAdapter<CharSequence> adapter;

        final Spinner spinnerBanco = (Spinner) findViewById(R.id.spinner_conta);
        final Spinner spinnerTipoConta = (Spinner) findViewById(R.id.spinner_tipo_conta);

        if (isCartao) {
            adapter = ArrayAdapter.createFromResource(this,
                    R.array.cartao_array, R.layout.simple_spinner_item_white);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            assert spinnerBanco != null;
            spinnerBanco.setAdapter(adapter);

            adapter = ArrayAdapter.createFromResource(this,
                    R.array.tipo_cartao_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            assert spinnerTipoConta != null;
            spinnerTipoConta.setAdapter(adapter);
        } else {
            adapter = ArrayAdapter.createFromResource(this,
                    R.array.banco_array, R.layout.simple_spinner_item_white);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            assert spinnerBanco != null;
            spinnerBanco.setAdapter(adapter);

            adapter = ArrayAdapter.createFromResource(this,
                    R.array.tipo_conta_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            assert spinnerTipoConta != null;
            spinnerTipoConta.setAdapter(adapter);
        }

        final Spinner spinnerCor = (Spinner) findViewById(R.id.spinner_cor);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.cor_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assert spinnerCor != null;
        spinnerCor.setAdapter(adapter);

        final EditText edtValor = (EditText) findViewById(R.id.edt_valor);
        assert edtValor != null;
        RelativeLayout rlContinuar = (RelativeLayout) findViewById(R.id.rl_continuar);
        assert rlContinuar != null;

        Button btnVoltar = (Button) findViewById(R.id.btn_voltar);
        assert btnVoltar != null;
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rlContinuar.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onClick(View v) {
                if (edtValor.getText().toString().equals(""))
                    Toast.makeText(CriarCartaoContaActivity.this, "Favor preencher o campo de Valor!", Toast.LENGTH_LONG).show();
                else {
                    ContentValues contentValues = new ContentValues();
                    if (isCartao) {
                        contentValues.put(Colunas.VALOR_CARTAO, Double.parseDouble(edtValor.getText().toString()));
                        contentValues.put(Colunas.TIPO_CARTAO,
                                TipoCartao.valueOf(spinnerTipoConta.getSelectedItemPosition()).getIdTipoCartao());
                        contentValues.put(Colunas.COR_CARTAO, TipoCor.valueOf(spinnerCor.getSelectedItemPosition()).getResIdColor());
                        contentValues.put(Colunas.BANDEIRA_CARTAO, spinnerBanco.getSelectedItem().toString());

                        Utility.insert(Constantes.TABLE_CARTAO, contentValues, CriarCartaoContaActivity.this);

                        Intent intent = new Intent(CriarCartaoContaActivity.this, ConfirmacaoActivity.class);
                        intent.putExtra(REQUEST_CONFIRMACAO_CARTAO, true);
                        setResult(RESULT_OK);
                        startActivity(intent);
                        finish();
                    } else {
                        contentValues.put(Colunas.VALOR_CONTA, Double.parseDouble(edtValor.getText().toString()));
                        contentValues.put(Colunas.TIPO_CONTA,
                                TipoConta.valueOf(spinnerTipoConta.getSelectedItemPosition()).getIdTipoConta());
                        contentValues.put(Colunas.COR_CONTA, TipoCor.valueOf(spinnerCor.getSelectedItemPosition()).getResIdColor());
                        contentValues.put(Colunas.NOME_BANCO, spinnerBanco.getSelectedItem().toString());

                        Utility.insert(Constantes.TABLE_CONTA, contentValues, CriarCartaoContaActivity.this);

                        Intent intent = new Intent(CriarCartaoContaActivity.this, ConfirmacaoActivity.class);
                        intent.putExtra(REQUEST_CONFIRMACAO_CONTA, true);
                        setResult(RESULT_OK);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}
