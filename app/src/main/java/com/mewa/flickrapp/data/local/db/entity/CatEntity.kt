package com.mewa.flickrapp.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class CatEntity(
    @PrimaryKey val id: Int,
    val imageUrl: String,
    val description: String,
    val link: String,
    val published: String
)