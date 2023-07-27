package com.example.ch10_permission_noti

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.ch10_permission_noti.databinding.ActivityTest1Binding

class Test1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityTest1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //퍼미션이 요구되는 기능이다 퍼미션 다디얼로그 사후처리
        val launcher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions() //요청 처리자, 퍼미션 조정 다이얼로그 띄운다
            //한꺼번에 여러개의 퍼미션 조정을 원할 떄
        ){
            //결과는 여러개
            if(it.all {a-> a.value == true}){
                //모두다 허락이 되었다면
                noti()
            }else{
                Toast.makeText(this,"Permissin denied" , Toast.LENGTH_SHORT).show()
            }
        }

        binding.button.setOnClickListener {
            //퍼미션 요구가 api level 33부터 추가되었다 그 하위 버전은 퍼미션이 없다
            //퍼미션이 없는데 퍼미션이 체크하면 거부가 나온다 버전별로 상이하게
            //Build.VERSION.SDK_INT - 앱이 설치된 유저폰의 api level
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                //퍼미션 체크
                if(ContextCompat.checkSelfPermission(
                        this,
                        "android.permission.POST_NOTIFICATIONS"
                ) == PackageManager.PERMISSION_GRANTED) {
                    noti()
                }else{
                    //퍼미션 거부 상태 조정 다이얼로그 띄운다
                    //RequestPermission() - 단일 퍼미션 문자열
                    //RequestMultipermission() - 여러 퍼미션 배열로
                    launcher.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
                }
            }else{
                //33버전 미만에서는 퍼미션 요구 안된다
                noti()
            }
        }
    }

    fun noti() {
        //as - 명시적 캐스팅
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        var builder :NotificationCompat.Builder
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //26버전 이상에서는 꼭 채널 개념을 대입해야
            //채널 준비
            //id 가 중요 식별자
            val channel = NotificationChannel("one","channel name",NotificationManager.IMPORTANCE_LOW)
            channel.description = "channel description"

            //만든 채널을 시스템에 등록
            manager.createNotificationChannel(channel)

            //Notification 을 만드는 Builder 를 준비
            //채널 개념을 대임
            builder = NotificationCompat.Builder(this,"one")
        }else {
            builder = NotificationCompat.Builder(this)
        }

        //notification 구성요소를 builder 에게 대입
        //android.R = android platform에서 제공되는 리소스
        builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
        builder.setWhen(System.currentTimeMillis())
        builder.setContentTitle("Title")
        builder.setContentText("text")

        //메세지의 사람을 표현
        val person1 = Person.Builder()
            .setName("kim")
            .setIcon(IconCompat.createWithResource(this,R.drawable.person1))
            .build()
        val person2 = Person.Builder()
            .setName("lee")
            .setIcon(IconCompat.createWithResource(this,R.drawable.person2))
            .build()

        //메세지 내용
        val message1 = NotificationCompat.MessagingStyle.Message(
            "hello",
            System.currentTimeMillis(),
            person1
        )
        val message2 = NotificationCompat.MessagingStyle.Message(
            "world",
            System.currentTimeMillis(),
            person2
        )

        //noti를 구성하는 스타일
        val messageStyle = NotificationCompat.MessagingStyle(person1)
            .addMessage(message1)
            .addMessage(message2)
        builder.setStyle(messageStyle)

        //noti 발생
        manager.notify(11,builder.build())
    }
}