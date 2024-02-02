package com.ekenya.rnd.dashboard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.utils.Status
import com.ekenya.rnd.dashboard.adapter.CharacterAdapter
import com.ekenya.rnd.dashboard.adapter.EpisodeAdapter
import com.ekenya.rnd.dashboard.databinding.FragmentEpisodesBinding
import javax.inject.Inject

class EpisodesFragment : BaseDaggerFragment() {
   private lateinit var binding: FragmentEpisodesBinding
    private lateinit var episodeAdapter: EpisodeAdapter

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEpisodes()

    }

    private fun setRecyclerView() {
        binding.episodesRecyclerview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = episodeAdapter
        }
    }


    private fun observeEpisodes(){
        viewModel.observeEpisodesLiveData().observe(
            viewLifecycleOwner
        ){ episodes ->
            when(episodes.status){
                Status.SUCCESS -> {
                    val episode = episodes.data?.results
                    episode?.let{
                        episodeAdapter = EpisodeAdapter(it)
                        setRecyclerView()
                    }
                }
                Status.LOADING ->{

            }
                Status.ERROR ->{


                }                }

        }

    }





}