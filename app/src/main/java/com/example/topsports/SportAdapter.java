package com.example.topsports;



import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.SportViewHolder>{
    private List<SportCount> sports;
    private static final String TAG = "SportAdapter";
    public static class SportViewHolder extends RecyclerView.ViewHolder {
        public TextView idSportName;
        public TextView sportName;
        public TextView numberSportName;

        public SportViewHolder(View itemView) {
            super(itemView);
            idSportName = itemView.findViewById(R.id.tvIdSport);
            sportName = itemView.findViewById(R.id.tvNumeSport);
            numberSportName = itemView.findViewById(R.id.tvNrSport);
        }
    }

    public SportAdapter(List<SportCount> sports) {
        this.sports = sports;
    }

    @Override
    public SportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sport_layout_request, parent, false);
        return new SportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SportViewHolder holder, int position) {
        SportCount sport = sports.get(position);

        Log.d(TAG, "Sport name: " + sport.getNumeSport());
        holder.idSportName.setText("ID: "+sport.getIdSport());
        holder.sportName.setText("Nume sport: "+sport.getNumeSport());
        holder.numberSportName.setText("Numar jucatori: "+String.valueOf(sport.getNrSport()));
    }
    @Override
    public int getItemCount() {
        return sports.size();
    }

    public void setSports(List<SportCount> sports) {
        this.sports = sports;
        notifyDataSetChanged();
    }
}

