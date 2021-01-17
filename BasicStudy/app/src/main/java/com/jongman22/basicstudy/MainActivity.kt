package com.jongman22.basicstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.jongman22.basicstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dramafragment:RecomFragment= RecomFragment()

        binding.show1.setOnClickListener {
            val fragmentManager: FragmentManager =supportFragmentManager
            val fragmentTransaction=fragmentManager.beginTransaction() //시작!
            fragmentTransaction.replace(R.id.linearLayout,dramafragment)//container부분과 fragmentOne을 바꾼다.
            fragmentTransaction.commit() //시간 될때 끝!
        }








    }
}