package com.example.haruto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Employee (

    @Expose
    @SerializedName("employee_username") val employee_username: String,

    @Expose
    @SerializedName("employee_password") val employee_password: String,

    @Expose
    @SerializedName("employee_name") val employee_name: String,

    @Expose
    @SerializedName("employee_gender") val employee_gender: String,

    @Expose
    @SerializedName("employee_tel") val employee_tel: String,

    @Expose
    @SerializedName("employee_email") val employee_email: String,

    @Expose
    @SerializedName("employee_address") val employee_address: String
){
}