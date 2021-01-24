package com.example.diaryinmyhand

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DiaryAdapter (val context:Context, val diaryList: List<Data>)
    :RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.diaryitem,parent,false)
        return DiaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder?.bind(diaryList[position])
    }

    override fun getItemCount(): Int {
        return diaryList.size
    }

    inner class DiaryViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView!!) {
        val nameTv = itemView?.findViewById<TextView>(R.id.itemName)
        val numberTv = itemView?.findViewById<TextView>(R.id.itemNumber)
        val contentTv = itemView?.findViewById<TextView>(R.id.itemContent)

        fun bind(data:Data){
            nameTv?.text=data.dataTitle
            numberTv?.text=data.dataImage.toString()
            contentTv?.text=data.dataContent
        }
    }

}
