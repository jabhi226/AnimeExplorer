package com.example.animeexplorer.ui.features.animeDetails.model

import androidx.annotation.DrawableRes

data class Action(
    val id: Int,
    val label: String,
    @DrawableRes val imageId: Int,
    var isActive: Boolean
)