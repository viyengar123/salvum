package com.safeschools.login;

import android.util.Patterns;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.safeschools.R;

public class LoginFormValidationViewModel extends ViewModel {

    public MutableLiveData<LoginFormState> getRegistrationFormState() {
        return loginFormState;
    }

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();

    public LoginFormValidationViewModel() {
        super();
    }


    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        /*if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }*/
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
