package com.example.haruto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Time
import java.util.*

class Reserve(
    @Expose
    @SerializedName("reserve_id") val reserve_id: String,

    @Expose
    @SerializedName("reserve_date") val reserve_date: String,

    @Expose
    @SerializedName("reserve_time") val reserve_time: String,

    @Expose
    @SerializedName("total_price") val total_price: Int,

    @Expose
    @SerializedName("count") val count: Int,

    @Expose
    @SerializedName("customer_username") val customer_username: String,

    @Expose
    @SerializedName("employee_username") val employee_username: String,

    @Expose
    @SerializedName("status_id") val status_id: Int
) {
}