package com.example.ch3_kotlin.test2

//top level, class member 변수는 선언과 동시에 초기화 해야한다
//만약 초기화를 나중에 하고자 한다면 lateinit 혹은 by lazy 로
val deta1 : Int = 10
var deta2 : Int = 10

class Myclass {
    val data3 : Int = 10
    var data4 : Int = 10

    fun someFun() {
        //함수 내부에 선언된 local variable은 선언과 동시에 초기화 안해도 된다
        val data5 : Int
        val data6 : Int

        //어디선가 이용하기 전에 꼭 초기화 되어 있어야 한다
        data5 = 10
        data6 = 10
        val result = data5+data6
    }

    //null - 객체가 선언되어 있는데 생성 안된 상태
    //null 상태에서 객체 변수, 함수에 접근하면 NPE가 발생한다
    //null saftey 지원
    //컴파일 단계에사 찰자히 막힘
    //null safety 를 지원하는 언어는 타입으로 nullable 과  non-null을 구분한다
    //?가 추가되면 nullable, 추가되지 않으면 non-null
    var data7: Int? = 10

    fun some() {
        data7 = null
    }

    //val 은 immutable 이다
    //상수는 아님
    //변수에 setter/getter 함수가 내장되어 있다
    //변수 값 변경은 불가능하지만 getter 함수를 customizing 해버리면 다른 값이 나올수 있음
    val data8: Int = 10
}

fun main() {
    val obj = Myclass()
    println("data8 : ${obj.data8}")
}