package com.tworld.tviewing.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tviewing.R
import com.example.tviewing.databinding.HomeVideoItemBinding

class HomeAdapter(val videoList: ArrayList<VideoItems>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    fun interface OnItemClickListner {
        fun onItemClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: OnItemClickListner

    fun setOnItemclickListner(onItemClickListner: OnItemClickListner) {
        itemClickListener = onItemClickListner
    }

    inner class HomeViewHolder(val binding: HomeVideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VideoItems) {
            //썸네일 적용
            Glide.with(itemView).load(item.thumbnails)
//                .placeholder(R.drawable.img_loding) // 이미지 로딩 중 사진
//                .error(R.drawable.img_no) // 이미지를 불러오지 못했을 때 사진
                .into(binding.homeVideoImage)
            binding.homeVideoProfile.setImageResource(R.drawable.ic_launcher_background)
            binding.homeVideoTitle.text = item.title
            binding.homeVideoChannelTitle.text = item.title

            //아이템을 클릭하면 디테일 페이지로 이동
            binding.itemFrame.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION && ::itemClickListener.isInitialized)
                    itemClickListener.onItemClick(binding.itemFrame, pos)
            }
            // 어댑터 클릭하면 디테일 페이지로 이동

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(HomeVideoItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(videoList[position])
    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}