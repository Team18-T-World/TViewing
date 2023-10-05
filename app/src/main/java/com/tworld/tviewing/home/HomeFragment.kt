package com.tworld.tviewing.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tviewing.BuildConfig
import com.example.tviewing.R
import com.example.tviewing.databinding.FragmentHomeBinding
import com.tworld.tviewing.data.MyVideoItems
import com.tworld.tviewing.youtube.RetrofitApi
import com.tworld.tviewing.youtube.YoutubeResponse
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private var videoList: ArrayList<MyVideoItems> = ArrayList()
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

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
        service.getYoutubeVideo(apiKey = BuildConfig.API_KEY)
            .enqueue(object : retrofit2.Callback<YoutubeResponse> {
                override fun onResponse(
                    call: Call<YoutubeResponse>,
                    response: Response<YoutubeResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.items?.forEach { item ->
                            val id = item.id
                            val title = item.snippet.title
                            val content = item.snippet.description
                            val thumbnails = item.snippet.thumbnails.default.url
                            videoList.add(MyVideoItems(id, title, thumbnails, content, false)
                            )
                        }
                    }

                    val adapter = HomeAdapter(videoList)
                    binding.homeRecyclerView.adapter = adapter
                    binding.homeRecyclerView.layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        true
                    )

                }

                override fun onFailure(call: Call<YoutubeResponse>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })

        return binding.root
    }
}