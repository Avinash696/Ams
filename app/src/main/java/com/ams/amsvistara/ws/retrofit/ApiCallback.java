package com.ams.amsvistara.ws.retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallback<T> implements Callback<T> {

    protected abstract void handleResponseData(T data);

    protected abstract void handleError(Response<T> response);

    protected abstract void handleException(Exception t);


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.body() != null) {
            handleResponseData(response.body());
        }else {
            handleError(response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof Exception) {
            handleException((Exception) t);
        }else {
            //do something else
        }
    }
}
