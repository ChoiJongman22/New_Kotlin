package com.jongman22.coffee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jongman22.coffee.databinding.ActivityCafeBinding

class CafeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCafeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCafeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.order.setOnClickListener {
            val intent= Intent(this,OrderActivity::class.java)
            startActivity(intent)
        }
    }
}