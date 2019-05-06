package com.example.myproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myproject.AppActivity.Companion.getDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.coroutines.CoroutineContext






class ContentActivity : AppCompatActivity(),CoroutineScope {
    private val rootJob = Job()


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        article_view.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        val content_adapter = ContentAdapter()
        article_view.adapter = content_adapter

          //jsonParse()
        doAsync {


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


        }








    }

    private fun jsonParse() = launch {
        val url = "https://my-project-id-326ba.firebaseio.com/Тест.json"
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder().url(url).build()
        val response: String = withContext(Dispatchers.IO) {
            client.newCall(request).execute().body()!!.string()
        }
        val type = object : TypeToken<Responsee>() {}
        val repos = Gson().fromJson<Responsee>(response, type.type)
        val data = repos.text
        val xx = findViewById<TextView>(R.id.text_content)
        xx.append(data)



    }

}
data class Responsee(val text: String)





