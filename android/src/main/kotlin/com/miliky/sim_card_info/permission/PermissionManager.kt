package com.miliky.sim_card_info.permission

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.flutter.plugin.common.PluginRegistry.RequestPermissionsResultListener

class PermissionManager : RequestPermissionsResultListener {

    private val PERMISSIONS_REQUEST_READ_PHONE_STATE_CODE = 0

    fun requestPermission (activity: Activity) {
        var permissionArr: Array<String>? = null
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
           val hasPermission: Boolean = ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_NUMBERS)
            if (!hasPermission) {
                permissionArr = arrayOf(Manifest.permission.READ_PHONE_NUMBERS)
            }
        } else {
            val hasPermission: Boolean = ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE)
            if (!hasPermission) {
                permissionArr = arrayOf(Manifest.permission.READ_PHONE_STATE)
            }
        }

        if (permissionArr != null) {
           ActivityCompat.requestPermissions(activity, permissionArr, PERMISSIONS_REQUEST_READ_PHONE_STATE_CODE)
        }
    }

    fun hasPermission (context: Context): Boolean {
        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_NUMBERS) == PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
        }
    }

     fun subscriptionsPremission (activity: Activity) : Boolean {
      if (
           ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_NUMBERS) == PackageManager.PERMISSION_DENIED
           &&
           ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED
       ) return true
       return false
     }

    @SuppressLint("SuspiciousIndentation")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ): Boolean {
        if (requestCode != PERMISSIONS_REQUEST_READ_PHONE_STATE_CODE || grantResults.isEmpty()) return false
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
           return true
        }
        return false
    }
}