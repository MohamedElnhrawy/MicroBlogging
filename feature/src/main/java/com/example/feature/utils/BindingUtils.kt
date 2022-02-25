package com.example.feature.utils

import android.annotation.SuppressLint
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.common.R

object BindingUtils {

    @JvmStatic
    @SuppressLint("CheckResult")
    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String) {
        if (TextUtils.isEmpty(url)) return
        val context = imageView.context
        Glide.with(context)
            .load(url)
            .error(R.drawable.ic_error)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

}