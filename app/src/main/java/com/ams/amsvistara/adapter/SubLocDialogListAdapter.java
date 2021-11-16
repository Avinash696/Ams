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
import com.ams.amsvistara.databinding.AdapterListDialogSubLocItemBinding;
import com.ams.amsvistara.db.table.master.SubLocationMasters;
import com.ams.amsvistara.interfaces.SubLocDialogClickListener;
import com.ams.amsvistara.viewmodel.ItemViewModel;

import java.util.ArrayList;
import java.util.Locale;

public class SubLocDialogListAdapter extends RecyclerView.Adapter<SubLocDialogListAdapter.ViewHolder> implements  Filterable {

    private ArrayList<SubLocationMasters> dataModelList;
    private ArrayList<SubLocationMasters> listFiltered;
    private Context context;
    private SubLocDialogClickListener listener;



    public SubLocDialogListAdapter(ArrayList<SubLocationMasters> dataModelList, Context ctx, SubLocDialogClickListener listener) {
        this.dataModelList = dataModelList;
        this.listFiltered = dataModelList;
        this.listener = listener;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        AdapterListDialogSubLocItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.adapter_list_dialog_sub_loc_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        SubLocationMasters item = listFiltered.get(i);
        ItemViewModel model = new ItemViewModel();
        model.tagCode.setValue(item.SubLocationCode+"\n"+item.SubLocationName);
        Log.e("####","###### model.tagCode ##### "+model.tagCode.getValue());
        model.subLocItemModel.setValue(item);
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
                ArrayList<SubLocationMasters> filteredList = filterList(charString);
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
                listFiltered = (ArrayList<SubLocationMasters>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private ArrayList<SubLocationMasters> filterList(String keyword) {
        if (listFiltered != null && dataModelList != null) {

            ArrayList<SubLocationMasters> filteredList = new ArrayList<>();
            try {
                keyword = keyword.toLowerCase(Locale.getDefault());

                if (TextUtils.isEmpty(keyword)) {
                    filteredList = dataModelList;
                } else {

                    for (SubLocationMasters item : dataModelList) {
                        if (!TextUtils.isEmpty(item.SubLocationCode)) {
                            if (item.SubLocationCode.toLowerCase(Locale.getDefault()).contains(keyword) || item.SubLocationName.toLowerCase(Locale.getDefault()).contains(keyword)) {
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
        AdapterListDialogSubLocItemBinding binding;

        ViewHolder(AdapterListDialogSubLocItemBinding binding) {
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


