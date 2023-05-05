package com.miliky.sim_card_info.simCard

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.IBinder
import android.telephony.SubscriptionInfo
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import com.miliky.sim_card_info.permission.PermissionManager
import com.miliky.sim_card_info.simCard.data.SimCard

class SimCardManager {

    private val context: Context

    private val activity: Activity

    private val permissionManager: PermissionManager

    private val telephonyManager: TelephonyManager

    constructor(context: Context, activity: Activity, permissionManager: PermissionManager) {
       this.context = context
       this.activity = activity
       this.permissionManager = permissionManager
       telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    }

    fun getSimCardInfo (): List<SimCard>? {
        val simCardList: MutableList<SimCard> = getSimCardList()
        if (simCardList.isEmpty()) {
            val simCard: SimCard? = getSimCard()
            if (simCard != null) simCardList.add(simCard)
        }
        if (simCardList.isEmpty()) {
            return null
        }
        return simCardList
    }

    private fun getSimCard () : SimCard? {
        if (permissionManager.subscriptionsPremission(activity)) return null
        if (telephonyManager.line1Number == null || telephonyManager.line1Number.isEmpty()) return null
        return SimCard(telephonyManager)
    }

    private fun getSimCardList (): MutableList<SimCard> {
        val simCardList = mutableListOf<SimCard>()
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            for (subscriotionInfo in getSubscriptions()) {
                simCardList.add(SimCard(subscriotionInfo))
            }
        }
        return simCardList
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun getSubscriptions () : List<SubscriptionInfo> {
        val subscriptionManager: SubscriptionManager = context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager ?: return arrayListOf()
        val subPermission : Boolean = permissionManager.subscriptionsPremission(activity)
        if (subPermission) return arrayListOf()
        return subscriptionManager.activeSubscriptionInfoList
    }
}
