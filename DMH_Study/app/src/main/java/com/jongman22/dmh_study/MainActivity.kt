package com.jongman22.dmh_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.jongman22.dmh_study.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewPager: ViewPager
    private var isLastPage: Boolean = false
    private val images:IntArray= intArrayOf(
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter=CustomPagerAdapter(this,images)
        viewPager= findViewById<ViewPager>(R.id.viewPager)
        viewPager?.adapter=adapter



    }
}