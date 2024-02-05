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
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.dashboard.models.characters.Result
import javax.inject.Inject


class LocationsFragment : BaseDaggerFragment() {
   private lateinit var binding: FragmentLocationsBinding

   private lateinit var homeViewModel: HomeViewModel


    private lateinit var characterAdapter: CharacterAdapter
    //private lateinit var characterResult: Result
    private lateinit var locationResult: com.ekenya.rnd.dashboard.models.location.Result


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
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


    }

    private fun onItemClick() {
        characterAdapter?.onItemClick = { char ->
            toast("click")
            toggleTopSheetVisibility()
            displayLocationDetails()


        }
    }

    private fun toggleTopSheetVisibility() {
        val topSheet = view?.findViewById<View>(R.id.top_sheet)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)

        if (topSheet?.visibility == View.GONE) {
            // If top_sheet is hidden, show it
            topSheet.visibility = View.VISIBLE

       }
    }

    val res = com.ekenya.rnd.dashboard.models.location.Result(created = "", dimension = null, id, name = "", residents = null, type = "", url = "")

    private fun displayLocationDetails(){
           viewModel.observeLocations().observe(
               viewLifecycleOwner
           ){
               location ->
               when(location.status){
                   Status.SUCCESS ->{
                       val loc = location.data?.results
                       Log.d("checkloacaion", "location: ${location.data}")
                       Log.d("checkloacaionid", "id: ${res.id}")
                       loc?.let {
//                           binding.locationName.text = loc
                           binding.locationType.text = res.type
                           binding.locationDimension.text = res.dimension
                           binding.locationDate.text = res.created
                       }
                   }
                   Status.ERROR ->{

                   }
                   Status.LOADING ->{

                   }
               }
           }

    }
//    private fun displayCharacterDetails(result: com.ekenya.rnd.dashboard.models.characters.Result){
//        binding.locationName.text = result.name
//        binding.locationType.text = result.type
////        binding.locationDimension.text = result.dimension
//        binding.locationDate.text = result.created
//    }



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
//                        for (i in 0..it.size){
//                            displayCharacterDetails(
//                                it[i]
//                            )
//                        }
                       // result = Result()

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