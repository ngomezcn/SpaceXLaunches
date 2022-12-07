package com.example.spacexlaunches.viewmodel

import Repository
import androidx.lifecycle.MutableLiveData
import com.example.spacexlaunches.model.models.Launch
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    val repository = Repository()
    var data = MutableLiveData<List<Launch>>()
    var actualLaunch = MutableLiveData<Launch>()

    init {
        fetchData("launches")
    }

    fun fetchData(url: String){
        repository.fetchData(url)
        android.os.Handler().postDelayed({
            println("ACTUALIZAMOS DATA CON DATOS RECIBIDOS")
            data.postValue(repository.dataInfo.value)
            println(data.value)
        },1000)
    }

    fun setLaunch(launch: Launch){
        actualLaunch.value = launch
    }
}