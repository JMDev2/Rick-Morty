package com.ekenya.rnd.dashboard.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekenya.rnd.common.utils.Resource
import com.ekenya.rnd.dashboard.models.characters.CharacterResponse
import com.ekenya.rnd.dashboard.models.episodes.EpisodeResponse
import com.ekenya.rnd.dashboard.models.location.LocationResponse
import com.ekenya.rnd.dashboard.models.location.Result
import com.ekenya.rnd.dashboard.repositories.CharacterRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: CharacterRespository) : ViewModel() {
//    var characterLiveData = MutableLiveData<Resource<CharacterResponse?>?>()
    var episodeLiveData = MutableLiveData<Resource<EpisodeResponse?>>()
    val locationLiveData = MutableLiveData<Resource<LocationResponse?>>()

    private var _result = MutableLiveData<Resource<LocationResponse?>>()
    val result: LiveData<Resource<LocationResponse?>>
        get() = _result

    private var _characterLiveData = MutableLiveData<Resource<CharacterResponse?>>()
    val characterLiveData: LiveData<Resource<CharacterResponse?>>
        get() = _characterLiveData




    private val _clickedCharacter = MutableLiveData<CharacterResponse>()
    val clickedCharacter: LiveData<CharacterResponse> get() = _clickedCharacter


    fun onCharacterClicked(character: CharacterResponse) {
        _clickedCharacter.value = character
    }


    init {
        getAllCharacters()
        getAllEpisodes()
       // getLocations()
    }


    fun getAllCharacters() = viewModelScope.launch {
        repository.getCharacter().collect{
            _characterLiveData.postValue(it)
        }
    }

    fun observeCharacterLiveData(): LiveData<Resource<CharacterResponse?>>{
            return _characterLiveData
    }

    fun getAllEpisodes()= viewModelScope.launch {
        repository.getEpisodes().collect {
            episodeLiveData.postValue(it)
        }
    }

    fun observeEpisodesLiveData(): LiveData<Resource<EpisodeResponse?>>{
        return episodeLiveData
    }

//    fun getLocations() = viewModelScope.launch {
//        repository.getLocations().collect{
//            when(it){
//                Resource.loading(it.data) -> {}
//                Resource.success(it.data, it.responseCode) -> {
//                    _result.value = it
//                }
//                Resource.error(it.message, it.responseCode) -> {}
//            }
//        }
//    }

//
//    private val _locationLiveData = MutableLiveData<Resource<LocationResponse?>>()
//    val locationLiveData: LiveData<Resource<LocationResponse?>>
//        get() = _locationLiveData

    fun getLocations(id: Int) {
        viewModelScope.launch {
            repository.getLocations(id).collect { location ->
                locationLiveData.postValue(location)
            }
        }
    }
    fun observeLocationLiveData(): LiveData<Resource<LocationResponse?>>{
        return locationLiveData
    }


    fun getCharacterById(id: Int): com.ekenya.rnd.dashboard.models.characters.Result? {
        return characterLiveData.value?.data?.results?.find { it.id == id }
    }


    private val _selectedCharacterId = MutableLiveData<Int>()

    // Function to set the selected character ID
    fun setSelectedCharacterId(id: Int) {
        _selectedCharacterId.value = id
        Log.d("ViewModel", "Selected Character ID set: $id")
    }

    // Optional: Function to get the selected character ID
    fun getSelectedCharacterId(): LiveData<Int?> {
        return _selectedCharacterId
    }



}