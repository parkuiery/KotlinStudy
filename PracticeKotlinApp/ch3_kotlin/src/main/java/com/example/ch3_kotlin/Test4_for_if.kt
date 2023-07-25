package com.example.ch3_kotlin

fun testWhen(arg: Any) {
    when(arg){
        //값으로 조건
        1 -> println("arg is 1")
        10,20 -> println("arg is 10 or 20")
        30 .. 40 -> println("arg is 30<= <=40")
        "hello" -> println("arg is hello")
        //값이 아닌 타입으로 조건이 가능하다
        is Int -> println("arg type is int")
        is String -> println("arg type is string")
    }
}

fun main() {
    testWhen(32)


    //if----------------------------------
    val data = 10
    val result = if(data>5){
        println("data >5")
        data
    }else {
        println("data <= 5")
        5
    }
    println("result : $result")

    //for ----------------------------
    for(i in 10 downTo 1 step 2) {
        println(i)
    }

    val array = arrayOf("hello", "world")
    for(i in array){
        println("array1 : $i")
    }
    for(i in array.indices) {
        println("array2 : $i")// index 대입
    }

    for((i,v) in array.withIndex()){
        println("arrat3 : $i, $v") //index와 값을 같이 받고 싶을 떄
    }

    //map은 key-value를 표현하는 Entryㄲ체가 대입된다
    val map = mapOf<String,String>("one" to "hello","two" to "world")
    for(item in map)
        println("key : ${item.key}, value : ${item.value}")
}