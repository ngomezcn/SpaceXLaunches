package com.example.spacexlaunches.viewmodel

import Repository
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.spacexlaunches.models.Launch.LaunchModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexlaunches.models.LaunchpadModel
import com.example.spacexlaunches.models.Rocket.RocketModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModel : ViewModel() {
    val repository = Repository()
    var launchList = MutableLiveData<List<LaunchModel>>()

    var searchLaunchList = MutableLiveData<MutableList<LaunchModel>>(mutableListOf())

    var actualLaunch = MutableLiveData<LaunchModel>()

    var rocket = MutableLiveData<RocketModel>()
    var launchpad = MutableLiveData<LaunchpadModel>()

    var scrollPos = MutableLiveData<Int>(0)

    init {
        fetchMissionList("launches")
    }

    private fun fetchMissionList(url: String) {

        viewModelScope.launch {
            val response = withContext( Dispatchers .IO) {
                repository.getAllMissions(url)
            }

            if(response.isSuccessful){
                launchList.postValue(response.body())
            }
            else{
                Log.e("Error :", response.message())
            }
        }
    }

    fun setLaunch(launch: LaunchModel){

        viewModelScope.launch {
            val response = withContext( Dispatchers .IO) { repository.getRocketDetail("rockets/" + launch.rocket) }
            if(response.isSuccessful){
                rocket.postValue(response.body())
            }
            else{
                Log.e("Error :", response.message())
            }
        }

        viewModelScope.launch {
            val response = withContext( Dispatchers .IO) { repository.getLaunchpadDetail("launchpads/" + launch.launchpad) }
            if(response.isSuccessful){
                launchpad.postValue(response.body())
            }
            else{
                Log.e("Error :", response.message())
            }
        }


        actualLaunch.value = launch
    }
}