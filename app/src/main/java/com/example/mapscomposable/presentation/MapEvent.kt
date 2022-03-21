package com.example.mapscomposable.presentation

import com.example.mapscomposable.domain.model.FishingSpot
import com.google.android.gms.maps.model.LatLng

sealed class MapEvent {

    object MarkCurrentLocation : MapEvent()

    data class OnInfoClick(val spot: FishingSpot) : MapEvent()

    data class OnInfoWindowLongClick(val spot: FishingSpot) : MapEvent()
}
