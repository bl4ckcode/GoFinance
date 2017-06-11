package carlos.gofinance.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import carlos.gofinance.R;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ListaAdapterViewholder> {
    public interface ListaAdapterItemClickListener {
        public void itemClickListener(int index);
    }

    private ListaAdapterItemClickListener listener;

    public ListaAdapter(ArrayList<String[]> dados, ListaAdapterItemClickListener listener, Activity activity) {

    }

    class ListaAdapterViewholder extends RecyclerView.ViewHolder {
        private LinearLayout llContainer;
        private TextView txtViewDia;
        private TextView txtViewTitle;
        private TextView txtViewTipo;
        private TextView txtViewValor;
        private TextView txtViewStatus;

        ListaAdapterViewholder(View itemView) {
            super(itemView);

            llContainer = (LinearLayout) itemView.findViewById(R.id.ll_container);
            txtViewDia = (TextView) itemView.findViewById(R.id.txtView_dia);
            txtViewTitle = (TextView) itemView.findViewById(R.id.txtView_title);
            txtViewTipo = (TextView) itemView.findViewById(R.id.txtView_tipo);
            txtViewValor = (TextView) itemView.findViewById(R.id.txtView_valor);
            txtViewStatus = (TextView) itemView.findViewById(R.id.txtView_status);
        }
    }


    @Override
    public ListaAdapterViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main, parent, false);
        return new ListaAdapterViewholder(mView);
    }

    @Override
    public void onBindViewHolder(ListaAdapterViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
