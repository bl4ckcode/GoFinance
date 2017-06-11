package carlos.gofinance.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import carlos.gofinance.R;
import carlos.gofinance.adapters.MainAdapter;
import carlos.gofinance.models.Cartao;
import carlos.gofinance.models.Conta;
import carlos.gofinance.models.Despesa;
import carlos.gofinance.models.Receita;

public class MainFragment extends Fragment implements MainAdapter.MainAdapterItemClickListener {
    private static final String LISTA_CONTAS = "contas";
    private static final String LISTA_CARTOES = "cartoes";
    private static final String LISTA_DESPESAS = "despesas";
    private static final String LISTA_RECEITAS = "receitas";

    private ArrayList<Conta> contas = new ArrayList<>();
    private ArrayList<Cartao> cartoes = new ArrayList<>();
    private ArrayList<Despesa> despesas = new ArrayList<>();
    private ArrayList<Receita> receitas = new ArrayList<>();
    private ArrayList<String[]> dados;

    private MainAdapter adapter;

    public static MainFragment newInstance(ArrayList<Conta> contas, ArrayList<Cartao> cartoes,
                                           ArrayList<Despesa> despesas, ArrayList<Receita> receitas) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();

        args.putSerializable(LISTA_CONTAS, contas);
        args.putSerializable(LISTA_CARTOES, cartoes);
        args.putSerializable(LISTA_DESPESAS, despesas);
        args.putSerializable(LISTA_RECEITAS, receitas);

        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            contas = (ArrayList<Conta>) getArguments().getSerializable(LISTA_CONTAS);
            cartoes = (ArrayList<Cartao>) getArguments().getSerializable(LISTA_CARTOES);
            despesas = (ArrayList<Despesa>) getArguments().getSerializable(LISTA_DESPESAS);
            receitas = (ArrayList<Receita>) getArguments().getSerializable(LISTA_RECEITAS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Activity activity = getActivity();

        RelativeLayout relLayoutMes = (RelativeLayout) view.findViewById(R.id.relLayoutMes);
        ImageView imgViewMesAnterior = (ImageView) view.findViewById(R.id.imgViewMesAnterior);
        TextView txtViewMes = (TextView) view.findViewById(R.id.txtViewMes);
        ImageView imgViewMesSeguinte = (ImageView) view.findViewById(R.id.imgViewMesSeguinte);

        RecyclerView recViewLista = (RecyclerView) view.findViewById(R.id.recViewLista);
        recViewLista.setLayoutManager(new LinearLayoutManager(activity));

        dados = new ArrayList<>();

        double valor = 0.0;
        for(Conta conta : contas) {
            valor += conta.getValorConta();
        }
        dados.add(new String[]{"Conta", contas.size() + "", valor + ""});

        valor = 0.0;
        for(Despesa despesa : despesas) {
            valor += despesa.getDespesaPaga();
        }
        dados.add(new String[]{"Despesa", despesas.size() + "", valor + ""});

        valor = 0.0;
        for(Receita receita : receitas) {
            valor += receita.getValorReceita();
        }
        dados.add(new String[]{"Receita", receitas.size() + "", valor + ""});

        valor = 0.0;
        for(Cartao cartao : cartoes) {
            valor += cartao.getValorConta();
        }

        dados.add(new String[]{"Cartão", cartoes.size() + "", valor + ""});

        adapter = new MainAdapter(dados, this, activity);
        recViewLista.setAdapter(adapter);
        return view;
    }

    @Override
    public void itemClickListener(int index) {

    }

    public void atualizarListaContas(ArrayList<Conta> contas) {
        double valor = 0.0;
        for(Conta conta : contas) {
            valor += conta.getValorConta();
        }
        dados.set(0, new String[]{"Conta", contas.size() + "", valor + ""});
        adapter.setDados(dados);
    }

    public void atualizarListaDespesas(ArrayList<Despesa> despesas) {
        double valor = 0.0;
        for(Despesa despesa : despesas) {
            valor += despesa.getDespesaPaga();
        }
        dados.add(new String[]{"Despesa", despesas.size() + "", valor + ""});
        adapter.setDados(dados);
    }

    public void atualizarListaReceitas(ArrayList<Receita> receitas) {
        double valor = 0.0;
        for(Receita receita : receitas) {
            valor += receita.getValorReceita();
        }
        dados.add(new String[]{"Receita", receitas.size() + "", valor + ""});
        adapter.setDados(dados);
    }

    public void atualizarListaCartoes(ArrayList<Cartao> cartoes) {
        double valor = 0.0;
        for(Cartao cartao : cartoes) {
            valor += cartao.getValorConta();
        }
        dados.set(3, new String[]{"Cartão", cartoes.size() + "", valor + ""});
        adapter.setDados(dados);
    }
}
