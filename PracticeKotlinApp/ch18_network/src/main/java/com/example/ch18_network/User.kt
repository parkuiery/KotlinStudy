package com.example.ch18_network

import com.google.gson.annotations.SerializedName

//서버데이터 추상화 VO(DTO)클래스, 서버에서 넘어오는 데이터 보고 만들면 된다
class User {
    var id: String,
    @SerializedName("first_name")//ison 키와 변수명이 다른 경우에만 선언
    var firstName: String,
    @SerializedName("last_name")
    var lastName: String,
    lateinit var avatar: String //서버에서 넘어오는 이미지 다운로드 url
}