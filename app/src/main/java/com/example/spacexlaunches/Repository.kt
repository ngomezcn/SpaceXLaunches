import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.spacexlaunches.model.models.LaunchModel
import com.example.spacexlaunches.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository {
    private val apiInterface = ApiInterface.create()
    var dataInfo = MutableLiveData<List<LaunchModel>>()

    fun fetchData(url: String) {

        /*val response = apiInterface.getData(url)
        if (response.isSuccessful) {
            dataInfo.value = response.body()
        } else
        {
            dataInfo.value = null
        }

        dataInfo.value = response.body()
        return dataInfo*/


        val call = apiInterface.getData(url)

        call.enqueue(object: Callback<List<LaunchModel>> {

            override fun onFailure(call: Call<List<LaunchModel>>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
                dataInfo.postValue(null)
            }
            override fun onResponse(call: Call<List<LaunchModel>?>, response: Response<List<LaunchModel>?>) {
                if (response != null && response.isSuccessful) {
                    dataInfo.value = response.body()
                    println("RECV DATA")
                    println(response.body().toString())
                }
            }
        })
    }
}

