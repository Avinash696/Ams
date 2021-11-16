package com.ams.amsvistara.utils;

import android.app.Activity;
import android.content.Context;

import androidx.core.app.ActivityCompat;


public enum RequestPermissions {

    Instance;

    /**
     * This method request the runtime permissions from the user.
     *
     * @param context     Context
     * @param permission  String Array of Permissions to be requested
     * @param requestCode int
     */
    public void requestPermission(Context context, String[] permission, int requestCode) {

        ActivityCompat.requestPermissions((Activity) context, permission, requestCode);

    }

    /**
     * This method shows dialog box when user select Never Ask Again when runtime permission is requested.
     *
     * @param context Context
     * @param title   String
     * @param message String
     */
    /*public void showSettingsDialog(final Context context, String title, String message) {
        DialogHelper.showDialog(context, new DialogListener() {
            @Override
            public void onPositiveClickedFromDialog() {
                if (context != null) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                    intent.setData(uri);
                    context.startActivity(intent);
                }
            }


        }, null, message, context.getString(R.string.settings), context.getString(R.string.not_now), "", false);
    }*/
}
