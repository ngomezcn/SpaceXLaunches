package com.example.spacexlaunches.viewmodel

import Repository
import androidx.lifecycle.MutableLiveData
import com.example.spacexlaunches.model.models.LaunchModel
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    val repository = Repository()
    var data = MutableLiveData<List<LaunchModel>>()
    var actualLaunch = MutableLiveData<LaunchModel>()

    init {
        fetchData("launches")
    }

    fun fetchData(url: String){
        repository.fetchData(url)
        android.os.Handler().postDelayed({
            data.postValue(repository.dataInfo.value)
            println(data.value)
        },3000)
    }

    fun setLaunch(launch: LaunchModel){
        actualLaunch.value = launch
    }
}