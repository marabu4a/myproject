package com.example.myproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.text.Html.*
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import android.text.style.ImageSpan
import android.text.style.URLSpan
import android.view.View
import android.widget.Toast
import com.example.myproject.AppActivity.Companion.getDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_scnd.*
import kotlinx.android.synthetic.main.article.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.lang.ref.WeakReference
import java.net.MalformedURLException
import java.sql.Struct
import java.util.*
import kotlin.coroutines.CoroutineContext
const val EXTRA_NAME = "titleArticle"

class ContentActivity : AppCompatActivity(),CoroutineScope {

    private val rootJob = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        val titleArticle = (intent.extras!!.get(EXTRA_NAME) as String).toString()
        if (isNetworkAvailable()) {
            jsonParse(titleArticle)
        }
        else {
            getDataFromDatabase(titleArticle)
            Toast.makeText(applicationContext,"Нет подключения к интернету",Toast.LENGTH_SHORT).show()
        }
        val intent = Intent(this,ScndActivity::class.java)
        nav2.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawer2.closeDrawers()
            when (menuItem.itemId) {
                R.id.engine -> {
                    intent.putExtra(EXTRA_POS,0)
                    this.startActivity(intent)
                }
                R.id.suspension -> {
                    intent.putExtra(EXTRA_POS,1)
                    this.startActivity(intent)
                }
                R.id.transmission -> {
                    intent.putExtra(EXTRA_POS,2)
                    this.startActivity(intent)
                }
                R.id.brakeSystem -> {
                    intent.putExtra(EXTRA_POS,3)
                    this.startActivity(intent)
                }
                R.id.electric -> {
                    intent.putExtra(EXTRA_POS,4)
                    this.startActivity(intent)
                }
                R.id.wheel -> {
                    intent.putExtra(EXTRA_POS,5)
                    this.startActivity(intent)
                }
                R.id.fuel -> {
                    intent.putExtra(EXTRA_POS,6)
                    this.startActivity(intent)
                }
                R.id.sensors-> {
                    intent.putExtra(EXTRA_POS,7)
                    this.startActivity(intent)
                }
                R.id.cool-> {
                    intent.putExtra(EXTRA_POS,8)
                    this.startActivity(intent)
                }
                R.id.igni-> {
                    intent.putExtra(EXTRA_POS,9)
                    this.startActivity(intent)
                }
            }
            true




        }

    }
    interface Callback {
        fun onImageClick(imageUrl: String?)
    }

    private fun setClickListenerOnHtmlImageGetter(html: Spannable, callback: Callback) {
        for (span in html.getSpans(0, html.length, ImageSpan::class.java)) {
            val flags = html.getSpanFlags(span)
            val start = html.getSpanStart(span)
            val end = html.getSpanEnd(span)

            html.setSpan(object : URLSpan(span.source) {
                override fun onClick(v: View) {
                    callback.onImageClick(span.source)
                }
            }, start, end, flags)
        }
    }

    private fun getDataFromDatabase(string: String) {
        doAsync {
            val myData = getDatabase()
            val imageGetter = PicassoImageGetter(texttest,applicationContext)
            val needArticle = myData!!.readoutDAO().getReadoutByAddr(string)
            var html: Spannable
            uiThread {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    html = fromHtml(needArticle.text, FROM_HTML_MODE_LEGACY, imageGetter, null) as Spannable
                } else {
                    html = Html.fromHtml(needArticle.text, imageGetter, null) as Spannable

                }

                texttest.text = html
            }
        }
        texttest.movementMethod = LinkMovementMethod()
    }

    private fun jsonParse(autoTitle: String) = launch {
        val url = "https://my-project-id-326ba.firebaseio.com/$autoTitle.json"
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder().url(url).build()
        val imageGetter = PicassoImageGetter(texttest,applicationContext)
        val response: String = withContext(Dispatchers.IO) {
            client.newCall(request).execute().body()!!.string()
        }
        val data: StructArt?
        var html: Spannable? = null
        val type = object : TypeToken<StructArt>() {}
        //val myData = getDatabase()
        //val needArticles = myData?.readoutDAO()?.getReadoutByAddr(autoTitle)
        data = Gson().fromJson<StructArt>(response, type.type)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            html = fromHtml(data.text, FROM_HTML_OPTION_USE_CSS_COLORS, imageGetter, null) as Spannable
        }

            texttest.text = html
        texttest.movementMethod = LinkMovementMethod.getInstance()
        }


    @SuppressLint("MissingPermission")
     fun isNetworkAvailable(): Boolean {
        val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (true) {
            val activeNetwork = cm.activeNetworkInfo
            activeNetwork?.isConnected ?: false
        } else false
    }



}
data class StructArt(val title:String, val text:String)





