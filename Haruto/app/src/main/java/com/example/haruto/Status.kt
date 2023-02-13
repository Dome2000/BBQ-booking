package com.example.haruto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Status(
    @Expose
    @SerializedName("status_id") val status_id: Int,

    @Expose
    @SerializedName("status_reserve") val status_reserve: String,

)  {
}