package com.ams.amsvistara.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ams.amsvistara.application.AMSApplication;
import com.ams.amsvistara.db.AppDatabase;
import com.ams.amsvistara.db.table.master.TagItems;
import com.ams.amsvistara.db.table.master.TagModels;
import com.ams.amsvistara.model.TaggingListModel;

import java.util.ArrayList;
import java.util.List;

public class TaggingListViewModel extends BaseViewModel {
    public MutableLiveData<List<TaggingListModel>> listMutableLiveDataTagList = new MutableLiveData<>();

    public TaggingListViewModel(@NonNull Application application) {
        super(application);
    }


    public ArrayList<TagItems> fetchTaggingData(int tagID) {
        ArrayList<TagItems> tagItemsArrayList = null;
        ArrayList<TagModels> tagModel = new ArrayList<>();
        tagModel.addAll(AppDatabase.getAppDatabase(AMSApplication.instance).masterDataDao().getMasterData().TAGModels);
        for (int i = 0; i < tagModel.size(); i++) {
            if (tagModel.get(i).TAGID == tagID) {
                tagItemsArrayList = new ArrayList<>();
                Log.e("####", "########## tagID ######### " + tagModel.get(i).TAGID);
                tagItemsArrayList.addAll(tagModel.get(i).TAGItems);
            }
        }
        return tagItemsArrayList;
    }
}
