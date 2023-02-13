package com.example.haruto

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface harutoAPI {
    //registerCustomer
    @FormUrlEncoded
    @POST("cus")
    fun insertCus(
        @Field("customer_username") customer_username: String,
        @Field("customer_password") customer_password: String,
        @Field("customer_name") customer_name: String,
        @Field("customer_gender") customer_gender: String,
        @Field("customer_tel") customer_tel: String,
        @Field("customer_email") customer_email: String,
        @Field("customer_address") customer_address: String
    ): Call<Customer>

    //loginCustomer
    @GET("cus/{customer_username}/{customer_password}")
    fun  loginCustomer(
        @Path("customer_username") customer_username: String,
        @Path("customer_password") customer_password: String,
    ): Call<Customer>

    //Reserve
    @FormUrlEncoded
    @POST("reserve")
    fun insertReserve(
        @Field("reserve_date") reserve_date: String,
        @Field("reserve_time") reserve_time: String,
        @Field("total_price") total_price: Int,
        @Field("count") count: Int,
        @Field("customer_username") customer_username: String,
        @Field("employee_username") employee_username: String,
        @Field("status_id") status_id: Int,
    ): Call<Reserve>

    //loginEmployee
    @GET("emp/{employee_username}/{employee_password}")
    fun  loginEmployee(
        @Path("employee_username") employee_username: String,
        @Path("employee_password") employee_password: String,
    ): Call<Employee>

    //showReserve
    @GET("allreserve/{customer_username}")
    fun  showReserve(
        @Path("customer_username") customer_username: String,
    ): Call<List<Reserve>>

    //deleteReserve
    @DELETE("reserve/{reserve_id}")
    fun deleteReserve(
        @Path("reserve_id") reserve_id: String
    ): Call<Reserve>

    //updateReserve
    @FormUrlEncoded
    @PUT("reserve/{reserve_id}")
    fun updateReserve(
        @Path("reserve_id") std_id: String,
        @Field("reserve_date") reserve_date: String,
        @Field("reserve_time") reserve_time: String,
        @Field("total_price") total_price: Int,
        @Field("count") count: Int,
    ): Call<Reserve>

    //updateReserve2
    @FormUrlEncoded
    @PUT("reserve/{reserve_id}")
    fun updateReserve2(
        @Path("reserve_id") std_id: String,
        @Field("reserve_date") reserve_date: String,
        @Field("reserve_time") reserve_time: String,
        @Field("total_price") total_price: Int,
        @Field("count") count: Int,
        @Field("employee_username") employee_username: String,
        @Field("status_id") status_id: Int
    ): Call<Reserve>



    //updateCustomer
    @FormUrlEncoded
    @PUT("cus/{customer_username}")
    fun updateCustomer(
        @Path("customer_username") customer_username: String,
        @Field("customer_name") customer_name: String,
        @Field("customer_gender") customer_gender: String,
        @Field("customer_tel") customer_tel: String,
        @Field("customer_email") customer_email: String,
        @Field("customer_address") customer_address: String
    ): Call<Customer>

    //updateCustomerPW
    @FormUrlEncoded
    @PUT("cus/{customer_username}/{customer_password}")
    fun updateCustomerPW(
        @Path("customer_username") customer_username: String,
        @Path("customer_password") customer_OldPassword:  String,
        @Field("customer_password") customer_NewPassword: String
    ): Call<Customer>

    //forgetPassword
    @FormUrlEncoded
    @PUT("cus/{customer_username}/{customer_email}")
    fun forgot(
        @Path("customer_username") customer_username: String,
        @Path("customer_email") customer_email: String,
        @Field("customer_password") customer_password: String
    ): Call<Customer>

    //ค้นหา
    @GET("reserve/{reserve_id}")
    fun search(
        @Path("reserve_id") reserve_id: Int
    ): Call<Reserve>

    //ทั้งหมด
    @GET("reserve")
    fun showReserve2(): Call<List<Reserve>>

    companion object{
        fun create(): harutoAPI{
            val cusClient: harutoAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(harutoAPI::class.java)
            return cusClient
        }
    }
}