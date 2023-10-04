package com.tworld.tviewing.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tviewing.R
import com.example.tviewing.databinding.FragmentHomeBinding
import com.tworld.tviewing.videoDetail.VideoDetailFragment
import com.tworld.tviewing.youtube.RetrofitApi
import com.tworld.tviewing.youtube.YoutubeResponse
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {

    private var videoList: ArrayList<VideoItems> = ArrayList()
    private val adapter: HomeAdapter by lazy { HomeAdapter(videoList) }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainMenuAdapter: MainMenuAdapter
    private lateinit var subMenuAdapter: SubMenuAdapter
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
                        response.body()?.items?.forEach { item ->
                            val id = item.id
                            val title = item.snippet.title
                            val content = item.snippet.description
                            val thumbnails = item.snippet.thumbnails.default.url
                            videoList.add(
                                VideoItems(
                                    id,
                                    thumbnails,
                                    title,
                                    content,
                                    false
                                )
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<YoutubeResponse>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }
            })
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        //어댑터 연결
        mainMenuAdapter = MainMenuAdapter()
        // MenuItem 데이터 클래스로 리스트 생성
        mainMenuAdapter.mainMenuList.addAll(
            listOf(
                MenuItem("최다 조회"),
                MenuItem("장르"),
                MenuItem("카테고리")
            )
        )
        binding.homeMainMenuList.adapter = mainMenuAdapter
        binding.homeMainMenuList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        mainMenuAdapter.setOnItemclickListner { _, position -> //클릭한 메뉴의 isClicked를 true로 변경
            //클릭한 메뉴를 제외한 나머지 메뉴의 isClicked를 false로 변경
            for (i in mainMenuAdapter.mainMenuList.indices) {
                mainMenuAdapter.mainMenuList[i].isClicked = i == position
                mainMenuAdapter.notifyItemRangeChanged(0, mainMenuAdapter.itemCount)
            }
            //서브메뉴 어댑터 연결
            subMenuAdapter = SubMenuAdapter()
            //mainMenu가 클릭하면 해당하는 서브메뉴가 출력
            when (position) {
                0 -> subMenuAdapter.subMenuList.addAll(
                    listOf(
                        MenuItem("최다 조회"),
                        MenuItem("최다 좋아요"),
                        MenuItem("최다 댓글"),
                        MenuItem("최다 공유")
                    )
                )

                1 -> subMenuAdapter.subMenuList.addAll(
                    listOf(
                        MenuItem("액션"),
                        MenuItem("코미디"),
                        MenuItem("로맨스"),
                        MenuItem("스릴러"),
                        MenuItem("공포"),
                        MenuItem("판타지"),
                        MenuItem("SF"),
                        MenuItem("드라마"),
                        MenuItem("다큐멘터리"),
                        MenuItem("애니메이션"),
                        MenuItem("뮤지컬"),
                        MenuItem("기타")
                    )
                )

                2 -> subMenuAdapter.subMenuList.addAll(
                    listOf(
                        MenuItem("영화"),
                        MenuItem("드라마"),
                        MenuItem("예능"),
                        MenuItem("뮤직비디오"),
                        MenuItem("애니메이션"),
                        MenuItem("기타")
                    )
                )
            }
            subMenuAdapter.setOnItemclickListner { _, location -> //클릭한 메뉴의 isClicked를 true로 변경
                //클릭한 메뉴를 제외한 나머지 메뉴의 isClicked를 false로 변경
                for (i in subMenuAdapter.subMenuList.indices) {
                    subMenuAdapter.subMenuList[i].isClicked = i == location
                    subMenuAdapter.notifyItemRangeChanged(0, subMenuAdapter.itemCount)
                }
                //서브 메뉴를 클릭할 때 홈 메뉴 동영상 출력
                Log.d("###", videoList.toString())
                binding.homeVideoList.adapter = adapter
            }
            binding.homeVideoList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.homeSubMenuList.adapter = subMenuAdapter
            binding.homeSubMenuList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        adapter.setOnItemclickListner { _, position ->
            val bundle = Bundle()
            val detailFragment = VideoDetailFragment()
            bundle.putString("title", videoList[position].title)
            bundle.putString("content", videoList[position].content)
            detailFragment.arguments = bundle

            val fragmentManager = (view?.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.home_fragment, detailFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}