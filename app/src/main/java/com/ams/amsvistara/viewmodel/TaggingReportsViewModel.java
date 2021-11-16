package com.ams.amsvistara.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ams.amsvistara.model.PVReports;
import com.ams.amsvistara.model.TaggingReports;
import com.ams.amsvistara.utils.SharedPrefUtil;
import com.ams.amsvistara.ws.repo.IPVReportsRepo;
import com.ams.amsvistara.ws.repo.ITaggingReportsRepo;
import com.ams.amsvistara.ws.repo.PVReportsRepo;
import com.ams.amsvistara.ws.repo.TaggingReportsRepo;

import java.util.HashMap;
import java.util.Map;

import static com.ams.amsvistara.constants.ConstantStr.USER_TOKEN;

public class TaggingReportsViewModel  extends BaseViewModel {
    public TaggingReportsViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<TaggingReports> taggingReportsMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<String> last3daysdone = new MutableLiveData<>();
    public MutableLiveData<String> pending = new MutableLiveData<>();
    public MutableLiveData<String> done = new MutableLiveData<>();
    public MutableLiveData<String> total = new MutableLiveData<>();
    public MutableLiveData<String> countForToday = new MutableLiveData<>();

    public void fetchTaggingReportsData(Context context) {
        Map<String, String> req = new HashMap<String, String>();
        req.put("Token", SharedPrefUtil.getString(USER_TOKEN, "", context));

        TaggingReportsRepo.getInstance().getTaggingReports(req, new ITaggingReportsRepo.TaggingReportsCallback() {
            @Override
            public void onDataLoaded(TaggingReports TaggingReports) {
                taggingReportsMutableLiveData.setValue(TaggingReports);
                last3daysdone.setValue(""+TaggingReports.last3daysdone);
                pending.setValue(""+TaggingReports.pending);
                done.setValue(""+TaggingReports.done);
                total.setValue(""+TaggingReports.total);
                countForToday.setValue("Today's Completed Tagging : "+ TaggingReports.reportitems.size());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });


    }
}
