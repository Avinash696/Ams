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
import com.ams.amsvistara.databinding.AdapterTaListItemBinding;
import com.ams.amsvistara.interfaces.TAClickListener;
import com.ams.amsvistara.model.TRItems;
import com.ams.amsvistara.viewmodel.TAItemViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TAListAdapter extends RecyclerView.Adapter<TAListAdapter.ViewHolder> implements  Filterable {

    private List<TRItems> dataModelList;
    private List<TRItems> listFiltered;
    private Context context;
    private TAClickListener listener;



    public TAListAdapter(List<TRItems> dataModelList, Context ctx, TAClickListener listener) {
        this.dataModelList = dataModelList;
        this.listFiltered = dataModelList;
        this.listener = listener;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        AdapterTaListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.adapter_ta_list_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        TRItems item = listFiltered.get(i);
        TAItemViewModel model = new TAItemViewModel();
        model.setValue(item);
        viewHolder.binding.setViewModel(model);
        if(item.trdstatus.equalsIgnoreCase("completed") ) {
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
                List<TRItems> filteredList = filterList(charString);
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
                listFiltered = (ArrayList<TRItems>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private List<TRItems> filterList(String keyword) {
        if (listFiltered != null && dataModelList != null) {
            List<TRItems> filteredList = new ArrayList<>();
            keyword = keyword.toLowerCase(Locale.getDefault());

            if (TextUtils.isEmpty(keyword)) {
                filteredList = dataModelList;
            } else {

                for (TRItems item : dataModelList) {
                    if (!TextUtils.isEmpty(item.assetnumber)) {
                        if (item.assetnumber.toLowerCase(Locale.getDefault()).contains(keyword) || item.assetnumber.toLowerCase(Locale.getDefault()).contains(keyword)) {
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
        AdapterTaListItemBinding binding;

        ViewHolder(AdapterTaListItemBinding binding) {
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


