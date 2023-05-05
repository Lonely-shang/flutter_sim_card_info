package com.miliky.sim_card_info

import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.PluginRegistry.Registrar



/** SimCardInfoPlugin */
class SimCardInfoPlugin: FlutterPlugin, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
//  private lateinit var channel : MethodChannel

  private  var methodCallHandlerImpl: MethodCallHandlerImpl? = null

  companion object {
    @Suppress("DEPRECATION")
    fun registerWith(registrar: Registrar) {
      val methodCallHandlerImpl = MethodCallHandlerImpl()
      methodCallHandlerImpl?.setActivity(registrar.activity()!!)
      methodCallHandlerImpl?.startListening(registrar.context(), registrar.messenger())
    }
  }

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    methodCallHandlerImpl = MethodCallHandlerImpl()
    methodCallHandlerImpl?.startListening(flutterPluginBinding.applicationContext, flutterPluginBinding.binaryMessenger)
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    dispode()
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
      methodCallHandlerImpl?.setActivity(binding.activity)
  }

  override fun onDetachedFromActivityForConfigChanges() {
    onDetachedFromActivity()
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    onAttachedToActivity(binding)
  }

  override fun onDetachedFromActivity() {
    methodCallHandlerImpl?.setActivity(null)
  }

  private fun dispode () {
    if (methodCallHandlerImpl != null) {
      methodCallHandlerImpl?.stopListening()
      methodCallHandlerImpl?.setActivity(null)
      methodCallHandlerImpl = null
    }
  }
}
