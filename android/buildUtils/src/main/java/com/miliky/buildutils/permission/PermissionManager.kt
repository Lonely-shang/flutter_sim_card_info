package com.miliky.buildutils.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

/**
 *
 *
 * @Author: Miliky
 * @Date: 2023/5/29 11:07
 * @Description: [爱意随风起，风止意难平。]
 */
class PermissionManager {
    fun hasPermission (context: Context): Boolean {
        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_NUMBERS) == PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun subscriptionsPremission (activity: Activity) : Boolean {
        return (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_NUMBERS) == PackageManager.PERMISSION_DENIED
                &&
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED)
    }
}