package com.miliky.sim_card_info.utils

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(val success: Boolean,
                  val message: String,
                  val data: Any? = null,
) {
    companion object {
        fun success(data: Any?) = Result(success = true, message= "success", data = data)

        fun failure(message: String) = Result(
            success = false,
            message = message
        )
    }

}