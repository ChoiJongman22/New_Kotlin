package com.example.idmemolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.idmemolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var lock = true//잠금 상태 여부 확인
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.Reset.setOnClickListener {
            val intent=Intent(this@MainActivity,AppPassWordMain::class.java)
        }
        binding.textView2.setOnClickListener {
            val intent=Intent(this@MainActivity,UserpwCopy::class.java)
            startActivity(intent)
        }
    }


}