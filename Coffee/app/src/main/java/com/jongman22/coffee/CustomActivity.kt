package com.jongman22.coffee

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jongman22.coffee.databinding.ActivityCustomBinding

class CustomActivity : AppCompatActivity() {
    private val Model=SecondViewModel(application)
    private lateinit var binding:ActivityCustomBinding
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner=this
        binding.viewModel=Model

        binding.check.setOnClickListener {
            it.setBackgroundColor(R.color.purple_500)
            val intent= Intent(this,CafeActivity2::class.java)
            startActivity(intent)
        }
    }
}