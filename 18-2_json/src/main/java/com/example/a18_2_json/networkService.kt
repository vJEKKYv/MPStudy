package com.example.a18_2_json
import retrofit2.Call
import retrofit2.http.GET;
import retrofit2.http.Query;

interface networkService{
    @GET("v2/everything")
    fun getList(
        @Query("q") q:String?,
        @Query("apiKey") apiKey:String?,
        @Query("page") page:Int,
        @Query("qpageSize") pageSize:Int
    ): Calll<PageListModel>
}