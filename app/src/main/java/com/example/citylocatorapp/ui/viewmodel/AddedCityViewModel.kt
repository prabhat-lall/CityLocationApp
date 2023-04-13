package com.example.citylocatorapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.citylocatorapp.models.CityLocationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddedCityViewModel @Inject constructor() : ViewModel() {
    private val recentLocationLiveData = MutableLiveData<List<CityLocationItem>>()
    fun getRecentLocationList(): LiveData<List<CityLocationItem>> {
        return recentLocationLiveData
    }
    fun loadLocation(data : List<CityLocationItem>) {
        recentLocationLiveData.postValue(data)
    }


    val cityLocationList: MutableList<CityLocationItem> = mutableListOf()

}