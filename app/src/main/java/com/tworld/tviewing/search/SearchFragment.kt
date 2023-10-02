package com.tworld.tviewing.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.tviewing.databinding.FragmentSearchBinding
import com.tworld.tviewing.search.data.SearchResponse
import com.tworld.tviewing.videoDetail.DetailActivity
import com.tworld.tviewing.youtube.RetrofitApi
import retrofit2.Call
import retrofit2.Response


class SearchFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentSearchBinding? = null
    private lateinit var adaptor: SearchAdaptor
    private lateinit var gridmanager: StaggeredGridLayoutManager
    private lateinit var mContext: Context
    private var datalist: ArrayList<SearchResult> = ArrayList()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        setupView()
        setupListeners()


        return binding.root
    }

    private fun setupView() {
        gridmanager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.search.layoutManager = gridmanager
        adaptor = SearchAdaptor(mContext)
        binding.search.adapter = adaptor
        binding.search.itemAnimator = null
        adaptor.datalist = datalist
        adaptor.notifyDataSetChanged()
        adaptor.itemClickListener = object : SearchAdaptor.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("onitem", "onitemclick 프래그먼트 $position")
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("url", datalist[position].url)
                intent.putExtra("title", datalist[position].title)
                context?.startActivity(intent)
            }
        }
    }

    private fun setupListeners() {
        binding.tvSearch.setOnClickListener {
            val keyWord = binding.etSearch.text.toString()
            if (keyWord.isNotEmpty()) {
                adaptor.clearItem()
                fetchImageResults(keyWord)
            }
        }
    }

    private fun fetchImageResults(keyWord: String) {
        val service = RetrofitApi.youtubeService
        service.getSearchService(
            apiKey = "your-api-key", q = keyWord
        ).enqueue(object : retrofit2.Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>, response: Response<SearchResponse>
            ) {
                response.body()?.pageInfo?.let { pageInfo ->
                    if (pageInfo.resultsPerPage.toInt() > 0) {
                        response.body()!!.items.forEach { items ->
                            val title = items.snippet.title
                            val url = items.snippet.thumbnails.medium.url
                            datalist.add(SearchResult(title, url))
                        }
                    } else {
                        Toast.makeText(activity, "결과 값이 없습니다", Toast.LENGTH_SHORT).show()
                    }

                }
                adaptor.datalist = datalist
                adaptor.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
            }

        })
    }


}