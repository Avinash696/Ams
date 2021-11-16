package com.ams.amsvistara.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ams.amsvistara.R;
import com.ams.amsvistara.databinding.AdapterPvListItemBinding;
import com.ams.amsvistara.db.table.master.PvItems;
import com.ams.amsvistara.interfaces.CustomClickListener;
import com.ams.amsvistara.viewmodel.PVItemViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PVListAdapter extends RecyclerView.Adapter<PVListAdapter.ViewHolder> implements Filterable {

    private List<PvItems> dataModelList;
    private List<PvItems> listFiltered;
    private Context context;
    private CustomClickListener listener;


    public PVListAdapter(List<PvItems> dataModelList, Context ctx, CustomClickListener listener) {
        this.dataModelList = dataModelList;
        this.listFiltered = dataModelList;
        this.listener = listener;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        AdapterPvListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.adapter_pv_list_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        PvItems item = listFiltered.get(i);
        PVItemViewModel model = new PVItemViewModel();
        model.setValue(item);
        viewHolder.binding.setViewModel(model);
     /*   Log.e("####","####### item.PVDStatus ####### "+item.PVDStatus);
        Log.e("####","####### item.PVID ####### "+item.PVID);
        Log.e("####","####### item.PVDetailID ####### "+item.PVDetailID);*/
        if(item.PVDStatus.equalsIgnoreCase("completed") || item.SaveFlag) {
            viewHolder.binding.checkFlag.setVisibility(View.VISIBLE);
        }else {
            viewHolder.binding.checkFlag.setVisibility(View.GONE);
        }
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
                List<PvItems> filteredList = filterList(charString);
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
                listFiltered = (ArrayList<PvItems>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private List<PvItems> filterList(String keyword) {
        if (listFiltered != null && dataModelList != null) {
            List<PvItems> filteredList = new ArrayList<>();
            keyword = keyword.toLowerCase(Locale.getDefault());

            if (TextUtils.isEmpty(keyword)) {
                filteredList = dataModelList;
            } else {

                for (PvItems item : dataModelList) {
                    if (!TextUtils.isEmpty(item.AssetNumber)) {
                        if (item.AssetNumber.toLowerCase(Locale.getDefault()).contains(keyword) || item.AssetName.toLowerCase(Locale.getDefault()).contains(keyword)) {
                            filteredList.add(item);
                        }
                    }


                }
            }
            return filteredList;
        }
        return null;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        AdapterPvListItemBinding binding;

        ViewHolder(AdapterPvListItemBinding binding) {
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


