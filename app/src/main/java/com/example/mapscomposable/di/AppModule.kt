package com.example.mapscomposable.di

import android.app.Application
import androidx.room.Room
import com.example.mapscomposable.data.FishingSpotDatabase
import com.example.mapscomposable.data.FishingSpotRepositoryImpl
import com.example.mapscomposable.domain.repository.FishingSpotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFishingSpotDatabase(app: Application): FishingSpotDatabase {
        return Room.databaseBuilder(
            app,
            FishingSpotDatabase::class.java,
            "fishing_spot.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFishingSpotRepository(db: FishingSpotDatabase): FishingSpotRepository {
        return FishingSpotRepositoryImpl(db.dao)
    }
}