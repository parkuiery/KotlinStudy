package com.example.ch11_androidx_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Test2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)
    }
}