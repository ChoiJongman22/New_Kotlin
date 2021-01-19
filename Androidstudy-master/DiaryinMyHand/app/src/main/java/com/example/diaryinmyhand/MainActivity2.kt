package com.example.diaryinmyhand

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.diaryinmyhand.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
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