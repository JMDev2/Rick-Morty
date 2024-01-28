package com.ekenya.rnd.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekenya.rnd.common.utils.Resource
import com.ekenya.rnd.dashboard.models.CharacterResponse
import com.ekenya.rnd.dashboard.repositories.CharacterRespository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: CharacterRespository) : ViewModel() {
    var characterLiveData = MutableLiveData<Resource<CharacterResponse?>>()

    init {
        getAllCharacters()
    }

    fun getAllCharacters() = viewModelScope.launch {
        repository.getCharacter().collect{
            characterLiveData.postValue(it)
        }
    }

    fun observeCharacterLiveData(): LiveData<Resource<CharacterResponse?>>{
            return characterLiveData
    }

}