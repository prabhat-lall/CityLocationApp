package com.example.citylocatorapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.citylocatorapp.api.CityLocationApi
import com.example.citylocatorapp.models.CityLocationItem
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CityLocationRepository @Inject constructor(
    private val cityLocationApi: CityLocationApi
) {

    private val _cityLocationList = MutableLiveData<List<CityLocationItem>>()
    val cityLocationList: LiveData<List<CityLocationItem>>
        get() = _cityLocationList

    suspend fun getCityLocationList() {
        val result = cityLocationApi.getCityLocationAPI()
        if (result.isSuccessful && result.body() != null) {
            _cityLocationList.postValue(result.body())
        }
    }

}