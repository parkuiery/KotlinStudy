package com.example.ch10_permission_noti

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

//permission
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //manifest에 퍼미션을 등록했지만
        //유저가 거부할 수도 있고 앱의 초기상태는 denied
        //코드에서 퍼미션 체크 -> 조정 다이얼로그 -> 어떻게 조정된건지 판단

        //어디선가 이 launcher 객체의 launch() 함수를 호출하는 순간 일이 진행 된다
        val launcher = registerForActivityResult(
            ActivityResultContracts.RequestPermission() //작업자 객체 퍼미션 다이얼로그 띄어주는 역할
        ){ //다이얼로그가 닫혇을대 호출되는 콜백 ,사후처리
            if(it){
                Log.d("kkang","callback.. granted..")
            }else{
                Log.d("kkang", "callback.. denied")
            }
        }

        //퍼미션 체크
        val status = ContextCompat.checkSelfPermission(
            this,
            "android.permission.ACCESS_FINE_LOCATION"
        )

        if(status == PackageManager.PERMISSION_GRANTED) {
            Log.d("kkang","check granted")
        }else{
            //현재 퍼미션이 거부상태다
            //퍼미션을 조정하라고 다이얼로그 띄워야한다, 시스템 다이얼로그
            //launcher 에 일을 시키면 된다
            launcher.launch("android.permission.ACCESS_FINE_LOCATION")
        }
    }
}