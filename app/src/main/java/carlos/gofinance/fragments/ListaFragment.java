package carlos.gofinance.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import carlos.gofinance.R;
import carlos.gofinance.activities.MainActivity;
import carlos.gofinance.adapters.ListaAdapter;
import carlos.gofinance.adapters.MainAdapter;
import carlos.gofinance.models.Cartao;
import carlos.gofinance.models.Conta;
import carlos.gofinance.models.Despesa;
import carlos.gofinance.models.Receita;

public class ListaFragment extends Fragment implements ListaAdapter.ListaAdapterItemClickListener {
    public static ArrayList<Conta> contas = new ArrayList<>();
    public static ArrayList<Cartao> cartoes = new ArrayList<>();
    public static ArrayList<Despesa> despesas = new ArrayList<>();
    public static ArrayList<Receita> receitas = new ArrayList<>();

    public static ListaFragment newInstance(ArrayList<Conta> contas, ArrayList<Cartao> cartoes,
                                            ArrayList<Despesa> despesas, ArrayList<Receita> receitas) {
        ListaFragment fragment = new ListaFragment();
        if (contas != null) {
            ListaFragment.contas = contas;
        } else if(cartoes != null) {
            ListaFragment.cartoes = cartoes;
        } else if(despesas != null) {
            ListaFragment.despesas = despesas;
        } else {
            ListaFragment.receitas = receitas;
        }
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ListaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista, container, false);
        Activity activity = getActivity();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recViewLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        /*
        ArrayList<String[]> dados = new ArrayList<>();
        if (contas != null) {
            for(Conta conta : contas) {
                dados.add(new String[]{conta.getNomeBanco(), conta.getValorConta() + "", conta.});
            }
        } else if(cartoes != null) {
            ListaFragment.cartoes = cartoes;
        } else if(despesas != null) {
            ListaFragment.despesas = despesas;
        } else {
            ListaFragment.receitas = receitas;
        }

        ListaAdapter adapter = new ListaAdapter(this, activity);*/
        return view;
    }

    @Override
    public void itemClickListener(int index) {

    }
}
