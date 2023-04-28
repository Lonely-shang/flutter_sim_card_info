package com.miliky.sim_card_info

import android.app.Activity
import android.content.Context
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class MethodCallHandlerImpl () : MethodChannel.MethodCallHandler {
    lateinit var context: Context

    private var activity: Activity? = null

    private var channel: MethodChannel? = null


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

    private fun stopListening() {
        if (channel != null) {
            channel?.setMethodCallHandler(null)
            channel = null
        }
    }

    fun setActivity (activity: Activity) {
       this.activity = activity
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "requestPermission" -> onRequestPermission(result)
            else -> result.notImplemented()
        }
    }

    private fun onRequestPermission(result: MethodChannel.Result) {

        TODO("Not yet implemented")
    }


}