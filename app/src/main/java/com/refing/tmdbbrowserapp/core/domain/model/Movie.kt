package com.refing.tmdbbrowserapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Movie(
    var id: Int = 0,
    var name: String? = null,
    var description: String? = null,
    var photo: String? = null,
    var vote_average: Double? = null,
    var vote_count: Int? = null,
    var isFavorite: Boolean = false
) : Parcelable