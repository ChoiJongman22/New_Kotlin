package com.jongman22.basicstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jongman22.basicstudy.databinding.ActivityRecommendationRecyclerBinding

class RecommendationRecycler : AppCompatActivity() {
    private lateinit var binding:ActivityRecommendationRecyclerBinding
    private val adapter=RecommendationAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRecommendationRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dramaRecycler.layoutManager=LinearLayoutManager(this)
        binding.dramaRecycler.adapter=adapter
        adapter.setItem(SampleData().getItems())
    }
}