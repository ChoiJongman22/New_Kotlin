package com.jongman22.basicstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jongman22.basicstudy.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    private val Model=SecondViewModel(application)
    private lateinit var binding:ActivityMain4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner=this
        binding.viewModel=Model
    }
}