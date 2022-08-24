package com.example.whac_a_mole.data

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView

data class HoleData(
    private val iv: AppCompatImageView,
    @DrawableRes val defaultPicture: Int,
    @DrawableRes val changePicture: Int,
    var isActive: Boolean = false
) {

    companion object {

        const val DEFAULT_TIME: Long = 500L
    }

    @DrawableRes
    fun getDrawable(): Int = if (isActive) changePicture else defaultPicture
}