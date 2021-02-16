package com.example.transectas.findings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.transectas.R;
import com.example.transectas.data.Hallazgos;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FindingsAdapter extends RecyclerView.Adapter<FindingsAdapter.HallazgosViewHolder>  implements Filterable {

    private final LayoutInflater mInflater;
    private List<Hallazgos> mHallazgos;
    private static FindingsAdapter.ClickListener clickListener;

    private List<Hallazgos> listaHallazgosCompleta;

    public FindingsAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FindingsAdapter.HallazgosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_hallazgo, parent, false);
        return new FindingsAdapter.HallazgosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FindingsAdapter.HallazgosViewHolder holder, int position) {

        if (mHallazgos != null) {
            Hallazgos current = mHallazgos.get(position);
            Integer numero = current.getOcurrencia();
            holder.tvNumero.setText("Nro: " + numero);
        } else {
            // Covers the case of data not being ready yet.
            holder.tvNumero.setText(R.string.no_transecta);
        }
    }

    /**
     * Associates a list of hallazgos with this adapter
     */
    public void setHallazgos(List<Hallazgos> hallazgos) {
        mHallazgos = hallazgos;
        listaHallazgosCompleta = new ArrayList<>(mHallazgos);
        notifyDataSetChanged();
    }

    /**
     * getItemCount() is called many times, and when it is first called,
     * mHallazgos has not been updated (means initially, it's null, and we can't return null).
     */
    @Override
    public int getItemCount() {
        if (mHallazgos != null)
            return mHallazgos.size();
        else return 0;
    }

    @Override
    public Filter getFilter() {
        return listaHallazgoFiltrada;
    }

    private Filter listaHallazgoFiltrada = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Hallazgos> listaHallazgoFiltrada = new ArrayList<>();
            if(charSequence == null || charSequence.length() == 0){
                listaHallazgoFiltrada.addAll(listaHallazgosCompleta);
            }else {
                String filterPatter = charSequence.toString().toLowerCase().trim();
                for (Hallazgos hallazgo:listaHallazgosCompleta){
                    if(hallazgo.getOcurrencia().toString().toLowerCase().contains(filterPatter)){
                        listaHallazgoFiltrada.add(hallazgo);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = listaHallazgoFiltrada;
            return results;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mHallazgos.clear();
            mHallazgos.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    /**
     * Gets the hallazgo at a given position.
     * This method is useful for identifying which project
     * was clicked or swiped in methods that handle user events.
     *
     * @param position The position of the project in the RecyclerView
     * @return The project at the given position
     */
    public Hallazgos getHallazgoAtPosition(int position) {
        return mHallazgos.get(position);
    }

    public class HallazgosViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNumero;

        public HallazgosViewHolder(View itemView) {
            super(itemView);
            tvNumero = itemView.findViewById(R.id.tvNumeroHallazgo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(view, getAdapterPosition());
                }
            });
        }
    }

    public void setOnItemClickListener(FindingsAdapter.ClickListener clickListener) {
        FindingsAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);
    }
}

