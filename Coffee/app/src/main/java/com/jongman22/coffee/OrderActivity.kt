package com.jongman22.coffee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jongman22.coffee.databinding.ActivityOrderBinding

class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fun Coffee(fakeNumber: Int, coffee: CoffeeData = CoffeeData()): CoffeeData {
            for (i in 0 until fakeNumber) {
                coffee.coffeeadd(
                    Data("" + i + "번째 커피")
                )
            }
            return coffee
        }
        with(binding.recyclerview){
            this.adapter=CoffeeAdapter(
                coffeeList =Coffee(10)
            )
            this.layoutManager=LinearLayoutManager(this@OrderActivity)
        }
    }
}