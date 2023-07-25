package com.example.ch3_kotlin

class Test_kotlin {
}

class User {
    val name: String = "hello"
    fun someFun() {
        println("User... pr()")
    }
}

val doto1 = 10

fun sayHello() {
    println("top level fun : sayHello()")
}

//kotlin은 문자의 마지막 ; 강제 안함
//객체 생성에 new 예약이 없다
fun main() {
    val obj = User()
    obj.someFun()

    sayHello()
}