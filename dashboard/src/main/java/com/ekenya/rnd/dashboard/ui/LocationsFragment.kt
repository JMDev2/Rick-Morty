package com.ekenya.rnd.dashboard.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.utils.Status
import com.ekenya.rnd.common.utils.toast
import com.ekenya.rnd.dashboard.R
import com.ekenya.rnd.dashboard.adapter.CharacterAdapter
import com.ekenya.rnd.dashboard.databinding.FragmentLocationsBinding
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.common.utils.SHaredPreference
import com.ekenya.rnd.dashboard.models.location.LocationResponse
import javax.inject.Inject


class LocationsFragment : BaseDaggerFragment() {
    private lateinit var binding: FragmentLocationsBinding

    private lateinit var homeViewModel: HomeViewModel


    private lateinit var characterAdapter: CharacterAdapter
    //private lateinit var characterResult: Result
    // private lateinit var locationResult: com.ekenya.rnd.dashboard.models.location.Result

    private lateinit var locationName: String
    private lateinit var type: String
    private lateinit var dimension: String
    private lateinit var dateCreated: String


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
//        initTvs()

    }


    private fun initTvs(){
        binding.locationName.text = locationName
        binding.locationType.text = type
        binding.locationDimension.text = dimension
        binding.locationDate.text = dateCreated
    }

    private fun onItemClick() {
        characterAdapter?.onItemClick = { char ->

            locationName = char.name
            type = char.type
            dimension = char.species
            dateCreated = char.created

            initTvs()
            Log.i("MORARA", "$locationName $type $dimension $dateCreated")

            binding.topSheet.visibility = View.VISIBLE



            toast("${char.name}")

            char.id?.let { id ->
                // Save the selected character ID to SharedPreferences
                SHaredPreference.setSelectedCharacterId(requireContext(), id)
                Log.d("SelectedCharacterId", "pref ID: $id")

                // Update the views with the selected character ID
                  viewModel.getLocations(id)
                updateLocationUI()

                // Toggle the visibility of the top sheet
//                toggleTopSheetVisibility()
            }
        }
    }


//    private fun toggleTopSheetVisibility() {
//        val topSheet = binding.topSheet
//
//        if (onItemClick()){
//            topSheet.visibility = View.VISIBLE
//        } else{
//            topSheet.visibility = View.GONE
//        }
//    }


    private fun setRecyclerView() {
        binding.locationsRecyclerview.apply {
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
            adapter = characterAdapter
        }
    }

    private fun updateLocationUI() {
        viewModel.locationLiveData.observe(viewLifecycleOwner) { location ->
            when (location.status) {
                Status.SUCCESS -> {
                    val updatelocation = location.data?.results?.firstOrNull()


                    updatelocation?.let {
                        binding.locationName.text = it.name
                        binding.locationDate.text = it.created
                        binding.locationType.text = it.type
                        Log.d("checkOne", "Characters: ${updatelocation.id}")


                    }
                }
                Status.LOADING -> {
                    // Handle loading state if needed
                }
                Status.ERROR -> {
                    // Handle error if needed
                }
            }
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
                        updateLocationUI()
                        onItemClick()
//

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