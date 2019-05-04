package com.example.myproject
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.example.myproject.AppActivity.Companion.db
import com.example.myproject.AppActivity.Companion.getDatabase

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.FileOutputStream
import java.io.IOException
import kotlin.coroutines.CoroutineContext






class ContentActivity : AppCompatActivity(),CoroutineScope {
    private val DB_NAME = "databases/autoarticles.db"
    private val rootJob = Job()


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        //val db = Room.databaseBuilder(applicationContext,DataStructArticles::class.java,"articles.db").allowMainThreadQueries().build()
        // Асинхронно считаем данные из базы. Получаем запись с идентификатором ноль.
        //structArticles = DataStructArticles.getDatabase(applicationContext)!!
        //val rec = structArticles?.readoutDAO()?.getReadoutById(1)     // Считаем нулевую запись. Результат последнего выражения потока возвращается в переменную r

        // Запустить и забыть. Поток в потоке интерфейса UI. Ждём данные и выводим их в интерфейс.
        // Подождём результата.
                             // Выведем в интерфейс.


        //jsonParse()
        //DatabaseCopier()
        //GetDataFromDb(ContentActivity()).execute()
        doAsync {


            val artic = getDatabase()
            val tt = artic!!.readoutDAO().getReadoutById(0)
            text_content.append("vvvvvvvvvv")
            uiThread {
                text_content.append(tt.text)
                tt.text = "ggwp"
                artic.readoutDAO().updateReadout(tt)
                val vv = artic!!.readoutDAO().getReadoutById(0)
                text_content.append(vv.text)

            }


        }








    }

    private fun jsonParse() = launch {
        val url = "https://my-project-id-326ba.firebaseio.com/car.json"
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder().url(url).build()
        val response: String = withContext(Dispatchers.IO) {
            client.newCall(request).execute().body()!!.string()
        }
        val type = object : TypeToken<Response>() {}
        val repos = Gson().fromJson<Response>(response, type.type)
        //val data = repos.text
        val xx = findViewById<TextView>(R.id.text_content)
        //xx.append(data)


    }

}
data class Response(val text: String)





