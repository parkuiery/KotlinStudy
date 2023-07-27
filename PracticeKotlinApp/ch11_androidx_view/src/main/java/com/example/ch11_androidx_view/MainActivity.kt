package com.example.ch11_androidx_view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.ch11_androidx_view.databinding.ActivityMainBinding

//Toolbar Manu Test
//Toolbar 을 이용하려면 Activity의 테마 설정으로 Activity 구성요소만 ActionBar 못나오게 해야한다
//values /them.xml 이미 되어 있다
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Toolbar 는 ActionBar 의 대체이다 ActionBar의 구성요소가 그래도 Toolbar에 나온다
        //단 아래의 한줄은 등록해야
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menu xml을 inflate 시켜서
        menuInflater.inflate(R.menu.menu_main, menu)

        //위의 코드만으로 화면에 메뉴는 나오지만
        //우리는 SearchView를 사용했다 SearchView 객체를 얻어서 제어(최소한 이벤트)
        //SearchView 가 적용한 메뉴(MenuItem)을 먼저 얻고 그 안에 적용된 ActionView 객체 획득
        val menuItem = menu?.findItem(R.id.menu3)
        val searchView = menuItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                //유저가 검색어 한자한자 입력시마다 매개변수가 그 순간 입력된 검색어
                //추천단어 서비스
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                //searchView 에 입력하기 위해서 올라온 키보드의 검색버튼 을 유저가 누른순간
                //검색 업무 진행
                Toast.makeText(this@MainActivity,query,Toast.LENGTH_SHORT).show()
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
    //메뉴 이벤트 함수
    //매개변수 그 순간 이벤트가 발생한 메뉴 객체
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu1 -> Log.d("kkang","menu1 click")
            R.id.menu2 -> Log.d("kkang","menu2 clcik")
        }
        return super.onOptionsItemSelected(item)
    }
}