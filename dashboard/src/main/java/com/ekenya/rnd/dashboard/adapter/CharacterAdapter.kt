package com.ekenya.rnd.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.dashboard.databinding.CharacterLayoutBinding
import com.squareup.picasso.Picasso
import com.ekenya.rnd.dashboard.models.characters.Result
import java.text.SimpleDateFormat
import java.util.TimeZone

class CharacterAdapter(private val characters: List<Result>):
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(){

    var onItemClick: ((Result) -> Unit) = {}
    inner class CharacterViewHolder(val binding: CharacterLayoutBinding, val context: Context): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            character : Result
        ){
            binding.characterName.text = character.name
            Picasso.get().load(character.image).into(binding.characterImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            CharacterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])

        holder.itemView.setOnClickListener {
            onItemClick.invoke(characters[position])
        }
    }
    override fun getItemCount(): Int = characters.size


}