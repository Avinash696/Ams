package com.ams.amsvistara.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ams.amsvistara.application.AMSApplication;
import com.ams.amsvistara.db.AppDatabase;
import com.ams.amsvistara.db.table.master.PvItems;
import com.ams.amsvistara.db.table.master.PvModels;
import com.ams.amsvistara.model.PVListModel;

import java.util.ArrayList;
import java.util.List;


public class PVListViewModel extends BaseViewModel {
    public MutableLiveData<List<PVListModel>> listMutableLiveDataTagList = new MutableLiveData<>();

    public PVListViewModel(@NonNull Application application) {
        super(application);
    }


    public ArrayList<PvItems> fetchPVData(int pvID) {
        ArrayList<PvItems> pvItemsArrayList = null;
        ArrayList<PvModels> pvModel = new ArrayList<>();
        pvModel.addAll(AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().getMasterData().PVModels);
        Log.e("####", "########## pvModel Size  ######### " + pvModel.size());
        for (int i = 0; i < pvModel.size(); i++) {

            if (pvModel.get(i).PhysicalVerificationID == pvID) {
                pvItemsArrayList = new ArrayList<>();
                Log.e("####", "########## PhysicalVerificationID  ######### " + pvModel.get(i).PhysicalVerificationID);
                Log.e("####", "########## PVCode  ######### " + pvModel.get(i).PVCode);
                Log.e("####", "########## PVEndDate  ######### " + pvModel.get(i).PVEndDate);
                Log.e("####", "########## PVStartDate  ######### " + pvModel.get(i).PVStartDate);
                Log.e("####", "########## PVItems Size ######### " + pvModel.get(i).PVItems.size());
                pvItemsArrayList.addAll(pvModel.get(i).PVItems);
            }
        }
    //    Log.e("####", "########## pvItemsArrayList ######### " +pvItemsArrayList.size());
        return pvItemsArrayList;
    }
}
