package com.example.ch13_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch13_activity.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //자신을 실행시킨 곳에서 넘긴 데이터 추출
        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getIntExtra("data2",0)

        //결과는 화면 출력해서 확인
        binding.textView.text = "data1 : $data1, data2 : $data2"

        binding.button2.setOnClickListener {
            //화면을 이전 화면으로 되돌린다 결과 데이터 포함해서
            //결과 데이터 담는다 똑같이 intent 의 extra 데이터로 담아주면 된다
            intent.putExtra("detail","world")
            //화면을 되돌리기 전에 상태코드 지정 너네의 요청을 어떻게 처리할 것인지에 대한 설명
            setResult(RESULT_OK, intent)
            //코드적으로 화면을 이전 화면으로 되돌리기
            //시스템에 한 액티비티 종료 의뢰 시스템에 의해 자동으로 이전화면으로 돌아가기
            finish()
        }
    }
}