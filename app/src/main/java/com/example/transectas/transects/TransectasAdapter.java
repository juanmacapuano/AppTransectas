package com.example.transectas.transects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.transectas.R;
import com.example.transectas.data.Transectas;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TransectasAdapter extends RecyclerView.Adapter<TransectasAdapter.TransectasViewHolder>  implements Filterable {

    private final LayoutInflater mInflater;
    private List<Transectas> mTransectas; // Cached copy of words
    private static TransectasAdapter.ClickListener clickListener;

    private List<Transectas> listaTransectasCompleta;

    public TransectasAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TransectasAdapter.TransectasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_transecta, parent, false);
        return new TransectasAdapter.TransectasViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransectasAdapter.TransectasViewHolder holder, int position) {

        if (mTransectas != null) {
            Transectas current = mTransectas.get(position);
            Integer numero = current.getNumero();
            holder.tvNumero.setText("Nro: " + numero);
            holder.tvFechaCreacion.setText("Fecha Creación: " + current.getFechaCreacion());
        } else {
            // Covers the case of data not being ready yet.
            holder.tvNumero.setText(R.string.no_transecta);
            holder.tvFechaCreacion.setText(R.string.no_transecta);
        }
    }

    /**
     * Associates a list of transectas with this adapter
     */
    public void setTransectas(List<Transectas> transectas) {
        mTransectas = transectas;
        listaTransectasCompleta = new ArrayList<>(mTransectas);
        notifyDataSetChanged();
    }

    /**
     * getItemCount() is called many times, and when it is first called,
     * mWords has not been updated (means initially, it's null, and we can't return null).
     */
    @Override
    public int getItemCount() {
        if (mTransectas != null)
            return mTransectas.size();
        else return 0;
    }

    @Override
    public Filter getFilter() {
        return listaTransectasFiltrada;
    }

    private Filter listaTransectasFiltrada = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Transectas> listaFiltradaTransectas = new ArrayList<>();
            if(charSequence == null || charSequence.length() == 0){
                listaFiltradaTransectas.addAll(listaTransectasCompleta);
            }else {
                String filterPatter = charSequence.toString().toLowerCase().trim();
                for (Transectas transecta:listaTransectasCompleta){
                    if(transecta.getNumero().toString().toLowerCase().contains(filterPatter)){
                        listaFiltradaTransectas.add(transecta);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = listaFiltradaTransectas;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mTransectas.clear();
            mTransectas.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    /**
     * Gets the transecta at a given position.
     * This method is useful for identifying which project
     * was clicked or swiped in methods that handle user events.
     *
     * @param position The position of the project in the RecyclerView
     * @return The project at the given position
     */
    public Transectas getTransectaAtPosition(int position) {
        return mTransectas.get(position);
    }

    public class TransectasViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNumero;
        private TextView tvFechaCreacion;

        public TransectasViewHolder(View itemView) {
            super(itemView);
            tvNumero = itemView.findViewById(R.id.tvNumero);
            tvFechaCreacion = itemView.findViewById(R.id.tvFechaCreacion);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(view, getAdapterPosition());
                }
            });
        }
    }

    public void setOnItemClickListener(TransectasAdapter.ClickListener clickListener) {
        TransectasAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);
    }
}
