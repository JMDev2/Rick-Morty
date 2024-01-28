package com.ekenya.rnd.dashboard.models

data class CharacterResponse(
    val info: Info,
    val results: List<Result>
)
data class Result(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
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