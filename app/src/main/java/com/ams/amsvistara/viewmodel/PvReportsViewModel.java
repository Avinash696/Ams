package com.ams.amsvistara.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ams.amsvistara.db.table.master.PvItems;
import com.ams.amsvistara.model.PVReports;
import com.ams.amsvistara.utils.SharedPrefUtil;
import com.ams.amsvistara.ws.repo.IPVReportsRepo;
import com.ams.amsvistara.ws.repo.PVReportsRepo;
import com.ams.amsvistara.ws.retrofit.RestService;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ams.amsvistara.constants.ConstantStr.USER_TOKEN;

public class PvReportsViewModel extends BaseViewModel {
    public PvReportsViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<PVReports> pvReportsMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<String> last3daysdone = new MutableLiveData<>();
    public MutableLiveData<String> pending = new MutableLiveData<>();
    public MutableLiveData<String> done = new MutableLiveData<>();
    public MutableLiveData<String> total = new MutableLiveData<>();
    public MutableLiveData<String> countForToday = new MutableLiveData<>();

    public void fetchPVReportsData(Context context) {
        Map<String, String> req = new HashMap<String, String>();
        req.put("Token", SharedPrefUtil.getString(USER_TOKEN, "", context));

        PVReportsRepo.getInstance().getPVReports(req, new IPVReportsRepo.PVReportsCallback() {
            @Override
            public void onDataLoaded(PVReports PVReports) {
                pvReportsMutableLiveData.setValue(PVReports);
                last3daysdone.setValue(""+PVReports.last3daysdone);
                pending.setValue(""+PVReports.pending);
                done.setValue(""+PVReports.done);
                total.setValue(""+PVReports.total);
                countForToday.setValue("Today's Completed PV : "+PVReports.reportitems.size());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });


    }
}
