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
import com.ams.amsvistara.databinding.AdapterListDialogTagItemBinding;
import com.ams.amsvistara.db.table.master.TagModels;
import com.ams.amsvistara.interfaces.TagDialogClickListener;
import com.ams.amsvistara.viewmodel.ItemViewModel;

import java.util.ArrayList;
import java.util.Locale;

public class TagDialogListAdapter extends RecyclerView.Adapter<TagDialogListAdapter.ViewHolder> implements  Filterable {

    private ArrayList<TagModels> dataModelList;
    private ArrayList<TagModels> listFiltered;
    private Context context;
    private TagDialogClickListener listener;



    public TagDialogListAdapter(ArrayList<TagModels> dataModelList, Context ctx, TagDialogClickListener listener) {
        this.dataModelList = dataModelList;
        this.listFiltered = dataModelList;
        this.listener = listener;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        AdapterListDialogTagItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.adapter_list_dialog_tag_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        TagModels item = listFiltered.get(i);
        ItemViewModel model = new ItemViewModel();
        model.tagCode.setValue(item.TagCode+"\n"+item.TagEndDate);
        Log.e("####","###### model.tagCode ##### "+model.tagCode.getValue());
        model.tagItemModel.setValue(item);
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
                ArrayList<TagModels> filteredList = filterList(charString);
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
                listFiltered = (ArrayList<TagModels>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private ArrayList<TagModels> filterList(String keyword) {
        if (listFiltered != null && dataModelList != null) {

            ArrayList<TagModels> filteredList = new ArrayList<>();
            try {
                keyword = keyword.toLowerCase(Locale.getDefault());

                if (TextUtils.isEmpty(keyword)) {
                    filteredList = dataModelList;
                } else {

                    for (TagModels item : dataModelList) {
                        if (!TextUtils.isEmpty(item.TagCode)) {
                            if (item.TagCode.toLowerCase(Locale.getDefault()).contains(keyword) || item.TagEndDate.toLowerCase(Locale.getDefault()).contains(keyword)) {
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
        AdapterListDialogTagItemBinding binding;

        ViewHolder(AdapterListDialogTagItemBinding binding) {
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


