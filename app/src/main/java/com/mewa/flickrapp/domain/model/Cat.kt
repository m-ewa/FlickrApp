package com.mewa.flickrapp.domain.model

import java.util.Date

data class Cat(
    val imageUrl: String?,
    val description: String?,
    val link: String?,
    val published: Date
)