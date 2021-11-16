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
import com.ams.amsvistara.databinding.AdapterTaggingListItemBinding;
import com.ams.amsvistara.db.table.master.TagItems;
import com.ams.amsvistara.interfaces.TaggingClickListener;
import com.ams.amsvistara.viewmodel.TaggingItemViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TaggingListAdapter extends RecyclerView.Adapter<TaggingListAdapter.ViewHolder> implements  Filterable {

    private List<TagItems> dataModelList;
    private List<TagItems> listFiltered;
    private Context context;
    private TaggingClickListener listener;



    public TaggingListAdapter(List<TagItems> dataModelList, Context ctx, TaggingClickListener listener) {
        this.dataModelList = dataModelList;
        this.listFiltered = dataModelList;
        this.listener = listener;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        AdapterTaggingListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.adapter_tagging_list_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        TagItems item = listFiltered.get(i);
        TaggingItemViewModel model = new TaggingItemViewModel();
        model.setValue(item);
        viewHolder.binding.setViewModel(model);
        if(item.TagDStatus.equalsIgnoreCase("completed") || item.SaveFlag) {
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
                List<TagItems> filteredList = filterList(charString);
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
                listFiltered = (ArrayList<TagItems>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private List<TagItems> filterList(String keyword) {
        if (listFiltered != null && dataModelList != null) {
            List<TagItems> filteredList = new ArrayList<>();
            keyword = keyword.toLowerCase(Locale.getDefault());

            if (TextUtils.isEmpty(keyword)) {
                filteredList = dataModelList;
            } else {

                for (TagItems item : dataModelList) {
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
        AdapterTaggingListItemBinding binding;

        ViewHolder(AdapterTaggingListItemBinding binding) {
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


