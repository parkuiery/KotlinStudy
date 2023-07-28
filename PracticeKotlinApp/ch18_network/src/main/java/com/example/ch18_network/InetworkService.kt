package com.example.ch18_network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface InetworkService {
    //서버 연동시 호출할 함수를 정의, annotation으로 정보 지정하면서
    @GET("api/users")
    fun getUserList(@Query("page")page: String): Call<UserList>

    @GET
    fun getAvatarImage(@Url url: String): Call<ResponseBody>
}