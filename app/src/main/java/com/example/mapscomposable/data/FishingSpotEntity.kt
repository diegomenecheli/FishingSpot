package com.example.mapscomposable.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FishingSpotEntity(
    val lat: Double,
    val lng: Double,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)