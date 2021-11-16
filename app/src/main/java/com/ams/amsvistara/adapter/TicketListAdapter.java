package com.ams.amsvistara.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ams.amsvistara.R;
import com.ams.amsvistara.databinding.AdapterTicketListBinding;
import com.ams.amsvistara.interfaces.TicketClickListener;
import com.ams.amsvistara.model.TicketListModel;

import java.util.List;

public class TicketListAdapter extends RecyclerView.Adapter<TicketListAdapter.ViewHolder> {

    Context context;
    List<TicketListModel> dataModelList;
    TicketClickListener listener;

    public TicketListAdapter(List<TicketListModel> dataModelList, Context context, TicketClickListener listener) {
        this.dataModelList = dataModelList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterTicketListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.adapter_ticket_list, parent, false
        );
        return new ViewHolder(binding);
//        View v =LayoutInflater.from(context).inflate(R.layout.adapter_ticket_list, parent,false);
//        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TicketListModel datalist =  dataModelList.get(position);
        Log.d("rawat", "onBindViewHolder: "+datalist);
        holder.binding.rvTicketListAssets.setText(datalist.getAsset());
        holder.binding.rvTicketListAssignTo.setText(datalist.getAssignTo());
        holder.binding.rvTicketListDescription.setText(datalist.getDescription());
        holder.binding.rvTicketListStatus.setText(datalist.getStatus());
        holder.binding.rvTicketListImage.setText(datalist.getImage());
        holder.binding.rvTicketListTitle.setText(datalist.getTitle());
//        holder.assets.setText(datalist.getAsset());
//        holder.assignTo.setText(datalist.getAssignTo());
//        holder.status.setText(datalist.getStatus());
//        holder.desc.setText(datalist.getDescription());
////        holder.image.setText(datalist.getImage());
//        holder.title.setText(datalist.getTitle());
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        AdapterTicketListBinding binding;
        public ViewHolder(AdapterTicketListBinding binding) {
            super(binding.getRoot());
            this.binding=binding;                 // can point to null exception
        }
    }
    class ViewHolder2 extends RecyclerView.ViewHolder{
        TextView assets,assignTo,desc,status,image,title;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            assets =itemView.findViewById(R.id.rv_ticket_list_assets);
            assignTo =itemView.findViewById(R.id.rv_ticket_list_assignTo);
            desc =itemView.findViewById(R.id.rv_ticket_list_description);
            status =itemView.findViewById(R.id.rv_ticket_list_status);
            image =itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.rv_ticket_list_title);
        }
    }
}

