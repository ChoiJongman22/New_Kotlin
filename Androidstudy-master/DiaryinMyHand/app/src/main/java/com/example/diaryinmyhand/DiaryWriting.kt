package com.example.diaryinmyhand

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import com.example.diaryinmyhand.databinding.ActivityDiaryWritingBinding
import com.example.diaryinmyhand.databinding.ImageItemBinding
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.util.ArrayList
import kotlin.concurrent.timer

class DiaryWriting : AppCompatActivity() {

    private lateinit var binding: ActivityDiaryWritingBinding


    private val request_read_external=1000 // 이 코드로 (외부 저장소 접근에 대한 사용자의 요청)을 인식함
    fun getAllPhotos(){
        // 모든 사진 정보 가져오기
        // contentResolver객체는 프로바이더를 사용하고 데이터를 가져온다.
        // <query메소드를 통해 무엇을 가져올지, 정렬은 어떻게할지 등 결정>
        // 프로바이더: 앱 과 앱 사이에 데이터를 공유하는 환경을 만듬
        // <앱과 앱 사이에서 데이터를 공유하는 역할을 하는 컴포넌트임>
        // cursor는 가져온 데이터를 관리하는 객체를 가리킴
        val cursor=contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // 미디어 관련 외부 데이터를 가져옴
            null, // 어떤 항목을 가져올 것인지 지정 (null-> 다 가져옴)
            null, // 데이터를 가져올 조건 지정 (null-> 사용 안 함)
            null, // 마찬가지로 조건 지정 (null-> 사용 안 함)
            MediaStore.Images.ImageColumns.DATE_TAKEN+" DESC") // 사진이 찍힌 날짜의 내림차순으로 정렬
        // 프레그먼트를 요소로 하는 프레그먼트 리스트
        val fragments=ArrayList<Fragment>()
        if(cursor !=null){
            // moveToNext로 데이터를 하나씩 이동하면서 처리함 , 없으면 null이 오면서 while문을 빠져 나옴
            while(cursor.moveToNext()){
                // (현재 데이터에 해당하는 이미지)의 uri가져오기
                val uri=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                // uri를 보내서 프래그먼트 만들고 프래그먼트 가져와서 리스트에 저장
                fragments.add(photoFragment.newInstance(uri))
            }
            cursor.close() // 관리 객체 해제
            // 어댑터(아이템을 담는 객체: 아이템은 무엇이든 될 수 있다) 생성
            val adapter= ImagerAdpater(supportFragmentManager)
            val viewPager= findViewById<ViewPager>(R.id.Image_write)
            adapter.updateFragments(fragments) // fragments리스트를 어댑터에 넘김
            viewPager.adapter=adapter // 뷰페이저에 어댑터 연결
            // 뷰페이저는 현재 인덱스에 맞는 페이지(프래그먼트)를 화면에 나타낸다.
            // 3초마다 자동 슬라이드
            timer(period=3000){
                runOnUiThread{
                    // 현재 페이지가 마지막 페이지일 때
                    if(viewPager.currentItem == adapter.count-1)
                        viewPager.currentItem=0
                    // 아니면 페이지 인덱스를 1씩 올림
                    else
                        viewPager.currentItem=viewPager.currentItem+1
                }
            }
        }
    }
    // 권한 요청 클래스
    inner class user_req(main:DiaryWriting){
        init{
            ActivityCompat.requestPermissions(main,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),request_read_external)
        }
    }
    // 사용자가 권한 요청<허용,비허용>한 후에 이 메소드가 호출됨
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            // (외부 저장소 접근에 대한 사용자의 요청)일 때
            request_read_external->{
                // 요청이 허용일 때
                if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    getAllPhotos()
                // 요청이 비허용일 때
                else{
                    toast("권한 거부 됨")
                    finish() }
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryWritingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)





        binding.Back.setOnClickListener {
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }

        binding.Ok.setOnClickListener {
            val intent1 = Intent(this,MainActivity2::class.java)
            startActivity(intent1)
        }
        binding.Picture.setOnClickListener{
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){  // <PERMISSION_DENIED가 반환됨>
                // 이전에 사용자가 앱 권한을 허용하지 않았을 때 -> 왜 허용해야되는지 알람을 띄움
                // shouldShowRequestPermissionRationale메소드는 이전에 사용자가 앱 권한을 허용하지 않았을 때 ture를 반환함
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)){
                    alert("사진 정보를 얻으려면 외부 저장소 권한이 필수로 필요합니다",""){
                        yesButton{
                            // 권한 요청
                            user_req(this@DiaryWriting)
                        }
                        noButton { finish() }
                    }.show()
                }
                // 앱 처음 실행했을 때
                else
                    user_req(this) // 권한 요청
            }
            // 앱에 권한이 허용됨
            else
                getAllPhotos()

        }


    }


}