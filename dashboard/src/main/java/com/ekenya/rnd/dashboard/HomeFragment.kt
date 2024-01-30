package com.ekenya.rnd.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.utils.Status
import com.ekenya.rnd.dashboard.adapter.CharacterAdapter
import com.ekenya.rnd.dashboard.databinding.FragmentHomeBinding
import com.ekenya.rnd.dashboard.ui.HomeViewModel
import javax.inject.Inject


class HomeFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var characterAdapter: CharacterAdapter

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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


  //  observeCharacters()

}







}