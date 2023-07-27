package com.example.ch13_activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.ch13_activity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult() //요청 처리자 인텐트 발생
        ){
            //되돌아 왔을때 사후 처리
            //넘어온 데이터 받고
            val resultData = it.data?.getStringArrayExtra("detail")
            //화면출력
            binding.resultView.text = "result: $resultData"
        }

        binding.button.setOnClickListener {
            //Intent 준비
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data1","hello")
            intent.putExtra("data2",10)
            launcher.launch(intent)
        }

        binding.button3.setOnClickListener {
            //브라우저가 앱의 activity 실행
            // 암시적 인텐트
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW //action 문자열은 개발자 임의 문자열
            //컴포넌트 능력치를 표현하는 문자열을 해달라는 것이 권장사항
            //대부분 상세보기를 제공하는 것은 액션문자열이 view
            intent.data = Uri.parse("https://www.google.com")
            startActivity(intent)
        }

        binding.button4.setOnClickListener {
            //지도 앱
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.77.127.5"))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
    }
}