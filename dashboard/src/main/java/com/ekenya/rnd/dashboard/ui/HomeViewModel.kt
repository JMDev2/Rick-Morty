package com.ekenya.rnd.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekenya.rnd.common.utils.Resource
import com.ekenya.rnd.dashboard.models.characters.CharacterResponse
import com.ekenya.rnd.dashboard.models.episodes.EpisodeResponse
import com.ekenya.rnd.dashboard.repositories.CharacterRespository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: CharacterRespository) : ViewModel() {
    var characterLiveData = MutableLiveData<Resource<CharacterResponse?>>()
    var episodeLiveData = MutableLiveData<Resource<EpisodeResponse?>>()

    init {
        getAllCharacters()
        getAllEpisodes()
    }


    fun getAllCharacters() = viewModelScope.launch {
        repository.getCharacter().collect{
            characterLiveData.postValue(it)
        }
    }

    fun observeCharacterLiveData(): LiveData<Resource<CharacterResponse?>>{
            return characterLiveData
    }

    fun getAllEpisodes()= viewModelScope.launch {
        repository.getEpisodes().collect {
            episodeLiveData.postValue(it)
        }
    }

    fun observeEpisodesLiveData(): LiveData<Resource<EpisodeResponse?>>{
        return episodeLiveData
    }

}