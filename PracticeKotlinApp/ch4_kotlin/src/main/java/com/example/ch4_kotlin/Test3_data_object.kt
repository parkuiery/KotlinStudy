package com.example.ch4_kotlin.test1

class User(val no:Int, val name: String)

data class UserData(val no:Int, val name:String)

open class Super {

}

class Outer {
    //anonymouns inner class
    // :Super 는 이름은 없지만 Super를 상속받은 클래스
    //이름이 없기 때문에 선언과 동시에 생성 다시는 생성할 수 없다
    val obj = object : Super() {

    }
}

fun main() {
    val user1 = User(1,"kim")
    val user2 = User(1,"kim")

    val user3 = UserData(1,"kim")
    val user4 = UserData(1,"kim")

    println("user1.equals(user2) : ${user1.equals(user2)}")
    println("user1.equals(user3) : ${user1.equals(user3)}")
    //data 클래스의 경우 equals 함수가 객체 비교(주소값 비교)가 아니라 데이터 비교가 된다
    println("user3.equals(user4) : ${user3.equals(user4)}")

    println("user1 : ${user1}")
    println("user3: ${user3}")


}