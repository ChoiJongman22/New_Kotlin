package com.jongman22.basicstudy

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jongman22.basicstudy.databinding.RecommendationListBinding

class RecommendationAdapter:RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder>() {
    private var items= ArrayList<DramaList>()
    inner class RecommendationViewHolder(val binding:RecommendationListBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        val view: View =LayoutInflater.from(parent.context).inflate(R.layout.recommendation_list,parent,false)
        return RecommendationViewHolder(RecommendationListBinding.bind(view))
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        val item=items[position]
        Glide.with(holder.itemView.context)
            .load(item.url)
            .centerCrop()
            .into(holder.binding.dramaImage)
        holder.binding.dramaTitle.text=item.name
        holder.binding.dramaProd.text=item.producer
        holder.binding.dramaBroad.text=item.broadcast
        holder.binding.dramaDetail.text=item.detail

        when(position){
            0->{
                holder.binding.dramaImage.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://namu.wiki/w/%EB%AF%B8%EC%8A%A4%ED%84%B0%20%EC%85%98%EC%83%A4%EC%9D%B8"))
                    ContextCompat.startActivity(holder.itemView.context,intent,null)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItem(items:ArrayList<DramaList>){
        this.items=items
    }
}