package com.example.spacexlaunches.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacexlaunches.model.models.Launch


class LaunchViewModel: ViewModel(){
    var launchList = MutableLiveData<MutableList<Launch>>().apply {
        /*this.value = mutableListOf<Launch>(
            Launch("https://upload.wikimedia.org/wikipedia/commons/3/36/Fuente_Peque%C3%B1a_de_la_Plaza_de_la_Paz.jpg","Falcon SAT 1", "Falcon 1", "2006-03-25"),
            Launch("https://upload.wikimedia.org/wikipedia/commons/3/36/Fuente_Peque%C3%B1a_de_la_Plaza_de_la_Paz.jpg","Falcon SAT 2", "Falcon 1", "2007-03-21"),
            Launch("https://upload.wikimedia.org/wikipedia/commons/3/36/Fuente_Peque%C3%B1a_de_la_Plaza_de_la_Paz.jpg","Trailblazer", "Falcon 1", "2008-08-03"),
            Launch("https://upload.wikimedia.org/wikipedia/commons/3/36/Fuente_Peque%C3%B1a_de_la_Plaza_de_la_Paz.jpg","RatSat", "Falcon 1", "2008-09-28"),
            Launch("https://upload.wikimedia.org/wikipedia/commons/3/36/Fuente_Peque%C3%B1a_de_la_Plaza_de_la_Paz.jpg","Falcon 9 Test Flight", "Falcon 1", "2010-12-08"),
            Launch("https://upload.wikimedia.org/wikipedia/commons/3/36/Fuente_Peque%C3%B1a_de_la_Plaza_de_la_Paz.jpg","Cots 1", "Falcon 9", "2010-12-08"),
        )*/
    }

    var selectedUser = MutableLiveData<Launch>()

    fun addUser(user: Launch){
        launchList.value!!.add(user)
        //userList.postValue(userList.value)
    }

    fun select(launch: Launch) {
        selectedUser.postValue(launch)
    }
}