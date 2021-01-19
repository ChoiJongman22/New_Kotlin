package com.example.idmemolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.idmemolist.databinding.ActivityCategoryEditBinding

class CategoryEdit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding = ActivityCategoryEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homebutton.setOnClickListener {
            val intent = Intent(this, Category::class.java)
            startActivity(intent)
        }
    }
}