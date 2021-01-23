package com.jongman22.study1

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jongman22.study1.databinding.NameListBinding

class NameAdapter(private val item:List<NameData>):RecyclerView.Adapter<NameAdapter.NameViewHolder>() {
    inner class NameViewHolder(var binding:NameListBinding):RecyclerView.ViewHolder(binding.root){}

    interface OnItemClickListener{
        fun onClick(v: View, position: Int)
    }

    private lateinit var itemClickListener: OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener){
        this.itemClickListener=itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.name_list,parent, false)
        return NameViewHolder(NameListBinding.bind(view))
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val a=item[position]
        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it,position)
        }
        holder.binding.apply{
            firstName.text=a.FirstName
            lastName.text=a.LastName
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }
}