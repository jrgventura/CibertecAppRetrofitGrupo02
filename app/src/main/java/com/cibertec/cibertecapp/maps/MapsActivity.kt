package com.cibertec.cibertecapp.maps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cibertec.cibertecapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity: AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var viewModel: MapsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        viewModel = ViewModelProvider(this)[MapsViewModel::class.java]
        viewModel.getPlacesFirestore()

        val mapFrame = supportFragmentManager.findFragmentById(R.id.fragmentMap)
                as SupportMapFragment
        mapFrame.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                this, R.raw.map_style
            )
        )

        val positionMarket = LatLng(-8.111096,-79.028836)
        map.addMarker(
            MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_map))
                .position(positionMarket)
                .title("Trujillo")
        )

        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(positionMarket, 18f),
            4000,
            null
        )

        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isRotateGesturesEnabled = false

        viewModel.listPlaceMutable.observe(this) { listLugares ->
            if (listLugares.isNotEmpty()) {

                for (lugar in listLugares) {
                    val nombre = lugar.titulo
                    val posicion = lugar.posicion

                    val positionMarketFirestore = LatLng(posicion.latitude,
                        posicion.longitude)

                    map.addMarker(
                        MarkerOptions()
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_map))
                            .position(positionMarketFirestore)
                            .title(nombre)
                    )
                }

            }
        }

    }

}