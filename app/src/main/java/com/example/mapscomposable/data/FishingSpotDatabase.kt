package com.example.mapscomposable.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FishingSpotEntity::class],
    version = 1
)
abstract class FishingSpotDatabase: RoomDatabase() {
    abstract val dao: FishingSpotDao
}