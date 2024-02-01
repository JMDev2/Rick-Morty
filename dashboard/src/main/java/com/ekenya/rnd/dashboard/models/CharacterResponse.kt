package com.ekenya.rnd.dashboard.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


data class CharacterResponse(
    val info: Info,
    val results: List<Result>
)
@Parcelize
data class Result(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location:@RawValue Location,
    val name: String,
    val origin:@RawValue Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
): Parcelable
data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)
data class Location(
    val name: String,
    val url: String
)

data class Origin(
    val name: String,
    val url: String
)