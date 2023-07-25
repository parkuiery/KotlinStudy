package com.example.ch6_view

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

//코드에서 액티비티 화면구성
class Test1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //문자열을 출력하기 위한 뷰를 준비
        val name = TextView(this).apply {
            typeface = Typeface.DEFAULT_BOLD
            text = "Lake loves"
        }

        //이미지 출력하기
        val image = ImageView(this).also {
            it.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.test))
        }

        //문자열 출력하기
        val address = TextView(this).apply {
            typeface = Typeface.DEFAULT_BOLD
            text = "Lake loves,AB,캐나다"
        }

        //화면을 출력하기 위한 3개의 뷰
        //3개를 묶어서 계층으로 만들고, 상위 계층을 출력시킨다
        val layout = LinearLayout(this).apply { //다른 뷰를 자신에게 add 시키기 위한 뷰
            orientation = LinearLayout.VERTICAL //나열 방향
            gravity = Gravity.CENTER
            addView(name, WRAP_CONTENT, WRAP_CONTENT)
            addView(image, WRAP_CONTENT, WRAP_CONTENT)
            addView(address, WRAP_CONTENT, WRAP_CONTENT)
        }
        //화면 출력 뷰 계층의 root만 출력시키면 그 하위에 추가된 모든 뷰가 화면에 보인다
        setContentView(layout)
    }
}