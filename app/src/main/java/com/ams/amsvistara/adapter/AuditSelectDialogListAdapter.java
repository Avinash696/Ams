package com.ams.amsvistara.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ams.amsvistara.R;
import com.ams.amsvistara.databinding.AdapterListDialogAuditSelectItemBinding;
import com.ams.amsvistara.interfaces.AuditSelectDialogClickListener;
import com.ams.amsvistara.model.AuditIdModel;
import com.ams.amsvistara.model.res.AuditMasters;
import com.ams.amsvistara.model.updatedModels.NewAuditMaster;
import com.ams.amsvistara.viewmodel.ItemViewModel;

import java.util.ArrayList;
import java.util.Locale;

public class AuditSelectDialogListAdapter extends RecyclerView.Adapter<AuditSelectDialogListAdapter.ViewHolder> implements  Filterable {

//    private ArrayList<AuditMasters> dataModelList;
    private ArrayList<NewAuditMaster> dataModelList;
//    private ArrayList<AuditMasters> listFiltered;
    private ArrayList<NewAuditMaster> listFiltered;
    private Context context;
    private AuditSelectDialogClickListener listener;



//    public AuditSelectDialogListAdapter(ArrayList<AuditMasters> dataModelList, Context ctx, AuditSelectDialogClickListener listener) {
//        this.dataModelList = dataModelList;
//        this.listFiltered = dataModelList;
//        this.listener = listener;
//        context = ctx;
//    }

    public AuditSelectDialogListAdapter(ArrayList<NewAuditMaster> dataModelList, Context ctx, AuditSelectDialogClickListener listener) {
        this.dataModelList = dataModelList;
        this.listFiltered = dataModelList;
        this.listener = listener;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        AdapterListDialogAuditSelectItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.adapter_list_dialog_audit_select_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
//        AuditMasters item = listFiltered.get(i);
        NewAuditMaster item = listFiltered.get(i);
        ItemViewModel model = new ItemViewModel();
        //model.tagCode.setValue(item.PVCode+"\n"+item.PVEndDate);
        Log.e("####","###### model.tagCode ##### "+model.tagCode.getValue());
        model.auditIdModel.setValue(item);
        model.auditId.setValue(String.valueOf(item.getAuditID()));
        model.auditCode.setValue(String.valueOf(item.getAuditCode()));
        //model.itemModel.setValue(new CreateStockProductModel(item.productCode,item.productName,item.demand,item.stock));
        viewHolder.binding.setViewModel(model);

        viewHolder.binding.setItemClickListener(listener);
    }


    @Override
    public int getItemCount() {
        if (listFiltered == null)
            return 0;

        return listFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                ArrayList<NewAuditMaster> filteredList = filterList(charString);
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
                listFiltered = (ArrayList<NewAuditMaster>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private ArrayList<NewAuditMaster> filterList(String keyword) {
        if (listFiltered != null && dataModelList != null) {

            ArrayList<NewAuditMaster> filteredList = new ArrayList<>();
            try {
                keyword = keyword.toLowerCase(Locale.getDefault());

                if (TextUtils.isEmpty(keyword)) {
                    filteredList = dataModelList;
                } else {

                    for (NewAuditMaster item : dataModelList) {
//                        if (!TextUtils.isEmpty(item.auditenddate)) {
//                            if (item.auditenddate.toLowerCase(Locale.getDefault()).contains(keyword) || item.auditenddate.toLowerCase(Locale.getDefault()).contains(keyword)) {
//                                filteredList.add(item);
//                            }
//                        }


                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return filteredList;
        }
        return null;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        AdapterListDialogAuditSelectItemBinding binding;

        ViewHolder(AdapterListDialogAuditSelectItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
   /* public class ViewHolder extends RecyclerView.ViewHolder {
        public AdapterTaggingListItemBinding itemRowBinding;

        public ViewHolder(AdapterTaggingListItemBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.model, obj);
            itemRowBinding.executePendingBindings();
        }
    }*/


}


