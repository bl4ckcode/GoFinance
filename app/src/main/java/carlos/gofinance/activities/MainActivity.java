package carlos.gofinance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

import carlos.gofinance.R;
import carlos.gofinance.fragments.MainFragment;
import carlos.gofinance.models.Cartao;
import carlos.gofinance.models.Conta;
import carlos.gofinance.models.Despesa;
import carlos.gofinance.models.Receita;
import carlos.gofinance.util.Utility;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final int REQUEST_CRIAR_DESPESA = 2;
    public static final int REQUEST_CRIAR_CONTA = 0;
    public static final int REQUEST_CRIAR_CARTAO = 1;
    public static final int REQUEST_CRIAR_RECEITA = 3;

    public static final String CRIAR_CARTAO = "criar_cartao";
    public static final String CRIAR_RECEITA = "criar_receita";
    public static final String CONST_LISTA_CONTAS = "contas";
    public static final String CONST_LISTA_CARTOES = "cartoes";
    public static final String CONST_LISTA_DESPESAS = "despesas";
    public static final String CONST_LISTA_RECEITAS = "receitas";

    private FloatingActionButton fabCriarConta, fabCriarCartao, fabCriarDespesa, fabCriarreceita;

    private Animation mostrar_criar_conta, esconder_criar_conta, mostrar_criar_cartao, esconder_criar_cartao,
            mostrar_criar_despesa, esconder_criar_despesa, mostrar_criar_receita, esconder_criar_receita;

    private boolean FAB_Status = false;

    private ArrayList<Conta> contas = new ArrayList<>();
    private ArrayList<Cartao> cartoes = new ArrayList<>();
    private ArrayList<Despesa> despesas = new ArrayList<>();
    private ArrayList<Receita> receitas = new ArrayList<>();

    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contas = Utility.obterContas(this);
        cartoes = Utility.obterCartoes(this);
        despesas = Utility.obterDespesas(this);
        receitas = Utility.obterReceitas(this);

        mostrar_criar_conta = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_criar_conta_mostrar);
        esconder_criar_conta = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_criar_conta_esconder);

        mostrar_criar_cartao = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_criar_cartao_mostrar);
        esconder_criar_cartao = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_criar_cartao_esconder);

        mostrar_criar_despesa = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_criar_despesa_mostrar);
        esconder_criar_despesa = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_criar_despesa_esconder);

        mostrar_criar_receita = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_criar_carteira_mostrar);
        esconder_criar_receita = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_criar_carteira_esconder);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!FAB_Status) {
                    expandFAB();
                    FAB_Status = true;
                } else {
                    hideFAB();
                    FAB_Status = false;
                }
            }
        });

        fabCriarConta = (FloatingActionButton) findViewById(R.id.fab_criar_conta);
        fabCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFAB();
                FAB_Status = false;
                Intent intent = new Intent(MainActivity.this, CriarCartaoContaActivity.class);
                startActivityForResult(intent, REQUEST_CRIAR_CONTA);
            }
        });
        fabCriarCartao = (FloatingActionButton) findViewById(R.id.fab_criar_cartao);
        fabCriarCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFAB();
                FAB_Status = false;
                Intent intent = new Intent(MainActivity.this, CriarCartaoContaActivity.class);
                intent.putExtra(CRIAR_CARTAO, true);
                startActivityForResult(intent, REQUEST_CRIAR_CARTAO);
            }
        });
        fabCriarDespesa = (FloatingActionButton) findViewById(R.id.fab_criar_despesa);
        fabCriarDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFAB();
                FAB_Status = false;
                if(contas.size() > 0 || cartoes.size() > 0) {
                    Intent intent = new Intent(MainActivity.this, CriarReceitaDespesaActivity.class);
                    intent.putExtra(CONST_LISTA_CONTAS, contas);
                    intent.putExtra(CONST_LISTA_CARTOES, cartoes);
                    startActivityForResult(intent, REQUEST_CRIAR_DESPESA);
                } else {
                    Toast.makeText(MainActivity.this,
                            "É necessário criar uma conta antes de criar uma despesa!", Toast.LENGTH_LONG).show();
                }
            }
        });
        fabCriarreceita = (FloatingActionButton) findViewById(R.id.fab_criar_carteira);
        fabCriarreceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFAB();
                FAB_Status = false;
                if(contas.size() > 0 || cartoes.size() > 0) {
                    Intent intent = new Intent(MainActivity.this, CriarReceitaDespesaActivity.class);
                    intent.putExtra(CRIAR_RECEITA, true);
                    intent.putExtra(CONST_LISTA_CONTAS, contas);
                    intent.putExtra(CONST_LISTA_CARTOES, cartoes);
                    startActivityForResult(intent, REQUEST_CRIAR_RECEITA);
                } else {
                    Toast.makeText(MainActivity.this,
                            "É necessário criar uma conta antes de criar uma receita!", Toast.LENGTH_LONG).show();
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        mainFragment = MainFragment.newInstance(contas, cartoes, despesas, receitas);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fl_container, mainFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_contas) {
            Intent intent = new Intent(this, ListaActivity.class);
            intent.putExtra(CONST_LISTA_CONTAS, contas);
            startActivity(intent);
        } else if (id == R.id.nav_cartoes) {
            Intent intent = new Intent(this, ListaActivity.class);
            intent.putExtra(CONST_LISTA_CARTOES, cartoes);
            startActivity(intent);
        } else if (id == R.id.nav_receitas) {
            Intent intent = new Intent(this, ListaActivity.class);
            intent.putExtra(CONST_LISTA_DESPESAS, receitas);
            startActivity(intent);
        } else if (id == R.id.nav_despesas) {
            Intent intent = new Intent(this, ListaActivity.class);
            intent.putExtra(CONST_LISTA_RECEITAS, despesas);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == REQUEST_CRIAR_CONTA) {
                contas = Utility.obterContas(this);
                mainFragment.atualizarListaContas(contas);
            } else if(requestCode == REQUEST_CRIAR_CARTAO){
                cartoes = Utility.obterCartoes(this);
                mainFragment.atualizarListaCartoes(Utility.obterCartoes(this));
            } else if(requestCode == REQUEST_CRIAR_DESPESA) {
                despesas = Utility.obterDespesas(this);
                mainFragment.atualizarListaDespesas(Utility.obterDespesas(this));
            } else {
                receitas = Utility.obterReceitas(this);
                mainFragment.atualizarListaReceitas(Utility.obterReceitas(this));
            }
        }
    }

    public void expandFAB() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fabCriarConta.getLayoutParams();
        layoutParams.rightMargin += (int) (fabCriarConta.getWidth() * 0.20);
        layoutParams.bottomMargin += (int) (fabCriarConta.getHeight() * 1.7);
        fabCriarConta.setLayoutParams(layoutParams);
        fabCriarConta.startAnimation(mostrar_criar_conta);
        fabCriarConta.setClickable(true);

        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) fabCriarCartao.getLayoutParams();
        layoutParams1.rightMargin += (int) (fabCriarCartao.getWidth() * 0.20);
        layoutParams1.bottomMargin += (int) (fabCriarCartao.getHeight() * 3.0);
        fabCriarCartao.setLayoutParams(layoutParams1);
        fabCriarCartao.startAnimation(mostrar_criar_cartao);
        fabCriarCartao.setClickable(true);

        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fabCriarreceita.getLayoutParams();
        layoutParams2.rightMargin += (int) (fabCriarreceita.getWidth() * 0.20);
        layoutParams2.bottomMargin += (int) (fabCriarreceita.getHeight() * 4.3);
        fabCriarreceita.setLayoutParams(layoutParams2);
        fabCriarreceita.startAnimation(mostrar_criar_receita);
        fabCriarreceita.setClickable(true);

        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fabCriarDespesa.getLayoutParams();
        layoutParams3.rightMargin += (int) (fabCriarDespesa.getWidth() * 0.20);
        layoutParams3.bottomMargin += (int) (fabCriarDespesa.getHeight() * 5.6);
        fabCriarDespesa.setLayoutParams(layoutParams3);
        fabCriarDespesa.startAnimation(mostrar_criar_despesa);
        fabCriarDespesa.setClickable(true);
    }

    public void hideFAB() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fabCriarConta.getLayoutParams();
        layoutParams.rightMargin -= (int) (fabCriarConta.getWidth() * 0.20);
        layoutParams.bottomMargin -= (int) (fabCriarConta.getHeight() * 1.7);
        fabCriarConta.setLayoutParams(layoutParams);
        fabCriarConta.startAnimation(esconder_criar_conta);
        fabCriarConta.setClickable(false);

        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) fabCriarCartao.getLayoutParams();
        layoutParams1.rightMargin -= (int) (fabCriarCartao.getWidth() * 0.20);
        layoutParams1.bottomMargin -= (int) (fabCriarCartao.getHeight() * 3.0);
        fabCriarCartao.setLayoutParams(layoutParams1);
        fabCriarCartao.startAnimation(esconder_criar_cartao);
        fabCriarCartao.setClickable(false);

        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fabCriarreceita.getLayoutParams();
        layoutParams2.rightMargin -= (int) (fabCriarreceita.getWidth() * 0.20);
        layoutParams2.bottomMargin -= (int) (fabCriarreceita.getHeight() * 4.3);
        fabCriarreceita.setLayoutParams(layoutParams2);
        fabCriarreceita.startAnimation(esconder_criar_receita);
        fabCriarreceita.setClickable(false);

        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fabCriarDespesa.getLayoutParams();
        layoutParams3.rightMargin -= (int) (fabCriarDespesa.getWidth() * 0.20);
        layoutParams3.bottomMargin -= (int) (fabCriarDespesa.getHeight() * 5.6);
        fabCriarDespesa.setLayoutParams(layoutParams3);
        fabCriarDespesa.startAnimation(esconder_criar_despesa);
        fabCriarDespesa.setClickable(false);
    }
}
