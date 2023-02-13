package com.example.haruto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Customer(

    @Expose
    @SerializedName("customer_username") val customer_username: String,

    @Expose
    @SerializedName("customer_password") val customer_password: String,

    @Expose
    @SerializedName("customer_name") val customer_name: String,

    @Expose
    @SerializedName("customer_gender") val customer_gender: String,

    @Expose
    @SerializedName("customer_tel") val customer_tel: String,

    @Expose
    @SerializedName("customer_email") val customer_email: String,

    @Expose
    @SerializedName("customer_address") val customer_address: String
) {}