package com.example.topsports;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText etNume,etEmail,etSport,etVarsta,etInaltime,etGreutate,etOras,etParola;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNume = findViewById(R.id.etNume);
        etEmail = findViewById(R.id.etEmail);
        etSport = findViewById(R.id.etSport);
        etVarsta = findViewById(R.id.etVarsta);
        etInaltime = findViewById(R.id.etInaltime);
        etGreutate = findViewById(R.id.etGreutate);
        etOras = findViewById(R.id.etOras);
        btnRegister = findViewById(R.id.btnRegister);
        etParola = findViewById(R.id.etParola);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNume.getText().toString().isEmpty()){
                    etNume.setError("Introduceti numele!");
                }else if (etParola.getText().toString().isEmpty()) {
                    etParola.setError("Introduceti parola!");
                } else if (etEmail.getText().toString().isEmpty()) {
                    etEmail.setError("Introduceti email-ul!");
                }else if (etSport.getText().toString().isEmpty()) {
                    etSport.setError("Introduceti sportul!");
                }
                else if (etVarsta.getText().toString().isEmpty()) {
                    etVarsta.setError("Introduceti varsta!");
                }
                else if (etInaltime.getText().toString().isEmpty()) {
                    etInaltime.setError("Introduceti inaltimea!");
                }
                else if (etGreutate.getText().toString().isEmpty()) {
                    etGreutate.setError("Introduceti greutatea!");
                }
                else if (etOras.getText().toString().isEmpty()) {
                    etOras.setError("Introduceti orasul!");
                }else{
                    Sportiv s = new Sportiv(etParola.getText().toString(),etNume.getText().toString(),etEmail.getText().toString(),etSport.getText().toString(),Integer.parseInt(etVarsta.getText().toString()),Integer.parseInt(etInaltime.getText().toString()),Integer.parseInt(etGreutate.getText().toString()),etOras.getText().toString());
                    AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
                    appDatabase.sportivDao().insert(s);
                    Toast.makeText(getApplicationContext(), "Cont creat cu succes!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}