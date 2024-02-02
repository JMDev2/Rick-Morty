package com.ekenya.rnd.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.dashboard.databinding.CharacterLayoutBinding
import com.ekenya.rnd.dashboard.databinding.EpisodesLayoutBinding
import com.ekenya.rnd.dashboard.models.episodes.Result

class EpisodeAdapter (private val episodes: List<Result>):
    RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

        inner class EpisodeViewHolder(val binding: EpisodesLayoutBinding,  val context: Context): RecyclerView.ViewHolder(binding.root){
            fun bind( result: Result){
                binding.episodeName.text = result.name
                binding.episodeTitle.text = result.episode
                binding.episodeAiredDate.text = result.air_date

            }

        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            EpisodesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context
        )
    }

    override fun onBindViewHolder(holder: EpisodeAdapter.EpisodeViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount(): Int = episodes.size

}