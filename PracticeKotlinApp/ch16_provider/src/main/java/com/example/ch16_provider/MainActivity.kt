package com.example.ch16_provider

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.ch16_provider.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    //카메라에 공유하고자 하는 파일 경로
    lateinit var filePath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val galleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            //galleryResult

            //사진 사이즈를 줄여서 로딩해야 한다 OOM을 피하기 위해서
            val option = BitmapFactory.Options()
            option.inSampleSize = 10 //10분의 1로 줄여서 원본 이미지 해상도와 출력하고자 하는
            //사이즈를 비교해서 몇으로 줄일지 계산

            //gallery에서 사진 선택해서 되돌아오면 선택한 사진의 식별자만 url 형식으로 넣어논다
            //url이 gallery의 provider 이용 url이다
            //provider에게 요청해서 구체적으로 원하는 데이터르르 확들할 수 있다
            //사진 경로 해상도 등
            //선택한 사진을 읽어들이겠다면 Stream 자체를 제공한다
            var stream = contentResolver.openInputStream(it.data!!.data!!)
            //stream으로 넘어오는 데이터를 이미지 객체 aksemsek
            var bitmap = BitmapFactory.decodeResource(stream,null,option)
            stream?.close()
            bitmap?.let {
                //읽은게 있다면
                //화면 출력
                binding.userImageView.setImageBitmap(bitmap)
            }
        }

        binding.galleryButton.setOnClickListener {
            //gallery 뷰를 activity 실행 intent로
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/="
            galleryLauncher.launch(intent)
        }

        val cameraLaucher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            val option = BitmapFactory.Options()
            option.inSampleSize = 10
            //camera app 에서 우리의 파일에 write 한 상황이다 바로 파일에서 읽으면 된다
            val bitmap = BitmapFactory.decodeResource(filePath, option)
            bitmap?.let {
                //읽은게 있다면
                //화면 출력
                binding.userImageView.setImageBitmap(bitmap)
            }
        }

        binding.cameraButton.setOnClickListener {
            //카메라 앱에서 공유하기 위한 파일을 먼저 준비
            //파일명에 시간날짜를
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            //파일이 저장될 디렉토리
            val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            //위의 디렉토리에 날짜시간포함 중복되지 않게 파일을 준비
            val file = File.createTempFile(//알아서 파일명 중복되지 않게
                "JPEG_${timeStamp}_",
                "jpg",
                dir
            )
            //이 파일에서 나중에 읽어야 함으로 멤버변수에 저장
            filePath = file.absolutePath//파일 경로

            //camera app 에 파일 공유하기 위한 준비.. uri 형식으로
            val uri = FileProvider.getUriForFile(
                this,
                "com.example.ch16_provider.fileprovider",file
            )
            //camera app 실행
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //file 정보를 extra data 로 전달 만약 전달하지 않으면 데이터 변환 방식
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)
            cameraLaucher.launch(intent)
        }
    }
}