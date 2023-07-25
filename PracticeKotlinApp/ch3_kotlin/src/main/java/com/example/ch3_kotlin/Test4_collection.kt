package com.example.ch3_kotlin

fun main() {
    //array--------------------------------------------------

    //객체를 선언하고 따로 배열 값을 지정
    val a1:Array<Int> = Array(3,{0})
    a1[0] = 10
    a1[1] = 20
    a1.set(2,30)//클래스의 객체

    //배열 변수를 선언하면서 각 값을 지정하고자 한다면
    val a2 = arrayListOf(10,20,30)

    //자바 호환성 문제로 각 탕입에 해당되는 배열 클래스가 제공되는 것
    val a3: IntArray = IntArray(2)
    val a4 = intArrayOf(10,20)

    //2차원 배열
    //코틀린에서는 뱌열도 클래스 객체이다 배열 객체에 배열 객체가 들어가면 된다
    val a5: Array<Array<Int>> = Array(2,{Array(3,{0})})


    //list------------------------------------------------------
    //mutable, immutable 구분해서 사용해야 한다
    val list1 = listOf<Int>(10,20,10)
    list1.forEach{ println(it) }
    //list1.add(40)
    //list1.set(0,50)

    val list2 = mutableListOf<Int>(10,20,30)
    list2.add(40)
    list2.set(0,50)
    list2.forEach{ println(it) }

    //list vs set
    val set1 = setOf<Int>(10,20,10)
    set1.forEach{ println("set : $it") }


    //map-----------------------------------------
    //index 개념이 없음 , key-value로 데이터
    //<String,String> : 제네릭 타입 <키 타입, 값 타입>
    val map1 = mapOf<String,String>(Pair("one","hello"), Pair("two","world"))
    val map2 = mapOf<String,String>("one" to "hello","two" to "world")
    println("map : ${map1.get("one")}")
}