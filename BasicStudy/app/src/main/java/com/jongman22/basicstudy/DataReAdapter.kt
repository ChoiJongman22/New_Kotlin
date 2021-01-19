package com.jongman22.basicstudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jongman22.basicstudy.databinding.ListNameBinding

class DataReAdapter(private val itemList:List<User>):RecyclerView.Adapter<DataReAdapter.DataViewHoder>() {
    inner class DataViewHoder(var binding: ListNameBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHoder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.list_name,parent,false)
        return DataViewHoder(ListNameBinding.bind(view))
    }

    override fun onBindViewHolder(holder: DataViewHoder, position: Int) {
        val item =itemList[position]
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
        holder.apply{
            binding.firstName.text=item.firstName
            binding.lastName.text=item.lastName
        }
    }

    override fun getItemCount(): Int {
        return itemList.size

    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    private lateinit var itemClickListener : OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}