package com.example.ch5_kotlin

fun main() {
    //고차힘수를 선언한다
    //데이터를 필터링 하는 함수를 만든다
    //필터링 알로리즘은 함수를 호출하는 외부에서 전달한다
    //알고리즘이 전달되어야 한다
    fun myFilter(list: List<Int>, arg: (Int) -> Boolean) : List<Int> {
        val resultList = mutableListOf<Int>()//결과로 리턴할 데이터

        //Collection type 의 데이터 전체를 순서대로 핸들링 하기 우해서
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            //한건의 데이터 받아서
            val no = iterator.next()
            //이 데이터를 두번쨰 매개변수의 함수를 호출해서
            val result = arg(no)
            if(result) {
                resultList.add(no)
            }
        }
        return resultList
    }

    val testList = listOf<Int>(10,13,3,6,20)
    //위에 선언된 필터링 함수를 호출하겠다 필터링 알로리즘은 매개변수로 우리가 전달하겠다
    //val resultList = myFilter(testList, {no -> no > 10})
    //resultList.forEach{ println(it) }

    //위의 코드 축약형
    //매개변수 한개의 람다함수를 선언할 때, 매개변수를 it 으로 대처 가능
    //it 은 람다함수 내에서만 예약어, 매개변수를 지칭
//    val resultList = myFilter(testList,{it>10}  )

    //위의 코드를 아래처럼 작성가능
    //함수의 마지막 매개변수가 함수타입인 경우
    //그곳에 대입되는 람다함수 () 밖에 작성이 가능하다
    val resultList = myFilter(testList) {it>10}
    resultList.forEach{ println(it) }

    //kotlin에서 기본으로 제공하는 hof
    class User {
        var name = "kim"
        var age = 30
        fun sayHello() {}
    }

    val obj = User()

    //객체의 멤버에 접근한다
    //원래는 아래처럼 작성해야 한다
    obj.name = "lee"
    obj.age = 10
    obj.sayHello()

    //하나의 객체의 여러 멤버를 접근하려면 위같은 코드 귀찮
    //run -> kotlin의 hof
    obj.run {
        name = "park"
        age = 20
        sayHello()
    }
}