package com.example.ch18_network

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ch18_network.databinding.ItemRetrofitBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewHolder(val binding: ItemRetrofitBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val context: Context, val datas: List<User>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemRetrofitBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding
        val user = datas?.get(position)
        //서버 데이터 화면에 출력
        binding.id.text=user?.id
        binding.firstNameView.text=user?.firstName
        binding.lastNameView.text=user?.lastName

        //서버 데이터를 avatar 는 이미지 다운로드 url
        //이 url 다시 서버 네트워킹
        user?.avatar?.let {
            val avatarImageCall = (context.applicationContext as MyApplication).networkService.getAvatarImage(it)
            avatarImageCall.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            //서버 이미지 화면 출력
                            val bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())
                            binding.avatarView.setImageBitmap(bitmap)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    call.cancel()
                }
            })
        }



    }

}
