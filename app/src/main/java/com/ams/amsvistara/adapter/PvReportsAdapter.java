package com.ams.amsvistara.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ams.amsvistara.R;
import com.ams.amsvistara.databinding.AdapterPvReportsItemBinding;
import com.ams.amsvistara.interfaces.PvReportsClickListener;
import com.ams.amsvistara.model.PvReportsItem;
import com.ams.amsvistara.model.ReportItems;
import com.ams.amsvistara.viewmodel.PvReportsItemViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PvReportsAdapter extends RecyclerView.Adapter<PvReportsAdapter.ViewHolder> implements  Filterable {

    private List<ReportItems> dataModelList;
    private List<ReportItems> listFiltered;
    private Context context;
    private PvReportsClickListener listener;



    public PvReportsAdapter(List<ReportItems> dataModelList, Context ctx, PvReportsClickListener listener) {
        this.dataModelList = dataModelList;
        this.listFiltered = dataModelList;
        this.listener = listener;
        context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        AdapterPvReportsItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.adapter_pv_reports_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ReportItems item = listFiltered.get(i);
        PvReportsItemViewModel model = new PvReportsItemViewModel();
        model.setValue(item);
        viewHolder.binding.setViewModel(model);
      /*  if(item.trdstatus.equalsIgnoreCase("completed") ) {
            viewHolder.binding.checkFlag.setVisibility(View.VISIBLE);
        }else {
            viewHolder.binding.checkFlag.setVisibility(View.GONE);
        }*/
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
                List<ReportItems> filteredList = filterList(charString);
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
                listFiltered = (ArrayList<ReportItems>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private List<ReportItems> filterList(String keyword) {
        if (listFiltered != null && dataModelList != null) {
            List<ReportItems> filteredList = new ArrayList<>();
            keyword = keyword.toLowerCase(Locale.getDefault());

            if (TextUtils.isEmpty(keyword)) {
                filteredList = dataModelList;
            } else {

                for (ReportItems item : dataModelList) {
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
        AdapterPvReportsItemBinding binding;

        ViewHolder(AdapterPvReportsItemBinding binding) {
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


