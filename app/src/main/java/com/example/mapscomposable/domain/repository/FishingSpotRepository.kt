package com.example.mapscomposable.domain.repository

import com.example.mapscomposable.domain.model.FishingSpot
import kotlinx.coroutines.flow.Flow

interface FishingSpotRepository {

    suspend fun insertFishingSpot(spot: FishingSpot)

    suspend fun deleteFishingSpot(spot: FishingSpot)

    suspend fun getSpotLocation(spot: FishingSpot, current: FishingSpot)

    fun getFishingSpots(): Flow<List<FishingSpot>>
}