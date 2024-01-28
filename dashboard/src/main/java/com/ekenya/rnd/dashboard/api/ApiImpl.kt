package com.ekenya.rnd.dashboard.api

import com.ekenya.rnd.common.utils.Resource
import com.ekenya.rnd.dashboard.models.CharacterResponse
import javax.inject.Inject

class ApiImpl @Inject constructor(private val api: ApiService){
    suspend fun getAllCharacters(): Resource<CharacterResponse?>{
        val response = api.getCharacters()
        return if (response.isSuccessful){
            Resource.success(response.body())
        }else{
            Resource.error("No Character found", null)
        }
    }
}