package com.example.topsports;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SportivAdapter extends RecyclerView.Adapter<SportivAdapter.SportivViewHolder>{
    private List<Sportiv> sportivi;

    public static class SportivViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNume, tvEmail, tvSport, tvVarsta, tvInaltime, tvGreutate, tvOras;

        public SportivViewHolder(View itemView) {
            super(itemView);
            tvNume = itemView.findViewById(R.id.tvNume);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvSport = itemView.findViewById(R.id.tvSport);
            tvVarsta = itemView.findViewById(R.id.tvVarsta);
            tvInaltime = itemView.findViewById(R.id.tvInaltime);
            tvGreutate = itemView.findViewById(R.id.tvGreutate);
            tvOras = itemView.findViewById(R.id.tvOras);
        }
    }

    public SportivAdapter(List<Sportiv> sportivi) {
        this.sportivi = sportivi;
    }

    @Override
    public SportivViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_sportiv, parent, false);
        return new SportivViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SportivViewHolder holder, int position) {
        Sportiv sportiv = sportivi.get(position);
        holder.tvNume.setText(sportiv.getNume());
        holder.tvEmail.setText(sportiv.getEmail());
        holder.tvSport.setText(sportiv.getSport());
        holder.tvVarsta.setText(String.valueOf(sportiv.getVarsta()));
        holder.tvInaltime.setText(String.valueOf(sportiv.getInaltime()));
        holder.tvGreutate.setText(String.valueOf(sportiv.getGreutate()));
        holder.tvOras.setText(sportiv.getOras());
    }

    @Override
    public int getItemCount() {
        return sportivi.size();
    }

    public void setSportivi(List<Sportiv> sportivi) {
        this.sportivi = sportivi;
        notifyDataSetChanged();
    }
}
