package com.example.fragments1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;

    private boolean exitRequested = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailInput = findViewById(R.id.edit_email);
        passwordInput = findViewById(R.id.edit_password);
        loginButton = findViewById(R.id.button_login);

        // Check both fields whenever the user changes either one.
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                validateInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        emailInput.addTextChangedListener(textWatcher);
        passwordInput.addTextChangedListener(textWatcher);

        validateInputs();

        // Open the screen containing the three tabs.
        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    ModeActivity.class
            );
            startActivity(intent);
        });

        // Require two back presses to exit.
        getOnBackPressedDispatcher().addCallback(
                this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        if (exitRequested) {
                            finishAffinity();
                            return;
                        }

                        exitRequested = true;

                        Snackbar.make(
                                findViewById(R.id.main_layout),
                                "Press back again to exit",
                                Snackbar.LENGTH_LONG
                        ).show();
                    }
                }
        );
    }

    private void validateInputs() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString();

        boolean validEmail =
                Patterns.EMAIL_ADDRESS.matcher(email).matches();

        boolean validPassword = password.length() >= 6;

        loginButton.setEnabled(validEmail && validPassword);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Start a new exit sequence when returning to this screen.
        exitRequested = false;
    }
}