package com.tworld.tviewing.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tviewing.databinding.SearchItemBinding


class SearchAdaptor(private val mContext: Context) : RecyclerView.Adapter<SearchAdaptor.Holder>() {

    var datalist = ArrayList<SearchResult>()

    inner class Holder(val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val image = binding.imgThumbnail
        val title = binding.textTitle
        val layout: ConstraintLayout = binding.searchItem

        init {
            image.setOnClickListener(this)
            layout.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = datalist[position]

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