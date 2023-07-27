package com.example.ch11_androidx_view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ch11_androidx_view.databinding.ActivityTest1Binding
import com.example.ch11_androidx_view.databinding.ItemBinding

class Test1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityTest1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //목록을 구성하기 위한 데이터 준비
        val data = mutableListOf<String>()
        for(i in 1 ..20){
            data.add("Item $i")
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = MyAdapter(data)
        binding.recyclerView.addItemDecoration(MyDecoration(this))
    }
}

//항목을 구성하기 위해 필요한 뷰 객체를 가지는 역할
class MyViewHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root)

//ViewHolder 가 가지는 뷰를 이용해서 각 항목을 완성(데이터 출력, 이벤트 등록)하는 역할
class MyAdapter(val data: MutableList<String>): RecyclerView.Adapter<MyViewHolder>(){
    //가장 먼저 호출되는 함수
    override fun getItemCount(): Int {
        return data.size
    }
    //viewHolder를 준비하기 위해서 자동 호출
    //항목이 100개라고 100번 호출되지 않는다 필요한 만큼난 호출한후 재사용해준다
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    //각각의 항목을 구성하기 위해서 자동호출, 몇번쨰 항목을 위해서 호출한것인지 두번째 매개변수
    //항목을 구성하기 위해서 필요한 viewHolder 객체 전달
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.itemData.text = data[position]
    }
}

//꾸미기 작업
class MyDecoration(val context: Context): RecyclerView.ItemDecoration(){
    //항목이 찍히기 전 최초에 한번 호출
    //항목이 찍히기 전에 무언가 드로잉 그 위에 항목이 찍힌다
    //Canvas 안드로이드 드로잉을 위한 모든 api 제공
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        //준비한 이미지 드로잉
        c.drawBitmap(BitmapFactory.decodeResource(context.resources,R.drawable.stadium),0f,0f,null)
    }
    //각가의 항목을 꾸미기 위해서 자동 호출
    //Rect - 항목이 화면에 찍히는 사각형 정보
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        //몇번쨰 항목떄문에 호출된 것인지, index 값 항목
        val index = parent.getChildAdapterPosition(view)+1 //1을 더한것은 단순 알고리즘 편의성

        if(index % 3 == 0)
            outRect.set(10,10,10,60)
        else
            outRect.set(10,10,10,0)
        view.setBackgroundColor(Color.LTGRAY)
        ViewCompat.setElevation(view,20.0f)
    }
}