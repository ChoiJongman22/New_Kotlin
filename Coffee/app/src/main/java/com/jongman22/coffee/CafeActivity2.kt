package com.jongman22.coffee

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat.finishAffinity
import com.jongman22.coffee.databinding.ActivityCafe2Binding

class CafeActivity2 : AppCompatActivity() {
    private lateinit var binding:ActivityCafe2Binding
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCafe2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gift.setOnClickListener {
            it.setBackgroundColor(R.color.purple_500)
            val intent = Intent(this,GiftActivity::class.java)
            startActivity(intent)
        }
        binding.finish.setOnClickListener {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}