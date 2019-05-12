package com.example.myproject

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.text.Html.fromHtml
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.sufficientlysecure.htmltextview.HtmlTextView
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.lang.ref.WeakReference
import java.net.MalformedURLException
import java.util.*
import kotlin.coroutines.CoroutineContext


class ContentActivity : AppCompatActivity(),CoroutineScope {
    val mDrawableCache = Collections.synchronizedMap(WeakHashMap<String, WeakReference<Drawable>>())
    private val rootJob = Job()
    private var repos: Responsee? = null
    private var article_list: List<String>? = null
    private var html:HtmlTextView? =null
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + rootJob

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        //article_view.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
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
    fun getDrawable(source: String?): Drawable? {
        Toast.makeText(
            applicationContext, source,
            Toast.LENGTH_LONG
        ).show()

        lateinit var drawable: Drawable
        if (source!!.startsWith("http")) {
            // load from internet

            try {
                val httpClient = OkHttpClient.Builder().build()
                val request = Request.Builder().url(source).build()
                val response: InputStream = httpClient.newCall(request).execute().body()!!.byteStream()
                val bufferedInputStream = BufferedInputStream(response)
                val bm = BitmapFactory.decodeStream(bufferedInputStream)

                // convert Bitmap to Drawable
                drawable = BitmapDrawable(resources, bm)

                drawable.setBounds(
                    0, 0,
                    bm.width,
                    bm.height
                );

            } catch (e: MalformedURLException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }

        } else {
            // load from local drawable

            val dourceId = applicationContext
                .resources
                .getIdentifier(source, "drawable", packageName)

            drawable = applicationContext.resources
                .getDrawable(
                    dourceId,theme
                )

            drawable.setBounds(
                0, 0, drawable.intrinsicWidth,
                drawable.intrinsicHeight
            )
        }

        return drawable
    }

    fun jsonParse() = launch {
        val url = "https://my-project-id-326ba.firebaseio.com/Устройство двигателя.json"
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder().url(url).build()
        val response: String = withContext(Dispatchers.IO) {
            client.newCall(request).execute().body()!!.string()
        }
        val type = object : TypeToken<Responsee>() {}
        val p = URLImageParser(texttest, applicationContext)
        repos = Gson().fromJson<Responsee>(response, type.type)
        article_list = listOf(repos!!.title,repos!!.text0,repos!!.text1,repos!!.text2,repos!!.text3,repos!!.text4,repos!!.text5)
        runOnUiThread {
            //webtext.loadData(repos!!.text0,"text/html","UTF-8")
            //rticle_view.adapter = ContentAdapter(article_list!!)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                texttest.text = fromHtml(repos?.text0, FROM_HTML_MODE_LEGACY, p, null)

                }

        }
        texttest.movementMethod = ScrollingMovementMethod()
        }
    @SuppressLint("MissingPermission")
     fun isNetworkAvailable(): Boolean {
        val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (cm is ConnectivityManager) {
            val activeNetwork = cm.activeNetworkInfo
            activeNetwork?.isConnected ?: false
        } else false
    }




            }
data class Responsee(val text0: String,val text1: String,val text2: String,val text3: String,val text4: String,val text5: String,val title: String)





