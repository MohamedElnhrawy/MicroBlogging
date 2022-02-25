package com.example.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class PostUiModel(
    val id: Int ,
    val authorId: Int ,
    val date: String? ,
    val title: String? ,
    val body: String? ,
    val imageUrl: String? ,

) : Parcelable