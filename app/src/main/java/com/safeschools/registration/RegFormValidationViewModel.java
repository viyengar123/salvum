package com.safeschools.registration;

import android.util.Patterns;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.safeschools.R;

public class RegFormValidationViewModel extends ViewModel {
    public MutableLiveData<RegistrationFormState> getRegistrationFormState() {
        return registrationFormState;
    }

    private MutableLiveData<RegistrationFormState> registrationFormState = new MutableLiveData<>();

    public RegFormValidationViewModel() {
        super();
    }


    public void registrationDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            registrationFormState.setValue(new RegistrationFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            registrationFormState.setValue(new RegistrationFormState(null, R.string.invalid_password));
        } else {
            registrationFormState.setValue(new RegistrationFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        return username != null && username.trim().length() > 5;
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
