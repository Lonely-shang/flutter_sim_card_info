package com.miliky.sim_card_info.utils

import com.miliky.sim_card_info.simCard.data.SimCard
import com.squareup.moshi.Moshi


object Codec {

    val moshi = Moshi.Builder().build()

    fun encodeResult(list: List<SimCard>) =
        moshi.adapter(List::class.java).toJson(list)
}
