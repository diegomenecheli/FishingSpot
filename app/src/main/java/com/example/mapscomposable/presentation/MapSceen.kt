package com.example.mapscomposable.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.mapscomposable.R
import com.example.mapscomposable.domain.repository.FishingSpotRepository
import com.example.mapscomposable.ui.theme.Blue400
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("MissingPermission")
@Composable
fun MapScreen(
    context: Context,
    currentLatLng: LatLng,
    viewModel: MapsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(
                        MapEvent.MarkCurrentLocation,
                        context
                    )
                },
                elevation = FloatingActionButtonDefaults.elevation(),
                backgroundColor = Blue400,
            ) {
                Icon(
                    imageVector = Icons.Default.AddLocation,
                    contentDescription = context.getString(R.string.marker_content_description)
                )
            }
        }
    ) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = viewModel.state.properties,
            uiSettings = uiSettings,
            cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(
                    currentLatLng, 16f
                )
            }
        ) {
            viewModel.state.fishingSpots.forEach { spot ->
                Marker(
                    position = LatLng(spot.lat, spot.lng),
                    title = context.getString(R.string.marker_title_click, spot.lat, spot.lng),
                    snippet = context.getString(R.string.marker_snippet_click),
                    onInfoWindowLongClick = {
                        viewModel.onEvent(
                            MapEvent.OnInfoWindowLongClick(spot),
                            context
                        )
                    },
                    onClick = {
                        it.showInfoWindow()
                        true
                    },
                    onInfoWindowClick = {
                        viewModel.onEvent(
                            MapEvent.OnInfoClick(spot),
                            context
                        )
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_RED
                    )
                )
            }
        }
    }
}