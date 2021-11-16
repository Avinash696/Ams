package com.ams.amsvistara.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ams.amsvistara.db.table.master.TagItems;


public class TaggingItemViewModel extends ViewModel {
    public MutableLiveData<String> tagCode = new MutableLiveData<>();
    public MutableLiveData<String> tagName = new MutableLiveData<>();
    public MutableLiveData<TagItems> tagModel = new MutableLiveData<>();
    public TaggingItemViewModel() {
    }

    public void setValue(TagItems taggingListModel) {
        tagModel.setValue(taggingListModel);
        tagCode.setValue(taggingListModel.AssetNumber);
        tagName.setValue(taggingListModel.AssetName);

    }
}
