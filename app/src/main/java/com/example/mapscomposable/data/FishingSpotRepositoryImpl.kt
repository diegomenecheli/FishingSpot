package com.example.mapscomposable.data

import com.example.mapscomposable.domain.model.FishingSpot
import com.example.mapscomposable.domain.repository.FishingSpotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FishingSpotRepositoryImpl(
    private val dao: FishingSpotDao
) : FishingSpotRepository {

    override suspend fun insertFishingSpot(spot: FishingSpot) {
        dao.insertFishingSpot(spot.toFishingSpotEntity())
    }

    override suspend fun deleteFishingSpot(spot: FishingSpot) {
        dao.deleteFishingSpot(spot.toFishingSpotEntity())
    }

    override suspend fun getSpotLocation(spot: FishingSpot, current: FishingSpot) {
        dao.getSpotDetails(spot.toFishingSpotEntity().id!!)
    }

    override fun getFishingSpots(): Flow<List<FishingSpot>> {
        return dao.getFishingSpots().map { spots ->
            spots.map { it.toFishingSpot() }
        }
    }
}