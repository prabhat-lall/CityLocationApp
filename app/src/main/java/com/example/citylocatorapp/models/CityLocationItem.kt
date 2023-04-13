package com.example.citylocatorapp.models

import com.example.citylocater.utility.Constants.CITY_LOCATION_TABLE

data class CityLocationItem(
    val lat: String,
    val lon: String,
    val name: String,
    val state: String
)