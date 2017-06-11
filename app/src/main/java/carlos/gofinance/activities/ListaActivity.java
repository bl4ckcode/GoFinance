package carlos.gofinance.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import carlos.gofinance.R;
import carlos.gofinance.fragments.ListaFragment;
import carlos.gofinance.models.Cartao;
import carlos.gofinance.models.Conta;
import carlos.gofinance.models.Despesa;
import carlos.gofinance.models.Receita;
import carlos.gofinance.util.Utility;

public class ListaActivity extends AppCompatActivity {

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        RelativeLayout relLayoutMes = (RelativeLayout) findViewById(R.id.relLayoutMes);
        ListaFragment fragment;
        Bundle extras = getIntent().getExtras();
        if (extras.containsKey(MainActivity.CONST_LISTA_CONTAS)) {
            ArrayList<Conta> contas = (ArrayList<Conta>) extras.getSerializable(MainActivity.CONST_LISTA_CONTAS);
            fragment = ListaFragment.newInstance(contas, null, null, null);

            final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            assert fab != null;
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ListaActivity.this, CriarCartaoContaActivity.class);
                    startActivityForResult(intent, MainActivity.REQUEST_CRIAR_CONTA);
                    finish();
                }
            });
        } else if (extras.containsKey(MainActivity.CONST_LISTA_CARTOES)) {
            ArrayList<Cartao> cartoes = (ArrayList<Cartao>) extras.getSerializable(MainActivity.CONST_LISTA_CARTOES);
            fragment = ListaFragment.newInstance(null, cartoes, null, null);

            final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            assert fab != null;
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ListaActivity.this, CriarCartaoContaActivity.class);
                    startActivityForResult(intent, MainActivity.REQUEST_CRIAR_CARTAO);
                    finish();
                }
            });
        } else if (extras.containsKey(MainActivity.CONST_LISTA_RECEITAS)) {
            ArrayList<Receita> receitas = (ArrayList<Receita>) extras.getSerializable(MainActivity.CONST_LISTA_RECEITAS);
            fragment = ListaFragment.newInstance(null, null, null, receitas);

            final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            assert fab != null;
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ListaActivity.this, CriarCartaoContaActivity.class);
                    intent.putExtra(MainActivity.CRIAR_RECEITA, true);
                    intent.putExtra(MainActivity.CONST_LISTA_CONTAS, Utility.obterContas(ListaActivity.this));
                    intent.putExtra(MainActivity.CONST_LISTA_CARTOES, Utility.obterCartoes(ListaActivity.this));
                    startActivityForResult(intent, MainActivity.REQUEST_CRIAR_CONTA);
                    finish();
                }
            });
        } else {
            ArrayList<Despesa> despesas = (ArrayList<Despesa>) extras.getSerializable(MainActivity.CONST_LISTA_DESPESAS);
            assert relLayoutMes != null;
            relLayoutMes.getBackground().setColorFilter(getResources().getColor(R.color.circle_shape_red, null), PorterDuff.Mode.DST_ATOP);
            fragment = ListaFragment.newInstance(null, null, despesas, null);

            final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            assert fab != null;
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ListaActivity.this, CriarCartaoContaActivity.class);
                    intent.putExtra(MainActivity.CONST_LISTA_CONTAS, Utility.obterContas(ListaActivity.this));
                    intent.putExtra(MainActivity.CONST_LISTA_CARTOES, Utility.obterCartoes(ListaActivity.this));
                    finish();
                }
            });
        }

        ImageView imgViewMesAnterior = (ImageView) findViewById(R.id.imgViewMesAnterior);
        TextView txtViewMes = (TextView) findViewById(R.id.txtViewMes);
        ImageView imgViewMesSeguinte = (ImageView) findViewById(R.id.imgViewMesSeguinte);
        FrameLayout fragmentListContainer = (FrameLayout) findViewById(R.id.fragment_list_container);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fl_container, fragment).commit();
    }
}
