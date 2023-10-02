package com.tworld.tviewing.search

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tviewing.databinding.SearchItemBinding


class SearchAdaptor(private val mContext: Context) : RecyclerView.Adapter<SearchAdaptor.Holder>() {

    var datalist = ArrayList<SearchResult>()

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    var itemClickListener: OnItemClickListener? = null

    inner

    class Holder(val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.imgThumbnail
        val title = binding.textTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = datalist[position]
        Log.d("onitem", "onBindViewHolder $position")

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(position)
            Log.d("onitem", "setOnClickListener $position")
        }

        Glide.with(mContext) // mContext??
            .load(data.url) // 불러올 이미지 url
            .into(holder.image) // 이미지를 넣을 뷰

        holder.title.text = data.title // 타이틀 텍스트 넣기

    }

    override fun getItemCount(): Int {
        return datalist.size // 데이터 길이 반환
    }

    fun clearItem() {
        datalist.clear() // 데이터리스트 초기화
        notifyDataSetChanged() // 리사이클러뷰 업데이트
    }
}