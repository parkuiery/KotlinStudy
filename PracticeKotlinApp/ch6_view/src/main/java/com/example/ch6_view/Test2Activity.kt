package com.example.ch6_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//UI 구성은 Layout xml로
class Test2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //화면 출력
        setContentView(R.layout.activity_test2)
    }
}