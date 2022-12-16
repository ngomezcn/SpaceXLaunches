import com.example.spacexlaunches.retrofit.ApiInterface


class Repository {
    private val apiInterface = ApiInterface.create()

    suspend fun getAllMissions (url: String) = apiInterface.getAllMissions(url)

    suspend fun getRocketDetail(url: String) = apiInterface.getRocketDetail(url)

    suspend fun getLaunchpadDetail(url: String) = apiInterface.getLaunchpadDetail(url)

}

