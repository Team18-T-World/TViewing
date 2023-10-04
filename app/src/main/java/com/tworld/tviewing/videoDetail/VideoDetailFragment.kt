package com.tworld.tviewing.videoDetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.tviewing.R
import com.example.tviewing.databinding.FragmentVideoDetailBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.tworld.tviewing.MainActivity
import com.tworld.tviewing.data.MyVideoItems
import com.tworld.tviewing.myVideo.MyPageDatabase
import com.tworld.tviewing.myVideo.MyPageEntity

class VideoDetailFragment : Fragment() {

    private val binding by lazy { FragmentVideoDetailBinding.inflate(layoutInflater) }
    private lateinit var mContext: Context
    private lateinit var videoItems: MyVideoItems
    private lateinit var database: MyPageDatabase

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            database = MyPageDatabase.getInstance(requireContext())!!
            val id = it.getString("id") ?: ""
            val title = it.getString("title") ?: ""
            val thumbnail = it.getString("thumbnail")?:""
            videoItems = MyVideoItems(id, "", title, false)
            val recipeViewLinearLayout = binding.detailLinear
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.bottomMargin = 30
            val ypv = YouTubePlayerView(requireContext())
            ypv.layoutParams = params
            recipeViewLinearLayout.addView(ypv)
            lifecycle.addObserver(ypv)
            ypv.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(id, 0f)
                }
            })
            binding.detailTitle.text = title
            binding.detailContent.text = it.getString("content")

            binding.detailIsLike.setOnClickListener {
                binding.detailIsLike.setImageResource(R.drawable.ic_heart_fill)
                (mContext as MainActivity).addLikedItem(videoItems)
                database.contactDao().update(MyPageEntity(null, thumbnail, title))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.detailBackBtn.setOnClickListener {
            Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show()
            val fragmentManager = requireActivity().supportFragmentManager
            val mFragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            mFragmentTransaction.run {
                setCustomAnimations(
                    R.anim.transition_fade_in,
                    R.anim.transition_fade_out,
                    R.anim.transition_fade_in,
                    R.anim.transition_fade_out
                )
                addToBackStack(fragmentManager.backStackEntryCount.toString())
            }
            mFragmentTransaction.remove(this)
            mFragmentTransaction.commit()
            this.onDestroy()
            this.onDetach()
        }
        return binding.root
    }
}