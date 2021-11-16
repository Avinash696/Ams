package com.ams.amsvistara.viewmodel;

import android.app.Application;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public abstract class BaseViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> busy;
    //    private MutableLiveData<NetworkError> errors;
    public MutableLiveData<Boolean> dataNotAvailable = new MutableLiveData<>();

    public final MutableLiveData<Boolean> dataLoading = new MutableLiveData<>();
    public final MutableLiveData<Boolean> swipeToRefresh = new MutableLiveData<>();
    public final MutableLiveData<String> message = new MutableLiveData<>();
    public MutableLiveData<String> showToastMessage = new MutableLiveData<>();


    public BaseViewModel(@NonNull Application application) {
        super(application);
    }


    public MutableLiveData<Integer> getBusy() {
        if (busy == null) {
            busy = new MutableLiveData<>();
            if (Looper.myLooper() == Looper.getMainLooper()) {
                // Current Thread is Main Thread.
                busy.setValue(8);
            } else {
                busy.postValue(8);
            }

        }
        return busy;
    }

  /*  public MutableLiveData<NetworkError> getErrors() {
        if (errors == null) {
            errors = new MutableLiveData<>();
//            errors.setValue(NetworkError.SUCCESS);
        }
        return errors;
    }*/

    public MutableLiveData<Boolean> getDataLoading() {
        return dataLoading;
    }

    public MutableLiveData<Boolean> getSwipeToRefresh() {
        return swipeToRefresh;
    }
}
