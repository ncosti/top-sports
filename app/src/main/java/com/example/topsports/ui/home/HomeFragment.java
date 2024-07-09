package com.example.topsports.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topsports.R;
import com.example.topsports.SportAdapter;
import com.example.topsports.SportCount;
import com.example.topsports.databinding.FragmentHomeBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private SportAdapter adapter;
    private static final String TAG = "HomeFragment";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Permiteți operațiunile de rețea pe firul principal (nu este recomandat pentru producție)
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        fetchSportsData();

        return view;
    }

    private void fetchSportsData() {
        String apiUrl = "https://pastebin.com/raw/Sd8R4Asw";
        new FetchSportsTask().execute(apiUrl);
    }

    private class FetchSportsTask extends AsyncTask<String, Void, List<SportCount>> {

        @Override
        protected List<SportCount> doInBackground(String... params) {
            String apiUrl = params[0];
            List<SportCount> sportsList = new ArrayList<>();

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                int responseCode = urlConnection.getResponseCode();
                Log.d(TAG, "Response code: " + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.d(TAG, "Response: " + response.toString());

                    Gson gson = new GsonBuilder().create();
                    SportCount[] sportArray = gson.fromJson(response.toString(), SportCount[].class);
                    sportsList = Arrays.asList(sportArray);
                } else {
                    getActivity().runOnUiThread(() -> Toast.makeText(getActivity(), "Eroare la încărcarea datelor: " + responseCode, Toast.LENGTH_SHORT).show());
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "Eroare la încărcarea datelor: ", e);
                getActivity().runOnUiThread(() -> Toast.makeText(getActivity(), "Eroare la încărcarea datelor: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            return sportsList;
        }

        @Override
        protected void onPostExecute(List<SportCount> sportsList) {
            if (sportsList != null && !sportsList.isEmpty()) {
                Log.d(TAG, "Number of sports received: " + sportsList.size());
                adapter = new SportAdapter(sportsList);
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "Nu s-au găsit sporturi sau eroare la încărcarea datelor", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
