package com.example.myproject
import android.app.DownloadManager
import android.widget.ImageView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_content.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class ContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)


        jsonParse()



    }
    fun jsonParse() {
        val url = "https://api.myjson.com/bins/ao3lo"
        val client = OkHttpClient()
        //AsyncTaskHandJson().execute(url)
        val request = okhttp3.Request.Builder().url(url).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response.body()?.string()
                println(body)
                val gson = GsonBuilder().create()
                val homefeed = Gson().fromJson(body,Cat::class.java)
                runOnUiThread {

                    text_content.text = homefeed.title
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })


    }





}

class Cat(val title: String,val text: String)
