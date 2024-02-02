package com.ekenya.rnd.dashboard.models.episodes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class EpisodeResponse(
    val info: Info,
    val results: List<Result>
)
@Parcelize
data class Result(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
) :Parcelable

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)