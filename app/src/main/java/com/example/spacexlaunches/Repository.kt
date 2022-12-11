import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.spacexlaunches.model.models.Launch
import com.example.spacexlaunches.retrofit.ApiInterface
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Repository {
    private val apiInterface = ApiInterface.create()
    var dataInfo = MutableLiveData<List<Launch>>()

    fun fetchData(url: String) {
        val call = apiInterface.getData(url)

        call.enqueue(object: Callback<List<Launch>> {

            override fun onFailure(call: Call<List<Launch>>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
                dataInfo.postValue(null)
            }
            override fun onResponse(call: Call<List<Launch>?>, response: Response<List<Launch>?>) {
                if (response != null && response.isSuccessful) {
                    dataInfo.value = response.body()
                    println("RECV DATA")
                    println(response.body().toString())
                }
            }
        })

        /*call.enqueue(object: Callback<List<Launch>> {

            override fun onFailure(call: Call<List<Launch>>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
                dataInfo.postValue(null)
            }
            override fun onResponse(call: Call<List<Launch>?>, response: Response<List<Launch>?>) {
                if (response != null && response.isSuccessful) {
                    dataInfo.value = response.body()
                    println("RECV DATA")
                    println(response.body().toString())
                }
            }
        })*/
    }
}

