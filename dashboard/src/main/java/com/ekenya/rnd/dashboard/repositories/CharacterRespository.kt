package com.ekenya.rnd.dashboard.repositories

import com.ekenya.rnd.common.utils.Resource
import com.ekenya.rnd.dashboard.api.ApiImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterRespository @Inject constructor(private val api: ApiImpl) {
    suspend fun getCharacter()= flow {
        emit(Resource.loading(null))
        emit(api.getAllCharacters())
    }.flowOn(Dispatchers.IO)

    suspend fun getEpisodes()= flow {
        emit(Resource.loading(null))
        emit(api.getAllEpisodes())
    }.flowOn(Dispatchers.IO)

    suspend fun getLocations(id: Int)= flow{
        emit(Resource.loading(null))
        emit(api.getTheLocation(id))
    }.flowOn(Dispatchers.IO)
}