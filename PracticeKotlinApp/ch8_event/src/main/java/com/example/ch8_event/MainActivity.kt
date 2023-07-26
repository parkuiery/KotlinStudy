package com.example.ch8_event

import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ch8_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //3초 이내에 back button을 두번 느르면 종료 아니면 토스트
    //back button 누른 시간 저장
    var initTime = 0L

    //chornameter가 멈추었을때 그 다음 그 시간부터 다시 시작하려면 멈췄을때 시간 저장
    var pauseTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //event source 와 event handler 을 listner로 연결하고자 한다
        //event handler 는 지정된 interface 구현해야한다
        //event handler를 anonymous inner class 로 만든다
        //kotlin 의 anonymous inner class 는 object 예약어로 object { }
        //특정 interface 혹은 상위 클래스를 상속받았다면
        binding.startButton.setOnClickListener (object : OnClickListener{
            override fun onClick(p0: View?) {
                //멈추었다가 다시 start 될수도 있어서 시간 지정한 다음에 start 되게 해주어야 한다
                //Chronometer 가 내부적으로 시스템 시간을 이용해서 측적한다
                binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
                binding.chronometer.start()
                //button 의 활성 상태 조정
                binding.startButton.isEnabled = false
                binding.stopButton.isEnabled = true
                binding.resetButton.isEnabled = true
            }
        })

        //SAN - Single Abstract Method
        //자바에서 선언된 인터페이스, 추상함수가 하나짜리라면
        //이런 인터페이스를 구현하는 anonymous inner 클래스를 만든다면
        //위의 스타일로 작성이 가능함
        //SAM 기법을 적용해서 추상함수의 내용만 { } 에 작성할 수 있다
        binding.stopButton.setOnClickListener {
            //멈춘 시간 저장
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            //button 조정
            binding.startButton.isEnabled = true
            binding.startButton.isEnabled = false
            binding.resetButton.isEnabled = true
        }
        binding.resetButton.setOnClickListener {
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            binding.startButton.isEnabled = true
            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = false
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        //back button 이 클릭된거라면
        if(keyCode === KeyEvent.KEYCODE_BACK ) {
            //3초 이내에 지난 경우라면
            if(SystemClock.currentThreadTimeMillis() - initTime >3000) {
                //토스트를 띄운다
                //show 하는 순간 뜬다
                //세번째 매개변수의 시간이 지나면 자동으로 닫힌다
                //시간을 숫자값으로 줄수는 없다 상수로만 지정가능 SHORT -3초, LONG -5초
                Toast.makeText(this,"종료하려면 한번 더 누르세요.", Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }
        //back button이 눌렀지만 3초 이내에 두번 눌렀거나
        //다른 버튼이 눌렸다면 원래 기본대로 처리
        return super.onKeyDown(keyCode, event)
    }
}