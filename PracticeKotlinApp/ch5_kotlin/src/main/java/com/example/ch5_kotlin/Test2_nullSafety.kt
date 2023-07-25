package com.example.ch5_kotlin

fun main() {
    var data1: String? = "kim"

    //MPE 를 방지하기 위해서는 개발자가 코드에서 null 체크해서 작성
    var result1 = if(data1 != null) {
        data1.uppercase()
    }else{
        null
    }
    //위의 코드를 nu;; safety 연산자를 이용해 아래처럼 작성
    var result2 = data1?.uppercase()

    //null이 아닌 경우 여러라인을 실행
    data1?.let {
        //................
    }

    //null이 아닌경우 혹은 null인 경우 무언가 실행
    data1?.run {
        //null 이 아닌 경우
    }?: run {
        //null 인 경우
    }
}