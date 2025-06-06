package com.example.dclassicbookhomepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText usernameInput;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Pastikan layout ini ada

        usernameInput = findViewById(R.id.usernameInput); // ID harus sesuai di XML
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getText().toString().trim();
                if (!username.isEmpty()) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    finish();
                } else {
                    usernameInput.setError("Please enter your name");
                }
            }
        });
    }
}
