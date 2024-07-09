package com.example.topsports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnRegister,btnLogin;
    private EditText edtEmail,edtPassword;
    public int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.etUsername);
        edtPassword = findViewById(R.id.etPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email = edtEmail.getText().toString().trim();
                String parola = edtPassword.getText().toString().trim();
                if (email.isEmpty() || parola.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Te rog completeaza toate campurile!", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
                    userId = appDatabase.sportivDao().findUsers(email, parola);
                    Toast.makeText(getApplicationContext(), String.valueOf(userId), Toast.LENGTH_SHORT).show();
                    if (userId == 0) {
                        Toast.makeText(getApplicationContext(), "Autentificare esuata!", Toast.LENGTH_LONG).show();
                    } else if (userId != 0) {
                        Intent intent = new Intent(MainActivity.this, MenuSport.class);
                        startActivity(intent);
                    }
                }

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });
    }
}