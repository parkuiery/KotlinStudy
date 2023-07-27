package com.example.ch13_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch13_activity.databinding.ActivityTest1Binding

//Activity Lifecycle 테스트
class Test1Activity : AppCompatActivity() {

    var count = 0;
    lateinit var binding: ActivityTest1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTest1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button5.setOnClickListener {
            count++
            binding.textView2.text = "$count"
        }
    }

    //onDestroy 호출되기 직전에 호출된다
    //종료시 유실되면 안되는 데이터를 저장하는 목적
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count",count)//액티비티는 종료되지만 프로세스 메모리에 데이터 캐싱

    }

    //라이프사이클 함수이다 항상 호출되지는 않는다 액티비티를 위한
    // 데이터가 Bundle로 저장된 것이 있는 경우만 onCreate 함수 후에 호출된다
    //데이터 복원
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val data = savedInstanceState.getInt("count")
        binding.textView2.text = "$data"
    }
}