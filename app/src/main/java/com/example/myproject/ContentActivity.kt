package com.example.myproject

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.coroutines.CoroutineContext








class ContentActivity : AppCompatActivity(),CoroutineScope {
    private val rootJob = Job()
    private var repos: Responsee? = null
    private var article_list: List<String>? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        article_view.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        jsonParse()




          //jsonParse()
            /*doAsync {


            val artic = getDatabase()
            val tt = artic!!.readoutDAO().getReadoutById(0)
            text_content.append("vvvvvvvvvv")
            uiThread {
                text_content.append(tt.text)
                tt.text = "ggwp"
                artic.readoutDAO().updateReadout(tt)
                val vv = artic!!.readoutDAO().getReadoutByAddr("Устройство двигателей внутреннего сгорания")

                text_content.append(vv.text)

            }


        }*/








    }

    fun jsonParse() = launch {
        val url = "https://my-project-id-326ba.firebaseio.com/Устройство двигателя.json"
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder().url(url).build()
        val response: String = withContext(Dispatchers.IO) {
            client.newCall(request).execute().body()!!.string()
        }
        val type = object : TypeToken<Responsee>() {}
        repos = Gson().fromJson<Responsee>(response, type.type)
        article_list = listOf(repos!!.title,repos!!.text0,repos!!.text1,repos!!.text2,repos!!.text3,repos!!.text4,repos!!.text5)
        runOnUiThread {
            article_view.adapter = ContentAdapter(article_list!!)
        }
    }
    @SuppressLint("MissingPermission")
    private fun isNetworkAvailable(): Boolean {
        val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (cm is ConnectivityManager) {
            val activeNetwork = cm.activeNetworkInfo
            activeNetwork?.isConnected ?: false
        } else false
    }

}
data class Responsee(val text0: String,val text1: String,val text2: String,val text3: String,val text4: String,val text5: String,val title: String)





