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
import com.ams.amsvistara.databinding.AdapterListDialogTaItemBinding;
import com.ams.amsvistara.interfaces.TADialogClickListener;
import com.ams.amsvistara.model.InboundTransferRequestMasters;
import com.ams.amsvistara.viewmodel.ItemViewModel;

import java.util.ArrayList;
import java.util.Locale;

public class TADialogListAdapter extends RecyclerView.Adapter<TADialogListAdapter.ViewHolder> implements  Filterable {

    private ArrayList<InboundTransferRequestMasters> dataModelList;
    private ArrayList<InboundTransferRequestMasters> listFiltered;
    private Context context;
    private TADialogClickListener listener;



    public TADialogListAdapter(ArrayList<InboundTransferRequestMasters> dataModelList, Context ctx, TADialogClickListener listener) {
        this.dataModelList = dataModelList;
        this.listFiltered = dataModelList;
        this.listener = listener;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        AdapterListDialogTaItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.adapter_list_dialog_ta_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        InboundTransferRequestMasters item = listFiltered.get(i);
        ItemViewModel model = new ItemViewModel();
        model.tagCode.setValue(item.trcode+"\n"+item.transferdate);
        Log.e("####","###### model.tagCode ##### "+model.tagCode.getValue());
        model.inboundTransferMutableLiveData.setValue(item);
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
                ArrayList<InboundTransferRequestMasters> filteredList = filterList(charString);
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
                listFiltered = (ArrayList<InboundTransferRequestMasters>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private ArrayList<InboundTransferRequestMasters> filterList(String keyword) {
        if (listFiltered != null && dataModelList != null) {

            ArrayList<InboundTransferRequestMasters> filteredList = new ArrayList<>();
            try {
                keyword = keyword.toLowerCase(Locale.getDefault());

                if (TextUtils.isEmpty(keyword)) {
                    filteredList = dataModelList;
                } else {

                    for (InboundTransferRequestMasters item : dataModelList) {
                        if (!TextUtils.isEmpty(item.trcode)) {
                            if (item.trcode.toLowerCase(Locale.getDefault()).contains(keyword) || item.transferdate.toLowerCase(Locale.getDefault()).contains(keyword)) {
                                filteredList.add(item);
                            }
                        }


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
        AdapterListDialogTaItemBinding binding;

        ViewHolder(AdapterListDialogTaItemBinding binding) {
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


