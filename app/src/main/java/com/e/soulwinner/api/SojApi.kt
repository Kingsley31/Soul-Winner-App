package com.e.soulwinner.api

import com.e.soulwinner.models.SojApiResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SojApi {
    @FormUrlEncoded
    @POST("soj/controllers/Login.php")
    fun loginUser(
        @Field("phone") phone:String,
        @Field("code") code:String ): Call<SojApiResponse>

    @FormUrlEncoded
    @POST("soj/controllers/AllSouls.php")
    fun getUserSouls(
        @Field("user_id") userId:Int
    ): Call<SojApiResponse>


    @FormUrlEncoded
    @POST("soj/controllers/AddSoul.php")
    fun addSouls(
        @Field("user_id") userId:Int,
        @Field("firstname") firstname:String,
        @Field("lastname") lastname:String,
        @Field("phone") phone:String,
        @Field("email") email:String,
        @Field("address") address:String,
        @Field("gender") gender:String,
        @Field("occupation") occupation:String
    ): Call<SojApiResponse>

    @FormUrlEncoded
    @POST("soj/controllers/AddFeedback.php")
    fun addFeedback(
        @Field("user_id") userId:Int,
        @Field("message") message:String
    ): Call<SojApiResponse>


    companion object Factory {
        val BASE_URL="http://192.168.43.76:80"
        fun create(): SojApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(SojApi::class.java)
        }
    }
}