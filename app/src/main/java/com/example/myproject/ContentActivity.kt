package com.example.myproject
import android.app.DownloadManager
import android.widget.ImageView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlin.coroutines.CoroutineContext
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_content.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.URL
import org.json.JSONException



class ContentActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)


        jsonParse()



    }


     private fun jsonParse() {

     }





}



