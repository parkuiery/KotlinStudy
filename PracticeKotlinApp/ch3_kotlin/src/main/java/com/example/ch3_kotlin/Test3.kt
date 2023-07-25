package com.example.ch3_kotlin

fun main() {
    //argument - 함수 선언자 입장에서 자신의 함수에 선언한 매개변수
    //parameter - 함수를 호출하는 사람 입장에서 전달할 매개변수
    //default argument 만 선언되었다면 호출하는 입장에서 optional 생략 가능
    fun sayHello(arg: String = "kotlin") {}
    fun myFun(arg1:Int, arg2:String = "kaka") {}

    sayHello()
    sayHello("java")

    myFun(10)
    myFun(10,"java")
    myFun(arg2 = "java", arg1 = 20)
}