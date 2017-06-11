package carlos.gofinance.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import carlos.gofinance.R;
import carlos.gofinance.enums.TipoDespesa;
import carlos.gofinance.enums.TipoReceita;
import carlos.gofinance.fragments.DatePickerFragment;
import carlos.gofinance.interfaces.Colunas;
import carlos.gofinance.interfaces.Constantes;
import carlos.gofinance.models.Cartao;
import carlos.gofinance.models.Conta;
import carlos.gofinance.util.Utility;

public class CriarReceitaDespesaActivity extends AppCompatActivity implements DatePickerFragment.DataSelecionadaListener {
    public static final String REQUEST_CONFIRMACAO_DESPESA = "despesa";
    public static final String REQUEST_CONFIRMACAO_RECEITA = "receita";

    private TextView txtViewDataDesc;

    private EditText edtValor;
    private EditText edtTitle;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_despesa);

        Resources recursos = getResources();

        final Bundle extras = getIntent().getExtras();
        final boolean isReceita = extras.containsKey(MainActivity.CRIAR_RECEITA);

        final ArrayList<Conta> contas = (ArrayList<Conta>) extras.getSerializable(MainActivity.CONST_LISTA_CONTAS);
        final ArrayList<Cartao> cartoes = (ArrayList<Cartao>) extras.getSerializable(MainActivity.CONST_LISTA_CARTOES);
        ArrayList<String> opcoes = new ArrayList<>();

        assert contas != null;
        for (Conta conta : contas) {
            opcoes.add(conta.getNomeBanco());
        }

        assert cartoes != null;
        for (Cartao cartao : cartoes) {
            opcoes.add(cartao.getBandeiraCartao());
        }

        RelativeLayout rlCor = (RelativeLayout) findViewById(R.id.rl_cor);
        assert rlCor != null;

        ArrayAdapter<CharSequence> adapter;
        final Spinner spinnerTipo = (Spinner) findViewById(R.id.spinner_tipo_despesa);
        TextView txtViewTipoHint = (TextView) findViewById(R.id.txtView_tipo_hint);
        assert txtViewTipoHint != null;
        RelativeLayout rlContinuar = (RelativeLayout) findViewById(R.id.rl_continuar);
        assert rlContinuar != null;
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
        assert checkBox != null;

        if (isReceita) {
            rlContinuar.getBackground().setColorFilter(recursos.getColor(R.color.toolbar_color, null), PorterDuff.Mode.SRC_ATOP);
            rlCor.getBackground().setColorFilter(recursos.getColor(R.color.toolbar_color, null), PorterDuff.Mode.SRC_ATOP);
            checkBox.setVisibility(View.GONE);
            txtViewTipoHint.setHint("Fonte");
            adapter = ArrayAdapter.createFromResource(this,
                    R.array.tipo_receita_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            assert spinnerTipo != null;
            spinnerTipo.setAdapter(adapter);
        } else {
            adapter = ArrayAdapter.createFromResource(this,
                    R.array.tipo_despesa_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            assert spinnerTipo != null;
            spinnerTipo.setAdapter(adapter);
        }

        edtTitle = (EditText) findViewById(R.id.edt_title);
        edtValor = (EditText) findViewById(R.id.edt_valor);

        RelativeLayout rlData = (RelativeLayout) findViewById(R.id.rl_data);
        assert rlData != null;
        rlData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment fragment = new DatePickerFragment();
                fragment.show(getFragmentManager(), "datePicker");
            }
        });

        txtViewDataDesc = (TextView) findViewById(R.id.txtView_data_desc);

        final Spinner spinnerConta = (Spinner) findViewById(R.id.spinner_conta_despesa);
        ArrayAdapter<String> adapterConta = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcoes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assert spinnerConta != null;
        spinnerConta.setAdapter(adapterConta);

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
                if (edtTitle.getText().toString().equals("") || edtValor.getText().toString().equals("") ||
                        txtViewDataDesc.getText().equals(""))
                    Toast.makeText(CriarReceitaDespesaActivity.this, "Favor preencher os campos de Despesa, Valor e Data!", Toast.LENGTH_LONG).show();
                else {
                    ContentValues contentValues = new ContentValues();
                    if (isReceita) {
                        contentValues.put(Colunas.VALOR_RECEITA, Double.parseDouble(edtValor.getText().toString()));
                        contentValues.put(Colunas.DATA_RECEITA, txtViewDataDesc.getText().toString());
                        contentValues.put(Colunas.FONTE_RECEITA,
                                TipoReceita.valueOf(spinnerTipo.getSelectedItemPosition()).getIdReceita());
                        contentValues.put(Colunas.TITULO_RECEITA, edtTitle.getText().toString());

                        String selected = spinnerConta.getSelectedItem().toString();
                        boolean found = false;
                        for (Conta conta : contas) {
                            if (conta.getNomeBanco().contains(selected)) {
                                contentValues.put(Colunas.ID_CONTA, conta.getIdConta());
                                found = true;
                                break;
                            }
                        }

                        if (!found)
                            for (Cartao cartao : cartoes) {
                                if (cartao.getBandeiraCartao().contains(selected)) {
                                    contentValues.put(Colunas.ID_CARTAO, cartao.getIdCartao());
                                    break;
                                }
                            }

                        Utility.insert(Constantes.TABLE_RECEITA, contentValues, CriarReceitaDespesaActivity.this);

                        Intent intent = new Intent(CriarReceitaDespesaActivity.this, ConfirmacaoActivity.class);
                        intent.putExtra(REQUEST_CONFIRMACAO_RECEITA, true);
                        setResult(RESULT_OK);
                        startActivity(intent);
                        finish();
                    } else {
                        contentValues.put(Colunas.VALOR_DESPESA, Double.parseDouble(edtValor.getText().toString()));
                        contentValues.put(Colunas.DATA_DESPESA, txtViewDataDesc.getText().toString());
                        contentValues.put(Colunas.TIPO_DESPESA,
                                TipoDespesa.valueOf(spinnerTipo.getSelectedItemPosition()).getIdDespesa());
                        contentValues.put(Colunas.DESPESA_PAGA, checkBox.isSelected());
                        contentValues.put(Colunas.TITULO_DESPESA, edtTitle.getText().toString());

                        String selected = spinnerConta.getSelectedItem().toString();
                        boolean found = false;
                        for (Conta conta : contas) {
                            if (conta.getNomeBanco().contains(selected)) {
                                contentValues.put(Colunas.ID_CONTA, conta.getIdConta());
                                found = true;
                                break;
                            }
                        }

                        if (!found)
                            for (Cartao cartao : cartoes) {
                                if (cartao.getBandeiraCartao().contains(selected)) {
                                    contentValues.put(Colunas.ID_CARTAO, cartao.getIdCartao());
                                    break;
                                }
                            }

                        Utility.insert(Constantes.TABLE_DESPESA, contentValues, CriarReceitaDespesaActivity.this);

                        Intent intent = new Intent(CriarReceitaDespesaActivity.this, ConfirmacaoActivity.class);
                        intent.putExtra(REQUEST_CONFIRMACAO_DESPESA, true);
                        setResult(RESULT_OK);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void dataSelecionada(String dataSelecionada) {
        txtViewDataDesc.setText(dataSelecionada);
    }
}