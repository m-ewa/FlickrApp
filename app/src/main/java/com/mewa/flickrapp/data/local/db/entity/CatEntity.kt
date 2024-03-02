package com.mewa.flickrapp.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "cats")
data class CatEntity(
    @PrimaryKey val imageUrl: String,
    val description: String,
    val link: String,
    val published: Date,
)