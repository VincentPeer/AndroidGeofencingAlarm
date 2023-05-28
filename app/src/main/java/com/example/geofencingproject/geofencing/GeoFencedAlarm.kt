package com.example.geofencingproject.geofencing

class GeoFencedAlarm {
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var radius: Double = 0.0

    fun getLatitude(): Double {
        return latitude
    }

    fun getLongitude(): Double {
        return longitude
    }

    fun getRadius(): Double {
        return radius
    }

    fun setLatitude(latitude: Double) {
        this.latitude = latitude
    }

    fun setLongitude(longitude: Double) {
        this.longitude = longitude
    }

    fun setRadius(radius: Double) {
        this.radius = radius
    }


}