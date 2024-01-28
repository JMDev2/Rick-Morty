package com.ekenya.rnd.dashboard.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.dashboard.databinding.FragmentEpisodesBinding
import com.ekenya.rnd.dashboard.databinding.FragmentLocationsBinding


class locationsFragment : BaseDaggerFragment() {
   private lateinit var binding: FragmentLocationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }


}