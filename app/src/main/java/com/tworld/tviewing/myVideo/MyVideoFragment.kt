package com.example.yourmediaapp_18.myVideo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tviewing.databinding.FragmentMyVideoBinding
import com.tworld.tviewing.myVideo.MyPageAdapter
import com.tworld.tviewing.myVideo.MyPageEntity
import com.tworld.tviewing.myVideo.MyPageViewModel


class MyVideoFragment : Fragment() {

    private var _binding: FragmentMyVideoBinding? = null
    private val binding get() = _binding!!

    private val mypageAdapter by lazy {
        MyPageAdapter { mypage -> adapterOnClick(mypage) }
    }

    lateinit var mypageViewModel : MyPageViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bookmarkRecyclerRecyclerView.adapter = mypageAdapter
        binding.bookmarkRecyclerRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        mypageViewModel = ViewModelProvider(this).get(MyPageViewModel::class.java)
        mypageViewModel.getAll().observe(requireActivity()) {
            mypage -> mypageAdapter.setMypage(mypage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun adapterOnClick(mypage: MyPageEntity) {
        mypageViewModel.delete(mypage)

    }


}