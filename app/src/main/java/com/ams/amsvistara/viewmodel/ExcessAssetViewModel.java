package com.ams.amsvistara.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ams.amsvistara.application.AMSApplication;
import com.ams.amsvistara.db.AppDatabase;
import com.ams.amsvistara.db.table.excess.PvExcessReq;
import com.ams.amsvistara.db.table.master.PvItems;
import com.ams.amsvistara.model.res.ExcessRes;
import com.ams.amsvistara.utils.SharedPrefUtil;
import com.ams.amsvistara.ws.repo.ExcessPvRepo;
import com.ams.amsvistara.ws.repo.IExcessPvRepo;

import java.util.HashMap;
import java.util.Map;

import static com.ams.amsvistara.constants.ConstantStr.USER_TOKEN;

public class ExcessAssetViewModel extends BaseViewModel {


    public MutableLiveData<String> imageOne = new MutableLiveData<>();
    public MutableLiveData<String> imageTwo = new MutableLiveData<>();
    public MutableLiveData<String> imageThree = new MutableLiveData<>();
    public MutableLiveData<String> imageFour = new MutableLiveData<>();
    public MutableLiveData<String> imageFive = new MutableLiveData<>();
    public MutableLiveData<String> imageOneP = new MutableLiveData<>();
    public MutableLiveData<String> imageTwoP = new MutableLiveData<>();
    public MutableLiveData<String> imageThreeP = new MutableLiveData<>();
    public MutableLiveData<String> imageFourP = new MutableLiveData<>();
    public MutableLiveData<String> imageFiveP = new MutableLiveData<>();

    public MutableLiveData<String> AssetNumber = new MutableLiveData<>();
    public MutableLiveData<String> AssetName = new MutableLiveData<>();
    public MutableLiveData<String> ImageCount = new MutableLiveData<>();
    public MutableLiveData<String> AssetType = new MutableLiveData<>();
    public MutableLiveData<Integer> AssetTypeCode = new MutableLiveData<>();
    public MutableLiveData<String> AssetSurface = new MutableLiveData<>();
    public MutableLiveData<Integer> AssetSurfaceCode = new MutableLiveData<>();
    /*public MutableLiveData<String> HeatedAsset = new MutableLiveData<>();*/
    public MutableLiveData<String> AssetCondition = new MutableLiveData<>();
    public MutableLiveData<Integer> AssetConditionCode = new MutableLiveData<>();
    public MutableLiveData<String> AssetNameText = new MutableLiveData<>();
    public MutableLiveData<String> MakeTxt = new MutableLiveData<>();
    public MutableLiveData<String> ModelTxt = new MutableLiveData<>();
    public MutableLiveData<String> SerialNo = new MutableLiveData<>();
    public MutableLiveData<String> CapDate = new MutableLiveData<>();
    public MutableLiveData<String> Vendor = new MutableLiveData<>();
    public MutableLiveData<String> Location = new MutableLiveData<>();
    public MutableLiveData<String> SubLocation = new MutableLiveData<>();
    public MutableLiveData<Integer> LocationID = new MutableLiveData<>();
    public MutableLiveData<Integer> SubLocationID = new MutableLiveData<>();
    public MutableLiveData<String> SPOCName = new MutableLiveData<>();
    public MutableLiveData<String> PVName = new MutableLiveData<>();
    public MutableLiveData<String> PVStatus = new MutableLiveData<>();
    public MutableLiveData<String> PVRemarks = new MutableLiveData<>();
    public MutableLiveData<String> ClientRemarks = new MutableLiveData<>();
    public MutableLiveData<Boolean> saveFlag = new MutableLiveData<>();
    public MutableLiveData<String> isRadioSelection = new MutableLiveData<>();
    public MutableLiveData<PvItems> saveTaggingTable = new MutableLiveData<>();
    public MutableLiveData<Integer> pVDetailID = new MutableLiveData<>();
    public MutableLiveData<Integer> pVID = new MutableLiveData<>();
    public MutableLiveData<Boolean> showProgress = new MutableLiveData<>();

    public MutableLiveData<Boolean> callBackPress = new MutableLiveData<>();

    public ExcessAssetViewModel(@NonNull Application application) {
        super(application);
        ImageCount.setValue("0");
        imageOneP.setValue("");
        imageTwoP.setValue("");
        imageThreeP.setValue("");
        imageFourP.setValue("");
        imageFiveP.setValue("");
    }

    public void onClick(View view) {
        isRadioSelection.setValue("check");
        if (ImageCount.getValue().equalsIgnoreCase("0")) {
            Toast.makeText(getApplication(), "Add Some Asset Image. Tap On Camera Icon.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(AssetType.getValue())) {
            Toast.makeText(getApplication(), "Select Asset Type.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(AssetSurface.getValue())) {
            Toast.makeText(getApplication(), "Select Asset Surface.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(AssetCondition.getValue())) {
            Toast.makeText(getApplication(), "Select Asset Condition.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(AssetNameText.getValue())) {
            Toast.makeText(getApplication(), "Enter Asset Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(AssetNumber.getValue())) {
            Toast.makeText(getApplication(), "Enter Asset Number.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(MakeTxt.getValue())) {
            Toast.makeText(getApplication(), "Enter Make", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ModelTxt.getValue())) {
            Toast.makeText(getApplication(), "Enter Model", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Vendor.getValue())) {
            Toast.makeText(getApplication(), "Enter Vendor Name.", Toast.LENGTH_SHORT).show();
        } /*else if (TextUtils.isEmpty(CapDate.getValue())) {
            Toast.makeText(getApplication(), "Select Cap Date.", Toast.LENGTH_SHORT).show();
        }*/ else if (TextUtils.isEmpty(SerialNo.getValue())) {
            Toast.makeText(getApplication(), "Enter Serial No.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Location.getValue())) {
            Toast.makeText(getApplication(), "Enter Location", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(SubLocation.getValue())) {
            Toast.makeText(getApplication(), "Enter Sub Location", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(SPOCName.getValue())) {
            Toast.makeText(getApplication(), "Enter SPOC Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(PVName.getValue())) {
            Toast.makeText(getApplication(), "Enter PV Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(PVStatus.getValue())) {
            Toast.makeText(getApplication(), "Enter PV Status", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(PVRemarks.getValue())) {
            Toast.makeText(getApplication(), "Enter PV Remarks", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ClientRemarks.getValue())) {
            Toast.makeText(getApplication(), "Enter Client Remarks", Toast.LENGTH_SHORT).show();
        } else {
            showProgress.setValue(true);
            PvExcessReq pvExcessReq = new PvExcessReq();
            try {
                pvExcessReq.pvexcessid = 0;
                pvExcessReq.pvid = pVID.getValue();
                pvExcessReq.assetid = 0;
                pvExcessReq.locationid = LocationID.getValue();
                pvExcessReq.locationname = Location.getValue();
                pvExcessReq.sublocationid = SubLocationID.getValue();
                pvExcessReq.sublocationname = SubLocation.getValue();
                pvExcessReq.assetnumber = AssetNumber.getValue();
                pvExcessReq.assetname = AssetNameText.getValue();
                pvExcessReq.serialnumber = SerialNo.getValue();
                pvExcessReq.make = MakeTxt.getValue();
                pvExcessReq.model = ModelTxt.getValue();
                pvExcessReq.assettypeid = AssetTypeCode.getValue();
                pvExcessReq.assettypename = AssetType.getValue();
                pvExcessReq.assetsurfaceid = AssetSurfaceCode.getValue();
                pvExcessReq.assetsurfacename = AssetSurface.getValue();
                pvExcessReq.assetconditionid = AssetConditionCode.getValue();
                pvExcessReq.assetconditionname = AssetCondition.getValue();
                pvExcessReq.spocname = SPOCName.getValue();
                pvExcessReq.pvname = PVName.getValue();
                pvExcessReq.clientremarks = ClientRemarks.getValue();
                pvExcessReq.vendor = Vendor.getValue();
                pvExcessReq.capitalizationdate = CapDate.getValue();
                pvExcessReq.saveflag = true;
                pvExcessReq.imagecount = Integer.parseInt(ImageCount.getValue());
                pvExcessReq.image1 = imageOneP.getValue();
                pvExcessReq.image2 = imageTwoP.getValue();
                pvExcessReq.image3 = imageThreeP.getValue();
                pvExcessReq.image4 = imageFourP.getValue();
                pvExcessReq.image5 = imageFiveP.getValue();
                pvExcessReq.pvestatus = PVStatus.getValue();
                pvExcessReq.pveremarks = PVRemarks.getValue();

                Log.e("#### ","###### pvExcessReq pvexcessid#### "+pvExcessReq.pvexcessid);
                Log.e("#### ","###### pvExcessReq pvid#### "+pvExcessReq.pvid);
                Log.e("#### ","###### pvExcessReq assetid#### "+pvExcessReq.assetid);
                Log.e("#### ","###### pvExcessReq locationid#### "+pvExcessReq.locationid);
                Log.e("#### ","###### pvExcessReq locationname#### "+pvExcessReq.locationname);
                Log.e("#### ","###### pvExcessReq sublocationid#### "+pvExcessReq.sublocationid);
                Log.e("#### ","###### pvExcessReq sublocationid#### "+pvExcessReq.sublocationname);
                Log.e("#### ","###### pvExcessReq assetnumber#### "+pvExcessReq.assetnumber);
                Log.e("#### ","###### pvExcessReq assetname#### "+pvExcessReq.assetname);
                Log.e("#### ","###### pvExcessReq serialnumber#### "+pvExcessReq.serialnumber);
                Log.e("#### ","###### pvExcessReq make#### "+pvExcessReq.make);
                Log.e("#### ","###### pvExcessReq model#### "+pvExcessReq.model);
                Log.e("#### ","###### pvExcessReq assettypeid#### "+pvExcessReq.assettypeid);
                Log.e("#### ","###### pvExcessReq assettypename#### "+pvExcessReq.assettypename);
                Log.e("#### ","###### pvExcessReq assetsurfaceid#### "+pvExcessReq.assetsurfaceid);
                Log.e("#### ","###### pvExcessReq assetsurfacename#### "+pvExcessReq.assetsurfacename);
                Log.e("#### ","###### pvExcessReq assetconditionid#### "+pvExcessReq.assetconditionid);
                Log.e("#### ","###### pvExcessReq assetconditionname#### "+pvExcessReq.assetconditionname);
                Log.e("#### ","###### pvExcessReq spocname#### "+pvExcessReq.spocname);
                Log.e("#### ","###### pvExcessReq pvname#### "+pvExcessReq.pvname);
                Log.e("#### ","###### pvExcessReq clientremarks#### "+pvExcessReq.clientremarks);
                Log.e("#### ","###### pvExcessReq vendor#### "+pvExcessReq.vendor);
                Log.e("#### ","###### pvExcessReq capitalizationdate#### "+pvExcessReq.capitalizationdate);
                Log.e("#### ","###### pvExcessReq saveflag#### "+pvExcessReq.saveflag);
                Log.e("#### ","###### pvExcessReq imagecount#### "+pvExcessReq.imagecount);
                Log.e("#### ","###### pvExcessReq pvestatus#### "+pvExcessReq.pvestatus);
                Log.e("#### ","###### pvExcessReq pveremarks#### "+pvExcessReq.pveremarks);
                Log.e("#### ","###### pvExcessReq image5#### "+pvExcessReq.image5);
                Log.e("#### ","###### pvExcessReq image4#### "+pvExcessReq.image4);
                Log.e("#### ","###### pvExcessReq image3#### "+pvExcessReq.image3);
                Log.e("#### ","###### pvExcessReq image2#### "+pvExcessReq.image2);
                Log.e("#### ","###### pvExcessReq image1#### "+pvExcessReq.image1);




                Map<String, String> req = new HashMap<String, String>();
                req.put("Token", SharedPrefUtil.getString(USER_TOKEN, "", AMSApplication.instance));
                AppDatabase.getAppDatabase(AMSApplication.instance).pvExcessDao().insertSingleInfo(pvExcessReq);
                savePvExcessAsset(pvExcessReq, req);
            }catch (Exception e){
                showProgress.setValue(false);
                Toast.makeText(AMSApplication.instance,"Error in Record",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    public void savePvExcessAsset(PvExcessReq pvExcessReq, Map<String, String> req) {
        //   Gson gson = new Gson();
       /* String jsonTagItems = gson.toJson(pvItems);
        Log.e("####", "########## jsonTagItems ######### " + jsonTagItems);
*/

        pvExcessReq.image1 = imageOne.getValue();
        pvExcessReq.image2 = imageTwo.getValue();
        pvExcessReq.image3 = imageThree.getValue();
        pvExcessReq.image4 = imageFour.getValue();
        pvExcessReq.image5 = imageFive.getValue();

        ExcessPvRepo.getInstance().postExcessPv(req, pvExcessReq, new IExcessPvRepo.ExcessCallback() {
            @Override
            public void onDataLoaded(ExcessRes excessRes) {
              /*  Log.e("####", "######## tagAssetRes ########## " + pvAssetRes.responseMessage);
                Log.e("####", "######## pvID ########## " + pvID);
                Log.e("####", "######## pvDetailID ########## " + pvDetailID);
                updatePvDetailData(pvID, pvDetailID, false);*/
                updateExcessData(excessRes.assetnumber);

                showProgress.setValue(false);

            }

            @Override
            public void onDataNotAvailable() {
                showProgress.setValue(false);
                Log.e("####", "######## tagAssetRes onDataNotAvailable ########## ");
            }
        });
    }


    public void updateExcessData(String assetnumber) {
        //    PvAssetRes pvAssetRes= new PvAssetRes();

        AppDatabase.getAppDatabase(AMSApplication.instance).pvExcessDao().deleteWhere(assetnumber);

    }

}
