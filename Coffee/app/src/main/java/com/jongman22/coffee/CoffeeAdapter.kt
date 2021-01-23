package com.jongman22.coffee

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jongman22.coffee.databinding.CoffeeItemBinding

class CoffeeAdapter(val coffeeList: CoffeeData) :
    RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {
    inner class CoffeeViewHolder(val binding: CoffeeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coffee_item, parent, false)
        return CoffeeViewHolder(CoffeeItemBinding.bind(view))

    }

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        holder.binding.coffeeItem.text = coffeeList.coffeeList[position].coffee
        holder.binding.coffeeItem.setOnClickListener {
            val intent = Intent(holder.itemView?.context, CustomActivity::class.java)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return coffeeList.coffeeList.size
    }
}

class Data(val coffee: String?)

class CoffeeData() {
    val coffeeList = ArrayList<Data>()
    fun coffeeadd(coffee: Data) {
        coffeeList.add(coffee)
    }
}