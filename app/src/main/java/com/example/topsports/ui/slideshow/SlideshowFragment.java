package com.example.topsports.ui.slideshow;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.topsports.AppDatabase;
import com.example.topsports.MainActivity;
import com.example.topsports.ProfileUser;
import com.example.topsports.R;
import com.example.topsports.databinding.FragmentSlideshowBinding;
import com.google.gson.Gson;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SlideshowFragment extends Fragment {
    private EditText editWeight, editHeight, editAge;
    private Spinner sportSpinner;
    private Button saveButton;
    private FragmentSlideshowBinding binding;
    private String[] sports = {"Football", "Basketball", "Tennis", "Swimming", "Running", "Cycling", "Golf", "Volleyball", "Baseball", "Table Tennis"};

    private SharedPreferences sharedPreferences;
    private Gson gson = new Gson();
    private Executor executor = Executors.newSingleThreadExecutor();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);

        editWeight = view.findViewById(R.id.editWeight);
        editHeight = view.findViewById(R.id.editHeight);
        editAge = view.findViewById(R.id.editAge);
        sportSpinner = view.findViewById(R.id.sportSpinner);
        saveButton = view.findViewById(R.id.saveButton);

        // Adapter pentru spinner cu sporturi
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, sports);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sportSpinner.setAdapter(adapter);

        // Setăm un listener pentru alegerea sportului
        sportSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSport = sports[position];
                saveSportToPreferences(selectedSport); // Salvăm sportul preferat în SharedPreferences
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Listener pentru butonul de salvare
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserDataToDatabase(); // Salvăm datele de utilizator în baza de date Room

            }
        });

        // Inițializăm SharedPreferences
        sharedPreferences =getActivity().getSharedPreferences("UserData", MODE_PRIVATE);

        return view;
    }



    private void saveSportToPreferences(String sport) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selectedSport", sport);
        editor.apply();
    }

    private void saveUserDataToDatabase() {
        final String weight = editWeight.getText().toString().trim();
        final String height = editHeight.getText().toString().trim();
        final String age = editAge.getText().toString().trim();
        final String selectedSport = sharedPreferences.getString("selectedSport", "");

        if (weight.isEmpty() || height.isEmpty() || age.isEmpty() || selectedSport.isEmpty()) {
            Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Salvăm datele de utilizator în baza de date Room folosind Executor pentru a rula într-un fir separat
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ProfileUser profileUser = new  ProfileUser(weight, height, age, selectedSport);
                AppDatabase appDatabase = AppDatabase.getInstance(getActivity());
                appDatabase.sportivDao().insert(profileUser);
            }
        });

        Toast.makeText(getActivity(), "User data saved successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}