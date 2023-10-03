package com.tworld.tviewing.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tviewing.databinding.MainMenuItemBinding

class MainMenuAdapter :
    RecyclerView.Adapter<MainMenuAdapter.MainSubjectViewHolder>() {

    val mainMenuList = ArrayList<MenuItem>()

    fun interface OnItemClickListner {
        fun onItemClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: OnItemClickListner

    fun setOnItemclickListner(onItemClickListner: OnItemClickListner) {
        itemClickListener = onItemClickListner
    }

    inner class MainSubjectViewHolder(val binding: MainMenuItemBinding) :
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainSubjectViewHolder {
        return MainSubjectViewHolder(MainMenuItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MainSubjectViewHolder, position: Int) {
        holder.bind(mainMenuList[position])
    }

    override fun getItemCount(): Int {
        return mainMenuList.size
    }
}