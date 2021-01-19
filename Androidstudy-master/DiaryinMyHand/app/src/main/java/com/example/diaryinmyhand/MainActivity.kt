package com.example.diaryinmyhand

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CalendarView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.example.diaryinmyhand.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.Settings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.Calendar.setOnClickListener {
            val intent1 = Intent(this, CalendarActivity::class.java)
            startActivity(intent1)
        }

        binding.Plus.setOnClickListener {
            val intent2 = Intent(this, DiaryWriting::class.java)
            startActivity(intent2)
        }

    }
}

enum class ImageType{
    CAMERA,GALLERY
}