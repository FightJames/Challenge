package com.techapp.james.mylocation

import android.annotation.SuppressLint
import android.content.Context
import android.location.*
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.maps_activity.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mLocationMgr: LocationManager
    private lateinit var mLocationPro: String
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.maps_activity)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mLocationMgr = this.getSystemService(LocationManager::class.java)
        } else {
            mLocationMgr = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }
        mLocationPro = LocationManager.GPS_PROVIDER

        Toast.makeText(this@MapsActivity, "${mLocationMgr.isProviderEnabled(LocationManager.GPS_PROVIDER)}", Toast.LENGTH_LONG).show()

        mLocationMgr.requestLocationUpdates(mLocationPro, 0L, 0f, object : LocationListener {
            override fun onStatusChanged(provider: String, status: Int, extras: Bundle?) {

                Toast.makeText(this@MapsActivity, "ProviderStatus $provider", Toast.LENGTH_LONG).show()
            }

            override fun onProviderEnabled(provider: String) {
                Toast.makeText(this@MapsActivity, "ProviderEnable $provider", Toast.LENGTH_LONG).show()
            }

            override fun onProviderDisabled(provider: String) {
                Toast.makeText(this@MapsActivity, "ProviderDis $provider", Toast.LENGTH_LONG).show()
            }

            override fun onLocationChanged(location: Location?) {

                mMap?.let {
                    location?.let {
                        loactTextView.text = location?.latitude.toString() + " , " + location.longitude.toString()
                    }
                }


            }
        })

        var location = mLocationMgr.getLastKnownLocation(mLocationPro)
        loactTextView.text = location?.latitude.toString() + " , " + location.longitude.toString()

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap.isMyLocationEnabled = true
        mMap.uiSettings.isMyLocationButtonEnabled = true

        mMap.setOnMyLocationClickListener {
            Toast.makeText(this, " Hello", Toast.LENGTH_LONG).show()
        }
    }
}
