package com.ekenya.rnd.dashboard.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.utils.Status
import com.ekenya.rnd.common.utils.toast
import com.ekenya.rnd.dashboard.R
import com.ekenya.rnd.dashboard.adapter.CharacterAdapter
import com.ekenya.rnd.dashboard.databinding.FragmentLocationsBinding
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject


class LocationsFragment : BaseDaggerFragment() {
   private lateinit var binding: FragmentLocationsBinding

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
        binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeCharacters()

    }

    private fun onItemClick() {
        characterAdapter?.onItemClick = { char ->
            toast("click")
            toggleTopSheetVisibility()


        }
    }

    private fun toggleTopSheetVisibility() {
        val topSheet = view?.findViewById<View>(R.id.top_sheet)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)

        if (topSheet?.visibility == View.GONE) {
            // If top_sheet is hidden, show it
            topSheet.visibility = View.VISIBLE

//            val params = topSheet.layoutParams as? ConstraintLayout.LayoutParams
//            params?.matchConstraintPercentHeight = 0.3f  // Adjust the percentage as needed
//            topSheet.layoutParams = params

            // Adjust RecyclerView constraints
//            val recyclerParams = recyclerView?.layoutParams as? ConstraintLayout.LayoutParams
//            recyclerParams?.matchConstraintPercentHeight = 0.7f  // Adjust the remaining percentage
//            recyclerView?.layoutParams = recyclerParams
//        } else {
//            // If top_sheet is visible, hide it
//            topSheet?.visibility = View.GONE
//
//            // Adjust RecyclerView constraints
//            val recyclerParams = recyclerView?.layoutParams as? ConstraintLayout.LayoutParams
//            recyclerParams?.matchConstraintPercentHeight = 1f  // Full height
//            recyclerView?.layoutParams = recyclerParams
       }
    }



    private fun setRecyclerView() {
        binding.locationsRecyclerview.apply {
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
                 //   binding.progressBar.visibility = View.GONE
                    val char = character.data?.results
                    Log.d("check", "Characters: ${character.data}")

                    char?.let {
                        characterAdapter = CharacterAdapter(it)
                        setRecyclerView()
                        onItemClick()
                    }
                }
                Status.LOADING -> {
                //    binding.progressBar.visibility = View.VISIBLE

                }
                Status.ERROR -> {
                  //  binding.progressBar.visibility = View.GONE
                  //  binding.errText.visibility = View.VISIBLE

                }

            }

        }

    }
}