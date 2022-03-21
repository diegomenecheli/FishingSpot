package com.example.mapscomposable.data

import com.example.mapscomposable.domain.model.FishingSpot

fun FishingSpotEntity.toFishingSpot(): FishingSpot {
    return FishingSpot(
        id = id,
        lat = lat,
        lng = lng
    )
}

fun FishingSpot.toFishingSpotEntity(): FishingSpotEntity {
    return FishingSpotEntity(
        id = id,
        lat = lat,
        lng = lng
    )
}