package com.ams.amsvistara.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ams.amsvistara.model.TicketListModel;

import java.util.List;

public class TicketViewModel extends ViewModel {
    private MutableLiveData<TicketListModel> ticketViewModelList =new  MutableLiveData<>();    //obj will get updated from application
    public MutableLiveData<String> ticketId =new  MutableLiveData<>();
    public MutableLiveData<String> asset =new  MutableLiveData<>();
    public MutableLiveData<String> assignTo =new  MutableLiveData<>();
    public MutableLiveData<String> title =new  MutableLiveData<>();
    public MutableLiveData<String> description =new  MutableLiveData<>();

//    private LiveData<TicketListModel>  ticketListModelLiveData ;

    public MutableLiveData<TicketListModel> SaveTicket(){
        if(ticketViewModelList == null){
            ticketViewModelList = new MutableLiveData<>();
        }
        return ticketViewModelList;
    }
    public void onClick(View v)
    {
        //hit api
     
        TicketListModel ticketListModel =new TicketListModel(
                ticketId.getValue(),asset.getValue(),assignTo.getValue(),title.getValue(),description.getValue());
        ticketViewModelList.setValue(ticketListModel);
    }
}
