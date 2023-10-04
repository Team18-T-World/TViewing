package com.tworld.tviewing.myVideo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tviewing.databinding.FragmentMyVideoItemBinding

class MyPageAdapter(private val onClick: (MyPageEntity) -> Unit) :
    RecyclerView.Adapter<MyPageAdapter.Holder>() {
    private var mypage_list: List<MyPageEntity> = listOf()

    class Holder(val binding: FragmentMyVideoItemBinding, val onClick: (MyPageEntity) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun setBind(mypage: MyPageEntity) {
            mypage.let {
                Glide.with(binding.thumnailImageView).load(it.thumbnail).override(600)
                    .into(binding.thumnailImageView)


                binding.titleText.text = it.title

                binding.thumnailImageView.setOnClickListener {
                    onClick(mypage)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            FragmentMyVideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, onClick)
    }

    override fun getItemCount(): Int {
        return mypage_list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val bind = mypage_list.get(position)

        holder.setBind(bind)
    }

    fun setMypage(mypage: List<MyPageEntity>) {
        this.mypage_list = mypage
        notifyDataSetChanged()
    }
}