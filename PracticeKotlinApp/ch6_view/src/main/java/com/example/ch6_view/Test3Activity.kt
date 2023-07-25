package com.example.ch6_view

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Test3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //화면 출력 뷰 객체 생성 완료 inflate
        setContentView(R.layout.activity_test3)
        //필요한 뷰 객체 획득
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        //뷰에 이벤트 등록
        button1.setOnClickListener {
            button2.visibility = View.VISIBLE
        }
        button3.setOnClickListener {
            button2.visibility = View.INVISIBLE
        }


    }
}