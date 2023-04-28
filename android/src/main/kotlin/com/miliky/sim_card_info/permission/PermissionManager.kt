package com.miliky.sim_card_info.permission

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import androidx.core.app.ActivityCompat
import io.flutter.plugin.common.PluginRegistry.RequestPermissionsResultListener

class PermissionManager : RequestPermissionsResultListener {

    private val PERMISSIONS_REQUEST_READ_PHONE_STATE_CODE = 0

    fun requestPermission () {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {

        }
//            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
//                    Manifest.permission.READ_PHONE_NUMBERS)) {
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//            } else {
//                ActivityCompat.requestPermissions(activity,
//                    new String[]{Manifest.permission.READ_PHONE_NUMBERS}, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
//            }
//        } else {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
//                    Manifest.permission.READ_PHONE_STATE)) {
//            } else {
//                ActivityCompat.requestPermissions(activity,
//                    new String[]{Manifest.permission.READ_PHONE_STATE}, MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
//            }
//        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ): Boolean {
        if (requestCode != PERMISSIONS_REQUEST_READ_PHONE_STATE_CODE || grantResults.isEmpty()) return false

//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                if (permissionEvent != null)
//                    permissionEvent.success(true);
//                generateMobileNumber();
//                return true;
//            } else {
//                if (permissionEvent != null)
//                    permissionEvent.success(false);
//            }
            return false
//        result.error("PERMISSION", "onRequestPermissionsResult is not granted", null);
    }
}