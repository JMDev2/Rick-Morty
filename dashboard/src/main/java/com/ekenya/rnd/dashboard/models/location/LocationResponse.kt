package com.ekenya.rnd.dashboard.models.location

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class LocationResponse(
    val info: Info? = null,
    val results: List<Result>
): Parcelable

@Parcelize
data class Info(
    val count: Int? = null,
    val next: String? = null,
    val pages: Int? = null,
//    val prev: @RawValue<Any> = null
    val prev: @RawValue Any? = null
): Parcelable
@Parcelize
data class Result(
    val created: String? = null,
    val dimension: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val residents: List<String>? = null,
    val type: String? = null,
    val url: String? = null
) : Parcelable