package com.miliky.sim_card_info

import android.app.Activity
import android.content.Context
import com.miliky.sim_card_info.permission.PermissionManager
import com.miliky.sim_card_info.simCard.SimCardManager
import com.miliky.sim_card_info.utils.Codec
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class MethodCallHandlerImpl () : MethodChannel.MethodCallHandler {
    lateinit var context: Context

    private val permissionManager: PermissionManager

    private var activity: Activity? = null

    private var channel: MethodChannel? = null

    init {
       permissionManager = PermissionManager()
    }

    fun startListening (
        context: Context,
        binaryMessenger: BinaryMessenger
    ) {
        if (channel != null) {
            stopListening()
        }
       channel = MethodChannel(binaryMessenger, "com.miliky/simCard_info")
       channel?.setMethodCallHandler(this)
       this.context = context
    }

    fun stopListening() {
        if (channel != null) {
            channel?.setMethodCallHandler(null)
            channel = null
        }
    }

    fun setActivity (activity: Activity?) {
       this.activity = activity
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "requestPermission" -> onRequestPermission(result)
            "getSimCardInfo" -> onGetSimCardInfo(result)
            "checkPermission" -> result.success(permissionManager.hasPermission(context))
            else -> result.notImplemented()
        }
    }

    private fun onGetSimCardInfo(result: MethodChannel.Result) {
        if (!permissionManager.hasPermission(context)) {
            activity?.let { permissionManager.requestPermission(it) }
        } else {
            val simCardManager = SimCardManager(context, activity!!, permissionManager)
            val simCardInfo = simCardManager.getSimCardInfo()
            if (simCardInfo.isNullOrEmpty()) {
                result.error("UNAVAILABLE", "No phone number on sim card", null)
            } else {
                result.success(Codec.encodeResult(simCardInfo))
            }
        }
    }

    private fun onRequestPermission(result: MethodChannel.Result) {
       try {
           activity?.let { permissionManager.requestPermission(it) }
       } catch (e: Exception) {
          result.error("-1", e.message, null)
       }
    }


}
