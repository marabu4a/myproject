package com.example.myproject
import android.app.DownloadManager
import android.widget.ImageView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_content.*
import com.google.gson.reflect.TypeToken
import okhttp3.*
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.URL
import kotlin.coroutines.CoroutineContext
val data = mutableListOf<Response>()
class ContentActivity : AppCompatActivity(),CoroutineScope {
    private val rootJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

            jsonParse()

                /*val kek = data
        val xx = findViewById<TextView>(R.id.text_content)
        for (i in kek) {
                  for (k in i.employees) {
                      xx.append(k.firstname)
                  }


        }
        */

    }
    private fun jsonParse() = launch  {
        val url = "https://api.myjson.com/bins/kp9wz"
        val client = OkHttpClient.Builder().build()
        val request = okhttp3.Request.Builder().url(url).build()
        val response: String = withContext(Dispatchers.IO) {
            client.newCall(request).execute().body()!!.string()
        }
        val type = object : TypeToken<ArrayList<Response>>() {}
        val repos = Gson().fromJson<ArrayList<Response>>(response,type.type)
        data.clear()
        data.addAll(repos)

    }
}
data class Employee(val firstname: String,val age: Int,val mail: String)
data class Response(val employees: List<Employee>)





