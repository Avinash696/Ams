package com.ams.amsvistara.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.ams.amsvistara.R;
import com.ams.amsvistara.db.AppDatabase;
import com.ams.amsvistara.db.table.master.PvModels;
import com.ams.amsvistara.db.table.master.TagModels;
import com.ams.amsvistara.model.AuditIdModel;
import com.ams.amsvistara.model.res.AppMenus;
import com.ams.amsvistara.ui.activity.AllAssetsList;
import com.ams.amsvistara.ui.activity.PvReportsActivity;
import com.ams.amsvistara.ui.activity.TaggingReportsActivity;
import com.ams.amsvistara.utils.AuditSelectDialog;
import com.ams.amsvistara.utils.PVListPvDialog;
import com.ams.amsvistara.utils.TAListDialog;
import com.ams.amsvistara.utils.TagListPvDialog;

import java.util.ArrayList;

import static com.ams.amsvistara.constants.ConstantStr.AUDIT_TYPE_EXCESS;
import static com.ams.amsvistara.constants.ConstantStr.AUDIT_TYPE_KNOWN;
import static com.ams.amsvistara.constants.ConstantStr.PV_TYPE_EXCESS;
import static com.ams.amsvistara.constants.ConstantStr.PV_TYPE_KNOWN;

public class MainGridView extends BaseAdapter {

    private Context mContext;
    ArrayList<AppMenus> gridViewModelList;
    boolean permissionFlag;


    public MainGridView(Context context, ArrayList<AppMenus> gridViewModelList, Boolean permissionFlag) {
        mContext = context;
        this.gridViewModelList = gridViewModelList;
        this.permissionFlag = permissionFlag;
    }

    @Override
    public int getCount() {
        return gridViewModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return gridViewModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridViewAndroid = null;
        try {

        /*LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        gridViewAndroid = new View(mContext);
        gridViewAndroid = inflater.inflate(R.layout.grid_item, null);*/
            Log.e("####", "$$$$$$$$$$$$ dashboardReqModel.appmenus $$$$$$$$$$$$ " + gridViewModelList.get(position).menutitle);
            Log.e("####", "$$$$$$$$$$$$ dashboardReqModel.position $$$$$$$$$$$$ " + position);
            //  if (convertView == null) {  // if it's not recycled, initialize some attributes
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridViewAndroid = inflater.inflate(R.layout.grid_item, parent, false);
      /*  } else {
            gridViewAndroid = (View) convertView;
        }*/
            ImageView imageView = (ImageView) gridViewAndroid.findViewById(R.id.grid_item_iv);
            CardView cardView = (CardView) gridViewAndroid.findViewById(R.id.cardViewTag);
            TextView itemName = (TextView) gridViewAndroid.findViewById(R.id.itemName);
            // if (convertView == null) {


           /* TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.android_gridview_text);
            ImageView imageViewAndroid = (ImageView) gridViewAndroid.findViewById(R.id.android_gridview_image);
            textViewAndroid.setText(gridViewString[i]);
            imageViewAndroid.setImageResource(gridViewImageId[i]);*/


            itemName.setText("" + gridViewModelList.get(position).menutitle);
            TextView itemStatus = (TextView) gridViewAndroid.findViewById(R.id.itemStatus);
            itemStatus.setText("" + gridViewModelList.get(position).counters);
            cardView.setTag("" + gridViewModelList.get(position).menuid);

            Log.e("####", "###### ID #### " + gridViewModelList.get(position).menuid);
            Log.e("####", "###### Name #### " + gridViewModelList.get(position).menutitle);

            if (String.valueOf(gridViewModelList.get(position).menuid).equalsIgnoreCase("1")) {
                imageView.setImageResource(R.drawable.tagging);
            }
            if (String.valueOf(gridViewModelList.get(position).menuid).equalsIgnoreCase("2")) {
                imageView.setImageResource(R.drawable.pv);
            }
            if (String.valueOf(gridViewModelList.get(position).menuid).equalsIgnoreCase("3")) {
                imageView.setImageResource(R.drawable.tagging_report);
            }
            if (String.valueOf(gridViewModelList.get(position).menuid).equalsIgnoreCase("4")) {
                imageView.setImageResource(R.drawable.pv_reprot);
            }
            if (String.valueOf(gridViewModelList.get(position).menuid).equalsIgnoreCase("27")) {
                imageView.setImageResource(R.drawable.excess_assets);
            }

            if (String.valueOf(gridViewModelList.get(position).menuid).equalsIgnoreCase("29")) {
                imageView.setImageResource(R.drawable.asset_transfer);
            }

            if (String.valueOf(gridViewModelList.get(position).menuid).equalsIgnoreCase("34")) {
                imageView.setImageResource(R.drawable.disposal);
            }

            if (String.valueOf(gridViewModelList.get(position).menuid).equalsIgnoreCase("35")) {
                imageView.setImageResource(R.drawable.audit);
            }
            if (String.valueOf(gridViewModelList.get(position).menuid).equalsIgnoreCase("36")) {
                imageView.setImageResource(R.drawable.excess_assets);
            }

            if (String.valueOf(gridViewModelList.get(position).menuid).equalsIgnoreCase("9009")) {
                imageView.setImageResource(R.drawable.excess_assets);
            }

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view.getTag().toString().equalsIgnoreCase("2")) {
                        if (permissionFlag) {
                            if (turnOnLocationService()) {
                               /* Intent intent = new Intent(mContext, PVListActivity.class);
                                mContext.startActivity(intent);*/
                                try {
                                    PVListPvDialog PVListDialog = new PVListPvDialog(mContext, (ArrayList<PvModels>) AppDatabase.getAppDatabase(mContext).masterDataDao().getMasterData().PVModels, PV_TYPE_KNOWN);
                                    PVListDialog.show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                          /*      Log.e("####","########### getMasterData PV Model ########### "+ AppDatabase.getAppDatabase(MainActivity.this).masterDataDao().getMasterData().PVModels.size());
                                Log.e("####","########### getMasterData Tag Model ########### "+AppDatabase.getAppDatabase(MainActivity.this).masterDataDao().getMasterData().TAGModels.size());
                                Log.e("####","########### getMasterData PVItems ########### "+AppDatabase.getAppDatabase(MainActivity.this).masterDataDao().getMasterData().PVModels.get(0).PVItems.size());
                                Log.e("####","########### getMasterData TAGItems ########### "+AppDatabase.getAppDatabase(MainActivity.this).masterDataDao().getMasterData().TAGModels.get(0).TAGItems.size());
*/
                            }
                        }
                    }
                    if (view.getTag().toString().equalsIgnoreCase("1")) {
                        if (permissionFlag) {
                            if (turnOnLocationService()) {
                               /* Intent intent = new Intent(mContext, TaggingListActivity.class);
                                mContext.startActivity(intent);*/
                                try {
                                    Log.e("####", "########## TAGModels ######## " + AppDatabase.getAppDatabase(mContext).masterDataDao().getMasterData().TAGModels.size());
                                    TagListPvDialog TagListPvDialog = new TagListPvDialog(mContext, (ArrayList<TagModels>) AppDatabase.getAppDatabase(mContext).masterDataDao().getMasterData().TAGModels);
                                    TagListPvDialog.show();
                                } catch (Exception e) {

                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    if (view.getTag().toString().equalsIgnoreCase("3")) {
                        if (permissionFlag) {
                            if (turnOnLocationService()) {
                            }
                        }
                    }
                    if (view.getTag().toString().equalsIgnoreCase("27")) {
                        if (permissionFlag) {
                            if (turnOnLocationService()) {

                                try {
                                    PVListPvDialog PVListDialog = new PVListPvDialog(mContext, (ArrayList<PvModels>) AppDatabase.getAppDatabase(mContext).masterDataDao().getMasterData().PVModels, PV_TYPE_EXCESS);
                                    PVListDialog.show();
                                } catch (Exception e) {

                                    e.printStackTrace();
                                }

                            }
                        }
                    }
                    if (view.getTag().toString().equalsIgnoreCase("29")) {
                        if (permissionFlag) {
                            if (turnOnLocationService()) {

                                try {
                                    TAListDialog TAListDialog = new TAListDialog(mContext);
                                    TAListDialog.show();
                                } catch (Exception e) {

                                    e.printStackTrace();
                                }

                            }
                        }
                    }

                    if (view.getTag().toString().equalsIgnoreCase("3")) {
                        if (permissionFlag) {
                            Intent intent = new Intent(mContext, TaggingReportsActivity.class);
                            mContext.startActivity(intent);
                        }
                    }
                    if (view.getTag().toString().equalsIgnoreCase("4")) {
                        if (permissionFlag) {
                            Intent intent = new Intent(mContext, PvReportsActivity.class);
                            mContext.startActivity(intent);
                        }
                    }
                    if (view.getTag().toString().equalsIgnoreCase("35")) {
                        if (permissionFlag) {
                            try {
                                AuditSelectDialog AuditSelectDialog = new AuditSelectDialog(mContext, AUDIT_TYPE_KNOWN);
                                AuditSelectDialog.show();
                            } catch (Exception e) {

                                e.printStackTrace();
                            }
                        }
                    }


                    if (view.getTag().toString().equalsIgnoreCase("36")) {
                        if (permissionFlag) {
                            try {
                                AuditSelectDialog AuditSelectDialog = new AuditSelectDialog(mContext, AUDIT_TYPE_EXCESS);
                                AuditSelectDialog.show();
                            } catch (Exception e) {

                                e.printStackTrace();
                            }
                        }
                    }

                    if (view.getTag().toString().equalsIgnoreCase("9009")) {
                        if (permissionFlag) {
                            if (turnOnLocationService()) {

                                try {
                                    Intent intent = new Intent(mContext, AllAssetsList.class);
                                    mContext.startActivity(intent);
                                } catch (Exception e) {

                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
       /* } else {
            gridViewAndroid = (View) convertView;
        }*/

        return gridViewAndroid;
    }


    private boolean turnOnLocationService() {
        LocationManager lm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (!gps_enabled && !network_enabled) {
            // notify user
            new AlertDialog.Builder(mContext)
                    .setMessage("GPS Network Not Enabled. Please Turn On the Location.")
                    .setPositiveButton("Open Location Setting", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            mContext.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
        return gps_enabled;
    }
}