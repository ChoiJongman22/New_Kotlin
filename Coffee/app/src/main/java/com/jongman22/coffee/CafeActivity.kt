package com.jongman22.coffee

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jongman22.coffee.databinding.ActivityCafeBinding

class CafeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCafeBinding
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCafeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.order.setOnClickListener {
            it.setBackgroundColor(R.color.purple_500)
            val intent= Intent(this,OrderActivity::class.java)
            startActivity(intent)
        }
    }
}