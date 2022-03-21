package com.example.mapscomposable.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FishingSpotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFishingSpot(spot: FishingSpotEntity)

    @Delete
    suspend fun deleteFishingSpot(spot: FishingSpotEntity)

    @Query("SELECT * FROM fishingspotentity")
    fun getFishingSpots(): Flow<List<FishingSpotEntity>>

    @Query("SELECT * FROM fishingspotentity WHERE id = :spotId")
    suspend fun getSpotDetails(spotId: Int): FishingSpotEntity

}