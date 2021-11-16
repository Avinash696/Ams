package com.ams.amsvistara.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.ams.amsvistara.R;
import com.ams.amsvistara.databinding.AdapterAllassetsListBinding;
import com.ams.amsvistara.interfaces.AllAssetsClickListener;
import com.ams.amsvistara.model.AssetMasterDemoModel;
import com.ams.amsvistara.model.updatedModels.AssetMaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class AllAssetsListAdapter extends RecyclerView.Adapter<AllAssetsListAdapter.ViewHolder> implements Filterable {
    public static final String TAG = "rawat";
    private List<AssetMaster> dataModelList;
    private List<AssetMaster> listFiltered;
    private Context context;
    private AllAssetsClickListener listener;
    boolean isSelected;
    ArrayList<AssetMaster> selectedItems =new ArrayList<>();


    //        public AllAssetsListAdapter(List<AssetMasterDemoModel> dataModelList, Context ctx) {
//            this.dataModelList = dataModelList;
//            this.context = ctx;
//        }
    public AllAssetsListAdapter(List<AssetMaster> dataModelList) {
//            Log.d("rawat", "AllAssetsListAdapter: "+dataModelList);
        this.dataModelList = dataModelList;
    }

    /*

        public AllAssetsListAdapter(List<AllAssetsListModel> dataModelList, Context ctx, AllAssetsClickListener listener) {
            this.dataModelList = dataModelList;
            this.listFiltered = dataModelList;
            this.listener = listener;
            context = ctx;
        }
    */

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
//        Log.d(TAG, "onCreateViewHolder: ");
        AdapterAllassetsListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.adapter_allassets_list, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        //        AllAssetsListModel item = listFiltered.get(i);
        //        AdapterAllassetsListBinding model = new TaggingItemViewModel();
        //        model.setValue(item);
        //        viewHolder.binding.setViewModel(model);

        AssetMaster datalist = dataModelList.get(i);
//        Log.d("rawat", "onBindViewHolder: ." + datalist.AssetName);
        viewHolder.binding.tvAndroidCode.setText(datalist.AssetCode);
        viewHolder.binding.tvAndroidName.setText(datalist.AssetName);



        //        if(datalist.selectFlag) {
        //            viewHolder.binding.checkFlag.setVisibility(View.VISIBLE);
        //        }else {
        //            viewHolder.binding.checkFlag.setVisibility(View.GONE);
        //        }

          /*  viewHolder.binding.tvAndroidName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), AllAssetsDetail.class);
                    intent.putExtra(ASSET_CODE,""+datalist.tagCode);
                    intent.putExtra(ASSET_NAME,""+datalist.tagName);
                    context.startActivity(intent);
                }
            });*/
    }


    @Override
    public int getItemCount() {
//            if (listFiltered == null)
//                return 0;

        return dataModelList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                //                List<AssetMaster> filteredList = filterList(charString);
                FilterResults filterResults = new FilterResults();
                //                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notifyDataSetChanged();
                listFiltered = (ArrayList<AssetMaster>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

//        private List<AssetMaster> filterList(String keyword) {
//            if (listFiltered != null && dataModelList != null) {
//                List<AssetMaster> filteredList = new ArrayList<>();
//                keyword = keyword.toLowerCase(Locale.getDefault());
//
//                if (TextUtils.isEmpty(keyword)) {
//                    filteredList = dataModelList;
//                } else {
//
//                    for (AssetMaster item : dataModelList) {
//                        if (!TextUtils.isEmpty(item.AssetName)) {
//                            if (item.AssetCode.toLowerCase(Locale.getDefault()).contains(keyword) || item.AssetName.toLowerCase(Locale.getDefault()).contains(keyword)) {
//                                filteredList.add(item);
//                            }
//                        }
//                    }
//                }
//                return filteredList;
//            }
//            return null;
//        }


    class ViewHolder extends RecyclerView.ViewHolder {
        AdapterAllassetsListBinding binding;

        ViewHolder(AdapterAllassetsListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}