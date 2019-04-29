package com.example.myproject
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.example.myproject.AppActivity.Companion.db
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.FileOutputStream
import java.io.IOException
import kotlin.coroutines.CoroutineContext






class ContentActivity : AppCompatActivity(),CoroutineScope {
    private val DB_NAME = "autoarticles.db"
    private val rootJob = Job()

    private var structArticles: DataStructArticles? = db
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


            val artic = db!!.readoutDAO().allStructArticles
            Log.d("Activity", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
            uiThread {
                text_content.append("--")
                for (k in artic) {
                    Log.d("Activity", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv")
                    text_content.append(k.text + "--")
                }
                text_content.append("--")
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

    private fun copyDatabase(context: Context, databaseName: String,destinationPath: String) {
        /*val dbPath = context.getDatabasePath(databaseName)

        // If the database already exists, return
        if (dbPath.exists()) {
            Log.d("Activity", "db Path Exists")
            return
        }*/

        // Make sure we have a path to the file
        //dbPath.parentFile.mkdirs()
        // Try to copy database file
        try {
            val inputStream = context.assets.open("databases/$databaseName");
            val output = FileOutputStream(destinationPath);

            val buffer = ByteArray(8192)
            var length: Int
            length = inputStream.read(buffer, 0, 8192)
            while (length > 0) {
                output.write(buffer, 0, length)
                length = inputStream.read(buffer, 0, 8192)
            }
            output.flush()
            output.close()
            inputStream.close()
        } catch (e: IOException) {
            Log.d("Activity", "Failed to open file", e)
            e.printStackTrace()
        }

    }



    /*private fun DatabaseCopier() {
        //call method that check if database not exists and copy prepopulated file from assets
        copyDatabase(applicationContext,"articles.db")
        mAppDataBase = Room.databaseBuilder(
            applicationContext,
            DataStructArticles::class.java!!, "articles.db"
        )
            .addMigrations(DataStructArticles.MIGRATION_1_2)
            .build()
    }*/







    private class GetDataFromDb(var context: ContentActivity) : AsyncTask<Void, Void, List<StructArticles>>() {
        override fun doInBackground(vararg params: Void?): List<StructArticles> {
            return context.structArticles!!.readoutDAO().allStructArticles
        }
        override fun onPostExecute(articlesList: List<StructArticles>?) {
            if (articlesList!!.size > 0) {
                for (i in 0..articlesList.size - 1) {
                    context.text_content.append(articlesList[i].title)
                }
            }
        }
    }
}
data class Response(val text: String)





