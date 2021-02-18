package com.jongman22.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ContentsAdapter:RecyclerView.Adapter<ContentsAdapter.ContentsViewHolder>() {
    private var items=ArrayList<ContentsList>()
    inner class ContentsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var contents_View: ImageView
        var contents_Name: TextView
        var contents_Detail: TextView

        init{
            contents_View=itemView.findViewById(R.id.contents_image)
            contents_Name=itemView.findViewById(R.id.contents_name)
            contents_Detail=itemView.findViewById(R.id.contents_detail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentsViewHolder {
        val itemView:View = LayoutInflater.from(parent.context).inflate(R.layout.contents_item, parent, false)
        return ContentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContentsViewHolder, position: Int) {
        val item=items[position]
        Glide.with(holder.itemView.context)
            .load(item.getUrl())
            .centerCrop()
            .into(holder.contents_View)

        holder.contents_Name.text=item.getName()
        holder.contents_Detail.text=item.getDetail()

    }

    override fun getItemCount(): Int {
        return items.size
    }
}


class ContentsList(val url: String?, val name: String?, val detail: String?){

    @JvmName("getUrl1")
    fun getUrl(): String? {
        return this.url
    }

    @JvmName("getName1")
    fun getName(): String? {
        return this.name
    }

    @JvmName("getDetail1")
    fun getDetail(): String? {
        return this.detail
    }
}