package com.example.transectas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.transectas.data.Proyectos;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectsViewHolder>  implements Filterable{

    private final LayoutInflater mInflater;
    private List<Proyectos> mProjects;
    private static ClickListener clickListener;

    private List<Proyectos> listCompleteProject;

    public ProjectsAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProjectsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_proyecto, parent, false);
        return new ProjectsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsViewHolder holder, int position) {

        if (mProjects != null) {
            Proyectos current = mProjects.get(position);
            holder.tvNombre.setText("Proy. Nº: " + current.getNombre());
            holder.tvUbicacion.setText("Ubicación: " + current.getUbicacion());
            holder.tvFechaCreacion.setText("Fecha: " + current.getFechaCreacion());
        } else {
            // Covers the case of data not being ready yet.
            holder.tvNombre.setText(R.string.no_project);
            holder.tvUbicacion.setText(R.string.no_project);
            holder.tvFechaCreacion.setText(R.string.no_project);
        }
    }

    /**
     * Associates a list of words with this adapter
     */
    void setProyectos(List<Proyectos> proyectos) {
        mProjects = proyectos;
        listCompleteProject = new ArrayList<>(mProjects);
        notifyDataSetChanged();
    }

    /**
     * getItemCount() is called many times, and when it is first called,
     * mWords has not been updated (means initially, it's null, and we can't return null).
     */
    @Override
    public int getItemCount() {
        if (mProjects != null)
            return mProjects.size();
        else return 0;
    }

    @Override
    public Filter getFilter() {
        return listProjectsFiltrated;
    }

    private Filter listProjectsFiltrated = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Proyectos> listFiltratedProjects = new ArrayList<>();
            if(charSequence == null || charSequence.length() == 0){
                listFiltratedProjects.addAll(listCompleteProject);
            }else {
                String filterPatter = charSequence.toString().toLowerCase().trim();
                for (Proyectos project: listCompleteProject){
                    if(project.getNombre().toLowerCase().contains(filterPatter)){
                        listFiltratedProjects.add(project);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = listFiltratedProjects;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mProjects.clear();
            mProjects.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    /**
     * Gets the project at a given position.
     * This method is useful for identifying which project
     * was clicked or swiped in methods that handle user events.
     *
     * @param position The position of the project in the RecyclerView
     * @return The project at the given position
     */
    public Proyectos getProjectAtPosition(int position) {
        return mProjects.get(position);
    }

    public class ProjectsViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNombre;
        private TextView tvUbicacion;
        private TextView tvFechaCreacion;

        public ProjectsViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvUbicacion = itemView.findViewById(R.id.tvUbicacion);
            tvFechaCreacion = itemView.findViewById(R.id.tvFechaCreacion);
            itemView.setOnClickListener(view -> clickListener.onItemClick(view, getAdapterPosition()));
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ProjectsAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);
    }
}

