package com.ekenya.rnd.dashboard.api

import com.ekenya.rnd.common.utils.Resource
import com.ekenya.rnd.dashboard.models.characters.CharacterResponse
import com.ekenya.rnd.dashboard.models.episodes.EpisodeResponse
import com.ekenya.rnd.dashboard.models.location.LocationResponse
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

    suspend fun getAllEpisodes(): Resource<EpisodeResponse?>{
        val response = api.getEpisodes()
        return if (response.isSuccessful){
            Resource.success(response.body())
        }else{
            Resource.error("No Episode found", null)
        }
    }

    suspend fun getTheLocation(): Resource<LocationResponse?>{
        val response = api.getLocations()
        return if (response.isSuccessful){
            Resource.success(response.body())
        }else{
            Resource.error("No Locations Found", null)
        }
    }
}