package com.ekenya.rnd.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekenya.rnd.common.utils.Resource
import com.ekenya.rnd.dashboard.models.characters.CharacterResponse
import com.ekenya.rnd.dashboard.models.episodes.EpisodeResponse
import com.ekenya.rnd.dashboard.models.location.LocationResponse
import com.ekenya.rnd.dashboard.repositories.CharacterRespository
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

    init {
        getAllCharacters()
        getAllEpisodes()
        getLocations()
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


    fun getLocations() = viewModelScope.launch {
        repository.getLocations().collect{
            locationLiveData.postValue(it)
        }
    }
    fun observeLocations(): LiveData<Resource<LocationResponse?>>{
        return locationLiveData
    }


}