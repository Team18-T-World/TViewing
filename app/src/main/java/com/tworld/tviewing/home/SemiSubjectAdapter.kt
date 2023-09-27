package com.tworld.tviewing.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tviewing.databinding.MainSubjectItemBinding

// 2차 리사이클러뷰를 연결해주는 어댑터
class SemiSubjectAdapter : RecyclerView.Adapter<SemiSubjectAdapter.SemiSubjectViewHolder>() {

    // 2차 리사이클러뷰에 들어갈 데이터 리스트
    private var semiSubjectList = ArrayList<SemiSubject>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SemiSubjectViewHolder {

    }

    override fun onBindViewHolder(holder: SemiSubjectViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

    }

    class SemiSubjectViewHolder(private val binding: MainSubjectItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

        }
    }
}