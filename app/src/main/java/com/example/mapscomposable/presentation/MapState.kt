package com.example.mapscomposable.presentation

import com.example.mapscomposable.domain.model.FishingSpot
import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties: MapProperties = MapProperties(
        isMyLocationEnabled = true
    ),
    val fishingSpots: List<FishingSpot> = emptyList(),
    val isNightMap: Boolean = false
)