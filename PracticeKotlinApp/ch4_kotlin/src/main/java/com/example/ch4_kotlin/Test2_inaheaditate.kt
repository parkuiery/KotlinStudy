package com.example.ch4_kotlin

//open 예약어를 추가해야지만 하위 클래스 정의 가능
open class Super (no:Int) {
    //오버라이드를 허가하고자 한다면 open 예약어 추가
    open fun superFun() { println("super superFun()") }
}

//하위 클래스 객체 상성될때 상위 클래스의 생성자는 무조건 호출되어야 한다
class Sub(no:Int): Super(no) {
    fun subFun() {}
    //상위에 정의된 함수를 하위에서 재정의하고자 한다 오버라이드
    //오버라이드를 받는 함수는 꼭 override 예약어로 선언되어야한다
    override fun superFun() {
        println("sub spuerFun()")
    }
}

fun main() {
    val obj1 = Sub(10)
    obj1.superFun()

    val obj2 = Super(10)
    obj2.superFun()

    //하위 객체를 상위 타입에 대임
    //하위 -> 상위 : 캐스팅(casting) 가능하다
    val obj3: Super = Sub(10)
    obj3.superFun() //타입이 Super 이고 SupperFun() 을 호출했음에도 불구하고
    //실제 대입된 객체는 Sub 임으로 Sub의 superFun()이 호출된다
    //상위의 함수를 호출했는데 하위의 함수가 실행됨
    //->다형성
    //상속,오버라이드,캐스팅
}