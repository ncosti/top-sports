package com.example.topsports;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.List;

public class PieChartFragment extends Fragment {

    private List<SportCount> sportsList;

    public PieChartFragment(List<SportCount> sportsList) {
        this.sportsList = sportsList;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pie_chart, container, false);

        PieChart pieChart = view.findViewById(R.id.piechart);

       
        int totalTeamMembers = 0;
        for (SportCount sport : sportsList) {
            totalTeamMembers += Integer.parseInt(sport.getNrSport());
        }

   
        for (SportCount sport : sportsList) {
            float percentage = (Float.parseFloat(sport.getNrSport()) / totalTeamMembers) * 100;
            pieChart.addPieSlice(new PieModel(sport.getNumeSport(), percentage, Color.parseColor(getRandomColor())));
        }

        pieChart.startAnimation();

        return view;
    }

    
    private String getRandomColor() {
        String[] colors = {"#FF5722", "#FFC107", "#FFEB3B", "#4CAF50", "#03A9F4", "#9C27B0", "#E91E63", "#795548", "#607D8B", "#9E9E9E"};
        int randomNum = (int) (Math.random() * colors.length);
        return colors[randomNum];
    }
}
