package com.ams.amsvistara.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ams.amsvistara.db.table.master.LocationMasters;
import com.ams.amsvistara.db.table.master.PvModels;
import com.ams.amsvistara.db.table.master.SubLocationMasters;
import com.ams.amsvistara.db.table.master.TagModels;
import com.ams.amsvistara.model.AuditIdModel;
import com.ams.amsvistara.model.InboundTransferRequestMasters;
import com.ams.amsvistara.model.res.AuditMasters;
import com.ams.amsvistara.model.updatedModels.NewAuditMaster;


public class ItemViewModel extends ViewModel {
    public MutableLiveData<String> tagCode = new MutableLiveData<>();
    public MutableLiveData<PvModels> itemModel = new MutableLiveData<>();
    public MutableLiveData<TagModels> tagItemModel = new MutableLiveData<>();
    public MutableLiveData<LocationMasters> locItemModel = new MutableLiveData<>();
    public MutableLiveData<SubLocationMasters> subLocItemModel = new MutableLiveData<>();
    public MutableLiveData<InboundTransferRequestMasters> inboundTransferMutableLiveData = new MutableLiveData<>();
//    public MutableLiveData<AuditMasters> auditIdModel = new MutableLiveData<>();
    public MutableLiveData<NewAuditMaster> auditIdModel = new MutableLiveData<>();
    public MutableLiveData<String> auditId = new MutableLiveData<>();
    public MutableLiveData<String> auditCode = new MutableLiveData<>();

    public ItemViewModel() {
    }

}
