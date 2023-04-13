package com.example.citylocatorapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citylocatorapp.models.CityLocationItem
import com.example.citylocatorapp.repository.CityLocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: CityLocationRepository) : ViewModel() {

    val locationLiveData: LiveData<List<CityLocationItem>>
        get() = repository.cityLocationList




    init {
        viewModelScope.launch {
            repository.getCityLocationList()
        }
    }

}