package com.ekenya.rnd.dashboard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.dashboard.databinding.FragmentDetailsBinding
import com.ekenya.rnd.dashboard.models.characters.Result
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.TimeZone

class DetailsFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentDetailsBinding

  //  private val args: DetailsFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        receiveCharacterItems()

    }

    private fun receiveCharacterItems() {

       val char = requireArguments().getParcelable<Result>("characterItem")!!
            binding.status.text = char.status
            binding.species.text = char.species
            binding.type.text = char.type
            binding.gender.text = char.gender
            binding.tvName.text = char.name
            binding.tvDate.text = char.created
            Picasso.get().load(char.image).into(binding.characterImage)


        val timestamp = char.created // Replace with the actual timestamp from the chat message
        val formattedTime = timestamp?.let { formatTimestampToHHMM(it) }
        binding.tvDate.text = formattedTime

    }

    //format the time stamp
    fun formatTimestampToHHMM(timestamp: String): String {
        val inputFormat = SimpleDateFormat("yyyy")
        val outputFormat = SimpleDateFormat("HH:mm")

        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        try {
            val date = inputFormat.parse(timestamp)
            return inputFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }


}