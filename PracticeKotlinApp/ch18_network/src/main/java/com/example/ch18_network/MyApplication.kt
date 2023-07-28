package com.example.ch18_network

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//retrofit 객체를 앱이 실행되면서 최초에 한번 초기화 해야 한다
//여러 곳에서 할수도 있지만 Application 객체에서 하는게 가장 좋다
//Application - 앱의 프로세스가 살면서 가장 처음 실행시켜주는 객체, 한번만 실행
class MyApplication :Application(){
    var networkService: InetworkService

    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://regres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    init {
        //우리가 만든 인터페이스 등록
        networkService = retrofit.create(InetworkService::class.java)
    }
}