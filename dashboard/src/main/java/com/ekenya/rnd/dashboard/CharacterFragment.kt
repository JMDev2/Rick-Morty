package com.ekenya.rnd.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.utils.Status
import com.ekenya.rnd.dashboard.adapter.CharacterAdapter
import com.ekenya.rnd.dashboard.databinding.FragmentCharacterBinding
import com.ekenya.rnd.dashboard.ui.HomeViewModel
import javax.inject.Inject


class CharacterFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentCharacterBinding
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
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeCharacters()

    }

    private fun setRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
            adapter = characterAdapter
        }
    }


    private fun observeCharacters() {
        viewModel.observeCharacterLiveData().observe(
            viewLifecycleOwner
        ) { character ->
            when (character.status) {
                Status.SUCCESS -> {
                    val char = character.data?.results
                    Log.d("check", "Characters: ${character.data}")

                    char?.let {
                        characterAdapter = CharacterAdapter(it)
                        setRecyclerView()
                    }
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }

            }

        }


    }
}