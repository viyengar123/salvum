package com.safeschools.registration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.safeschools.R;

public class RegistrationActivity extends AppCompatActivity {

    private RegFormValidationViewModel regViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        regViewModel = new ViewModelProvider(this, new RegistrationViewModelFactory())
                .get(RegFormValidationViewModel.class);

        final EditText usernameEditText = findViewById(R.id.reg_username);
        final EditText passwordEditText = findViewById(R.id.reg_password);
        final Button registrationButton = findViewById(R.id.reg_button);

        regViewModel.getRegistrationFormState().observe(this, new Observer<RegistrationFormState>() {
            @Override
            public void onChanged(@Nullable RegistrationFormState regFormState) {
                if (regFormState == null) {
                    return;
                }
                registrationButton.setEnabled(regFormState.isDataValid());
                if (regFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(regFormState.getUsernameError()));
                }
                if (regFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(regFormState.getPasswordError()));
                }
            }
        });
        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                regViewModel.registrationDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
    }
}