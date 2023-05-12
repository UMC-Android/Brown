package com.example.umcch6

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerReAdapter(var images:ArrayList<Int>): RecyclerView.Adapter<ViewPagerReAdapter.PagerViewHolder>() {
    inner class PagerViewHolder(parent: ViewGroup):RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate((R.layout.view_pager_re),parent,false)){
        val img = itemView.findViewById<ImageView>(R.id.vp_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.img.setImageResource(images[position])
    }

    override fun getItemCount(): Int = images.size
}