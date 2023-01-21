package com.example.spacexlaunches.viewmodel

import Repository
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.spacexlaunches.models.Mission.MissionModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexlaunches.models.Mission.Failures
import com.example.spacexlaunches.models.LaunchpadModel
import com.example.spacexlaunches.models.Rocket.RocketModel
import com.example.spacexlaunches.room.MissionsListApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModel : ViewModel() {
    val repository = Repository()

    var missionList = MutableLiveData<List<MissionModel>>()

    var selectedMission = MutableLiveData<MissionModel>()
    var selectedRocket = MutableLiveData<RocketModel>()
    var selectedLaunchpad = MutableLiveData<LaunchpadModel>()

    var scrollPos = MutableLiveData<Int>(0)

    var onlyShowPinned = MutableLiveData<Boolean>(false)

    var searchMissionList = MutableLiveData<MutableList<MissionModel>>(mutableListOf())
    var searchText = MutableLiveData<String>("")

    init {
        loadInitData()
    }

    fun loadInitData()
    {
        fetchMissionList("launches")
    }

    fun loadPinnedList() {

        val models = mutableListOf<MissionModel>()

        CoroutineScope(Dispatchers.IO).launch {

            val entities = MissionsListApplication.database.launchDao().getAll()

            for (i in entities) {
                val launch = MissionModel()

                launch.name = i.name
                launch.dateUtc = i.dateUtc

                val failures = Failures()
                failures.reason = i.failures_reason
                launch.failures.add(failures)

                launch.details = i.details
                launch.success = i.success
                launch.flightNumber = i.flightNumber
                launch.links!!.patch!!.large = i.links_patch_large
                launch.links!!.patch!!.small = i.links_patch_small

                models.add(launch)
                missionList.postValue(models)
            }
        }
    }

    private fun fetchMissionList(url: String) {

        viewModelScope.launch {
            val response = withContext( Dispatchers .IO) {
                repository.getAllMissions(url)
            }

            if(response.isSuccessful){
                missionList.postValue(response.body())
            }
            else{
                Log.e("Error :", response.message())
            }
        }
    }

    fun setLaunch(launch: MissionModel){

        viewModelScope.launch {
            val response = withContext( Dispatchers .IO) { repository.getRocketDetail("rockets/" + launch.rocket) }
            if(response.isSuccessful){
                selectedRocket.postValue(response.body())
            }
            else{
                Log.e("Error :", response.message())
            }
        }

        viewModelScope.launch {
            val response = withContext( Dispatchers .IO) { repository.getLaunchpadDetail("launchpads/" + launch.launchpad) }
            if(response.isSuccessful){
                selectedLaunchpad.postValue(response.body())
            }
            else{
                Log.e("Error :", response.message())
            }
        }

        selectedMission.value = launch
    }
}