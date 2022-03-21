package com.example.mapscomposable.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapscomposable.domain.model.FishingSpot
import com.example.mapscomposable.domain.repository.FishingSpotRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val repository: FishingSpotRepository
) : ViewModel() {

    var state by mutableStateOf(MapState())
    lateinit var fusedLocationClient: FusedLocationProviderClient

    init {
        viewModelScope.launch {
            repository.getFishingSpots().collectLatest { spots ->
                state = state.copy(
                    fishingSpots = spots
                )
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun onEvent(event: MapEvent, context: Context) {
        when (event) {
            is MapEvent.MarkCurrentLocation -> {
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location ->
                        viewModelScope.launch {
                            repository.insertFishingSpot(
                                FishingSpot(
                                    location.latitude,
                                    location.longitude
                                )
                            )
                        }
                    }
            }
            is MapEvent.OnInfoClick -> {
                //TODO still need to make path to the marker clicked
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location ->
                        viewModelScope.launch {
                            repository.getSpotLocation(
                                event.spot,
                                FishingSpot(
                                    location.latitude,
                                    location.longitude
                                )
                            )
                        }
                    }


            }
            is MapEvent.OnInfoWindowLongClick -> {
                viewModelScope.launch {
                    repository.deleteFishingSpot(event.spot)
                }
            }
        }
    }
}