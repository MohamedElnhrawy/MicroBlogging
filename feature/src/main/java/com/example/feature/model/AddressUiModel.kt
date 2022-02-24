package com.example.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*


@Parcelize
data class AddressUiModel(
    val latitude: Double,
    val longitude: Double
) : Parcelable