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
import com.ams.amsvistara.databinding.AdapterAuditListItemBinding;
import com.ams.amsvistara.databinding.AdapterTaListItemBinding;
import com.ams.amsvistara.interfaces.AuditClickListener;
import com.ams.amsvistara.interfaces.TAClickListener;
import com.ams.amsvistara.model.TRItems;
import com.ams.amsvistara.model.res.AuditExcessItem;
import com.ams.amsvistara.viewmodel.AuditItemViewModel;
import com.ams.amsvistara.viewmodel.ItemViewModel;
import com.ams.amsvistara.viewmodel.TAItemViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AuditListAdapter extends RecyclerView.Adapter<AuditListAdapter.ViewHolder> implements  Filterable {

    private List<AuditExcessItem> dataModelList;
    private List<AuditExcessItem> listFiltered;
    private Context context;
    private AuditClickListener listener;



    public AuditListAdapter(List<AuditExcessItem> dataModelList, Context ctx, AuditClickListener listener) {
        this.dataModelList = dataModelList;
        this.listFiltered = dataModelList;
        this.listener = listener;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        AdapterAuditListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.adapter_audit_list_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        AuditExcessItem item = listFiltered.get(i);
        AuditItemViewModel model = new AuditItemViewModel();
        model.setValue(item);
        viewHolder.binding.setViewModel(model);
        if(item.auditdstatus.equalsIgnoreCase("completed") ) {
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
                List<AuditExcessItem> filteredList = filterList(charString);
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
                listFiltered = (ArrayList<AuditExcessItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private List<AuditExcessItem> filterList(String keyword) {
        if (listFiltered != null && dataModelList != null) {
            List<AuditExcessItem> filteredList = new ArrayList<>();
            keyword = keyword.toLowerCase(Locale.getDefault());

            if (TextUtils.isEmpty(keyword)) {
                filteredList = dataModelList;
            } else {

                for (AuditExcessItem item : dataModelList) {
                    if (!TextUtils.isEmpty(item.assetname)) {
                        if (item.assetname.toLowerCase(Locale.getDefault()).contains(keyword) || item.assetnumber.toLowerCase(Locale.getDefault()).contains(keyword)) {
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
        AdapterAuditListItemBinding binding;

        ViewHolder(AdapterAuditListItemBinding binding) {
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


