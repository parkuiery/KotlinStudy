package com.example.practicekotlinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//Activity의 특수 서브 클래스인 AppCompatActivity를 상속받아서 정의 -> 액티비티이다
//하위버전 호환성 문제를 해결하기 위해서
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //화면 출력
        //R -> Resource
        setContentView(R.layout.activity_main)
    }
}