package com.miliky.sim_card_info.simCard.data

import android.annotation.SuppressLint
import android.os.Build
import android.telephony.SubscriptionInfo
import android.telephony.TelephonyManager
import com.squareup.moshi.JsonClass
import org.json.JSONException
import org.json.JSONObject


@Suppress("DEPRECATION")
@JsonClass(generateAdapter = true)
class SimCard() {
    var slotIndex: Int = 0
    var carrierName: String = ""
    var displayName: String = ""
    var phoneNumber: String = ""
    var countryPhonePrefix: String = "86"

   constructor (subscriptionInfo: SubscriptionInfo) : this() {
       this.carrierName = subscriptionInfo.carrierName.toString()
       this.displayName = subscriptionInfo.displayName.toString()
       this.slotIndex = subscriptionInfo.simSlotIndex
       this.phoneNumber = _delPhoneNumberPrefix(subscriptionInfo.number)
   }

   @SuppressLint("MissingPermission")
   constructor (telephonyManager: TelephonyManager) : this() {
       if (telephonyManager.simOperator != null)
           carrierName = telephonyManager.simOperatorName
       if (telephonyManager.simOperator != null)
           displayName = telephonyManager.simOperatorName
       if (telephonyManager.line1Number != null && telephonyManager.line1Number.isNotEmpty()) {
           phoneNumber = _delPhoneNumberPrefix(telephonyManager.line1Number)
       }
   }

   private fun _delPhoneNumberPrefix (phoneNumber: String = "") : String {
      if (phoneNumber.startsWith("+86")) return phoneNumber.substring(3)
      if (phoneNumber.startsWith("0")) return phoneNumber.substring(1)
      return phoneNumber
   }
}