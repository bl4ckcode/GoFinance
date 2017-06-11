package carlos.gofinance.adapters;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

import carlos.gofinance.R;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainAdapterViewholder> {
    public interface MainAdapterItemClickListener {
        public void itemClickListener(int index);
    }

    private MainAdapterItemClickListener listener;
    private ArrayList<String[]> dados;
    private Resources recursos;
    private Activity activity;

    public MainAdapter(ArrayList<String[]> dados, MainAdapterItemClickListener listener, Activity activity) {
        this.dados = dados;
        this.listener = listener;
        this.recursos = activity.getResources();
        this.activity = activity;
    }

    class MainAdapterViewholder extends RecyclerView.ViewHolder {
        private RelativeLayout rlContainer;
        private ImageView icone;
        private TextView descricao;
        private TextView quantidadeCadastradas;
        private TextView valor;
        private PieChart grafico;

        MainAdapterViewholder(View itemView) {
            super(itemView);

            rlContainer = (RelativeLayout) itemView.findViewById(R.id.rl_container);
            icone = (ImageView) itemView.findViewById(R.id.icone);
            descricao = (TextView) itemView.findViewById(R.id.descricao);
            quantidadeCadastradas = (TextView) itemView.findViewById(R.id.quantidade_cadastradas);
            valor = (TextView) itemView.findViewById(R.id.valor);
            grafico = (PieChart) itemView.findViewById(R.id.grafico);
        }
    }


    @Override
    public MainAdapterViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main, parent, false);
        return new MainAdapterViewholder(mView);
    }

    @Override
    public void onBindViewHolder(MainAdapterViewholder holder, int position) {
        String[] objeto = dados.get(position);

        holder.descricao.setText(objeto[0]);
        int qtd = Integer.parseInt(objeto[1]);
        holder.quantidadeCadastradas.setText(qtd == 1 ? qtd + " cadastrada" : qtd + " cadastradas");

        if (position == 1) {
            holder.rlContainer.getBackground().setColorFilter(recursos.getColor(R.color.circle_shape_red, null), PorterDuff.Mode.DST_ATOP);
            holder.icone.setImageDrawable(activity.getDrawable(R.drawable.ic_despesa));
        } else if (position == 2) {
            holder.icone.setImageDrawable(activity.getDrawable(R.drawable.ic_carteira));
        } else if (position == dados.size() - 1) {
            holder.icone.setImageDrawable(activity.getDrawable(R.drawable.ic_cartao));
            holder.quantidadeCadastradas.setText(qtd == 1 ? qtd + " cadastrado" : qtd + " cadastrados");
        }

        String valor = "R$ " + objeto[2];
        holder.valor.setText(valor);
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public void setDados(ArrayList<String[]> dados) {
        this.dados = dados;
        notifyDataSetChanged();
    }
}
