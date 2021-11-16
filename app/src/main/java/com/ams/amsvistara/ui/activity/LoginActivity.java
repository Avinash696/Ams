package com.ams.amsvistara.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.multidex.BuildConfig;


import com.ams.amsvistara.R;
import com.ams.amsvistara.databinding.ActivityLoginBinding;
import com.ams.amsvistara.model.LoginUserModel;
import com.ams.amsvistara.utils.SharedPrefUtil;
import com.ams.amsvistara.viewmodel.LoginViewModel;

import java.util.Objects;

import static com.ams.amsvistara.constants.ConstantStr.USER_LOCATION;
import static com.ams.amsvistara.constants.ConstantStr.USER_NAME;
import static com.ams.amsvistara.constants.ConstantStr.USER_TOKEN;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);

        binding.setLifecycleOwner(this);

        binding.setViewModel(loginViewModel);
        binding.txtAppVersion.setText("V:" + BuildConfig.VERSION_NAME);
        loginViewModel.callSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                loginViewModel.showProgress.setValue(false);
                if (aBoolean) {
                    SharedPrefUtil.putString(USER_TOKEN, loginViewModel.UserToken.getValue(), LoginActivity.this);
                    try {
                        String result = loginViewModel.UserToken.getValue();
                        Log.e("####", "@@@@@ ############result " + result);
                        if (result.contains("|")) {
                            String[] strs = result.split("\\|");
                            Log.e("####", "@@@@@ ############ " + strs[0] + "####" + strs[1]);
                            Log.e("####", "@@@@@ ############strs[1] " + strs[1]);
                            if (strs[1].contains(":")) {
                                String[] strs_ = strs[1].split(":");
                                Log.e("####", "@@@@@ ############ " + strs_[0] + "####" + strs_[1]);
                                SharedPrefUtil.putString(USER_LOCATION, strs_[1], LoginActivity.this);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Request Failed. Please Recheck the Username and Password.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginViewModel.getUser().observe(this, new Observer<LoginUserModel>() {
            @Override
            public void onChanged(@Nullable LoginUserModel loginUser) {

                if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getStrEmailAddress())) {
                    binding.txtEmailAddress.setError("Enter an E-Mail Address");
                    binding.txtEmailAddress.requestFocus();
                } else if (!loginUser.isEmailValid()) {
                    binding.txtEmailAddress.setError("Enter a Valid E-mail Address");
                    binding.txtEmailAddress.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getStrPassword())) {
                    binding.txtPassword.setError("Enter a Password");
                    binding.txtPassword.requestFocus();
                } else if (!loginUser.isPasswordLengthGreaterThan5()) {
                    binding.txtPassword.setError("Enter at least 6 Digit password");
                    binding.txtPassword.requestFocus();
                } else {
                    SharedPrefUtil.putString(USER_NAME, loginUser.getStrEmailAddress(), LoginActivity.this);
                  /*  binding.lblEmailAnswer.setText(loginUser.getStrEmailAddress());
                    binding.lblPasswordAnswer.setText(loginUser.getStrPassword());*/

                }

            }
        });

    }

}