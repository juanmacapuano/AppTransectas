package com.example.transectas.samplings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.transectas.R;
import com.example.transectas.data.Muestreos;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SamplingsAdapter extends RecyclerView.Adapter<SamplingsAdapter.SamplingsViewHolder>  implements Filterable {

    private final LayoutInflater mInflater;
    private List<Muestreos> mSamplings;
    private static SamplingsAdapter.ClickListener clickListener;

    private List<Muestreos> listSamplingComplete;

    public SamplingsAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SamplingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_muestreo, parent, false);
        return new SamplingsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SamplingsViewHolder holder, int position) {

        if (mSamplings != null) {
            Muestreos current = mSamplings.get(position);
            Integer number = current.getNumero();
            holder.tvNumero.setText("Nro: " + number);
        } else {
            // Covers the case of data not being ready yet.
            holder.tvNumero.setText(R.string.no_transecta);
        }
    }

    /**
     * Associates a list of muestreos with this adapter
     */
    public void setMuestreos(List<Muestreos> muestreos) {
        mSamplings = muestreos;
        listSamplingComplete = new ArrayList<>(mSamplings);
        notifyDataSetChanged();
    }

    /**
     * getItemCount() is called many times, and when it is first called,
     * mWords has not been updated (means initially, it's null, and we can't return null).
     */
    @Override
    public int getItemCount() {
        if (mSamplings != null)
            return mSamplings.size();
        else return 0;
    }

    @Override
    public Filter getFilter() {
        return listSamplingsFiltrate;
    }

    private final Filter listSamplingsFiltrate = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Muestreos> listFiltrateSampling = new ArrayList<>();
            if(charSequence == null || charSequence.length() == 0){
                listFiltrateSampling.addAll(listSamplingComplete);
            }else {
                String filterPatter = charSequence.toString().toLowerCase().trim();
                for (Muestreos sampling: listSamplingComplete){
                    if(sampling.getNumero().toString().toLowerCase().contains(filterPatter)){
                        listFiltrateSampling.add(sampling);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = listFiltrateSampling;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mSamplings.clear();
            mSamplings.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public Muestreos getSamplingAtPosition(int position) {
        return mSamplings.get(position);
    }

    public class SamplingsViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNumero;

        public SamplingsViewHolder(View itemView) {
            super(itemView);
            tvNumero = itemView.findViewById(R.id.tvNumeroMuest);
            itemView.setOnClickListener(view -> clickListener.onItemClick(view, getAdapterPosition()));
        }
    }

    public void setOnItemClickListener(SamplingsAdapter.ClickListener clickListener) {
        SamplingsAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);
    }
}
