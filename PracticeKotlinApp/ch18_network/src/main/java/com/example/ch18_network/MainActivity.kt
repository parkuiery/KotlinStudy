package com.example.ch18_network

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch18_network.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkService = (applicationContext as MyApplication).networkService

        //인터페이스의 함수를 호출해서 네트워킹이 가능한 call 객체 획득
        val call = networkService.getUserList("1")
        //call의 enqueue 하는 순간 네트워킹이 되며 콜백 등록하면 된다
        call.enqueue(object : Callback<UserList> {

            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                //서버 결과 데이터 획득
                val userList = response.body()
                //recyclerview 화면 출력
                binding.recyclerView.adapter = MyAdapter(this@MainActivity, userList?.data)
                binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
                )
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {

            }
        })

    }
}