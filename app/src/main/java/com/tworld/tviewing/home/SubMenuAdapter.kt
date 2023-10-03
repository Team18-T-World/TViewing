package com.tworld.tviewing.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tviewing.databinding.SubMenuItemBinding

// 2차 리사이클러뷰를 연결해주는 어댑터
class SubMenuAdapter :
    RecyclerView.Adapter<SubMenuAdapter.SubMenuViewHolder>() {

    // 2차 리사이클러뷰에 들어갈 데이터 리스트
    var subMenuList = ArrayList<MenuItem>()

    fun interface OnItemClickListner {
        fun onItemClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: OnItemClickListner

    fun setOnItemclickListner(onItemClickListner: OnItemClickListner) {
        itemClickListener = onItemClickListner
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubMenuViewHolder {
        return SubMenuViewHolder(SubMenuItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SubMenuViewHolder, position: Int) {
        holder.bind(subMenuList[position])
    }

    override fun getItemCount(): Int {
        return subMenuList.size
    }

    inner class SubMenuViewHolder(private val binding: SubMenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.cardView.setOnClickListener {

                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION && ::itemClickListener.isInitialized)
                    itemClickListener.onItemClick(binding.cardView, pos)
            }
        }

        fun bind(item: MenuItem) {
            binding.textView2.text = item.text
            if (item.isClicked) {
                binding.textView2.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                binding.textView2.setTextColor(Color.parseColor("#888787"))
            }
        }
    }
}