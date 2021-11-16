package com.ams.amsvistara.viewmodel;

import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ams.amsvistara.model.LoginUserModel;
import com.ams.amsvistara.model.req.LoginReqModel;
import com.ams.amsvistara.ws.repo.ILoginRepo;
import com.ams.amsvistara.ws.repo.LoginRepo;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;

import static com.ams.amsvistara.constants.AppConstant.android_id;


public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();
    public MutableLiveData<Boolean> callSuccess = new MutableLiveData<>();
    public MutableLiveData<String> UserToken = new MutableLiveData<>();
    public MutableLiveData<Boolean> showProgress = new MutableLiveData<>();
    private MutableLiveData<LoginUserModel> userMutableLiveData;

    public MutableLiveData<LoginUserModel> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public void onClick(View view) {

        LoginUserModel loginUser = new LoginUserModel(EmailAddress.getValue(), Password.getValue());

        userMutableLiveData.setValue(loginUser);
        if(!TextUtils.isEmpty(EmailAddress.getValue()) && !TextUtils.isEmpty(Password.getValue())) {
            showProgress.setValue(true);
            LoginReqModel loginReqModel = new LoginReqModel();
            loginReqModel.Password = userMutableLiveData.getValue().getStrPassword();
            loginReqModel.Username = userMutableLiveData.getValue().getStrEmailAddress();
            Map<String, String> req= new HashMap<String, String>();
          //  req.put("Username",android_id+"|"+userMutableLiveData.getValue().getStrEmailAddress());
            req.put("Username",userMutableLiveData.getValue().getStrEmailAddress());
            req.put("Password",userMutableLiveData.getValue().getStrPassword());
            LoginRepo.getInstance().getLogin(req, new ILoginRepo.LoginCallback() {
                @Override
                public void onDataLoaded(Response<ResponseBody> loginReqModel) {
                  //  Log.e("####","########### loginReqModel "+loginReqModel.headers().get("Token"));
                    UserToken.setValue(loginReqModel.headers().get("Token"));
                    callSuccess.setValue(true);
                }

                @Override
                public void onDataNotAvailable() {
                    callSuccess.setValue(false);
                }
            });
        }

    }
}
