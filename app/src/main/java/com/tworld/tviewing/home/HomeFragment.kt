package com.tworld.tviewing.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tviewing.databinding.FragmentHomeBinding
import com.tworld.tviewing.youtube.RetrofitApi
import com.tworld.tviewing.youtube.YoutubeResponse
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("TAG", "HomeFragment")
        val service = RetrofitApi.youtubeService
        service.getYoutubeVideo(apiKey = "AIzaSyB-hi0gpZmfY5A0fv_wOVf_q6l1L0N5Jz4")
            .enqueue(object : retrofit2.Callback<YoutubeResponse> {
                override fun onResponse(
                    call: Call<YoutubeResponse>,
                    response: Response<YoutubeResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.items.let {
                            //예제
                            binding.homeText.text = it!!.get(1).snippet.title
                        }
                    }
                }

                override fun onFailure(call: Call<YoutubeResponse>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })
        return binding.root
    }
}