package com.ekenya.rnd.dashboard.api

import com.ekenya.rnd.dashboard.models.characters.CharacterResponse
import com.ekenya.rnd.dashboard.models.episodes.EpisodeResponse
import com.ekenya.rnd.dashboard.models.location.LocationResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    suspend fun getCharacters(

    ): Response<CharacterResponse>

    @GET("episode")
    suspend fun getEpisodes(

    ): Response<EpisodeResponse>

    @GET("location")
    suspend fun getLocations(

    ): Response<LocationResponse>

}

