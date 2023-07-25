package com.example.ch4_kotlin

//주 생성자에 한해서만 매개변수를 var,  val을 추가해서 클래스 멤버변수로 선언할 수 있다
class User (var id: String, var name: String){

    var email: String = ""
    // 주 생성자가 선언되어 있다면 보조 생성자에게 주 생성자 호출해야 한다
    constructor(id: String, name: String, email: String) : this(id,name){
        println("cunstructor : id = $id, name = $name, email = $email")
        this.email = email
    }
    fun some() {
        println("some : id = $id, name = $name, email = $email")
    }
}

fun main() {
    //객체 생성에 new 를 사용하지 않는다
    val obj = User("1","kim","a@a.com")
    obj.some()
}