package com.example.ch6_view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ch6_view.databinding.ActivityTest3Binding

class Test3Activity : AppCompatActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //화면 출력 뷰 객체 생성 완료 inflate
//        setContentView(R.layout.activity_test3)
//        //필요한 뷰 객체 획득
//        val button1 = findViewById<Button>(R.id.button1)
//        val button2 = findViewById<Button>(R.id.button2)
//        val button3 = findViewById<Button>(R.id.button3)
//        //뷰에 이벤트 등록
//        button1.setOnClickListener {
//            button2.visibility = View.VISIBLE
//        }
//        button3.setOnClickListener {
//            button2.visibility = View.INVISIBLE
//        }
//
//
//    }

    //ViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Viewbinding 을 이용한다면 필수 입력 코드
        //자동으로 만들어진 Binding 클래스에 일을 시켜서 ,inflate
        val binding = ActivityTest3Binding.inflate(layoutInflater)
        //화면 출력 모든 뷰가 binding에 있다 모든 뷰의 계층 구조상 루트를 출력시킨다
        setContentView(binding.root)

        binding.button1.setOnClickListener{
            binding.button2.visibility = View.VISIBLE
        }

        binding.button3.setOnClickListener{
            binding.button2.visibility = View.INVISIBLE
        }
    }
}