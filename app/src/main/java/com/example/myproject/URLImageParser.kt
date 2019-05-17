package com.example.myproject

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.text.Html.ImageGetter
import android.view.View
import kotlinx.android.synthetic.main.activity_content.view.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import android.R.drawable
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.DisplayMetrics
import java.lang.ref.WeakReference
import java.util.*




class URLImageParser
    (internal var container: View, private var c: Context) : ImageGetter {
    val mDrawableCache = Collections.synchronizedMap(WeakHashMap<String, WeakReference<Drawable>>())
    override fun getDrawable(source: String): Drawable {
        val urlDrawable = URLDrawable()
        if (mDrawableCache.containsKey(source))
            return mDrawableCache[source]!!.get()!!
        // get the actual source
        val asyncTask = ImageGetterAsyncTask(urlDrawable)

        asyncTask.execute(source)

        // return reference to URLDrawable where I will change with actual image from
        // the src tag
        return urlDrawable
    }

    inner class ImageGetterAsyncTask(internal var urlDrawable: URLDrawable?) : AsyncTask<String, Void, Drawable>() {

        override fun doInBackground(vararg params: String): Drawable? {
            val source = params[0]
            if (!mDrawableCache.containsKey(source)) {
                return fetchDrawable(source)
            }
            return null
        }

        @SuppressLint("ResourceType")
        override fun onPostExecute(result: Drawable? ) {
            if (result != null) {
                // set the correct bound according to the result from HTTP call
                urlDrawable?.setBounds(0, 0, 0 + result.intrinsicWidth + 400, 0 + result.intrinsicHeight + 400)
                // change the reference of the current drawable to the result
                // from the HTTP call
                urlDrawable?.drawable = result
                // redraw the image by invalidating the container
                this@URLImageParser.container.invalidate()
                container.texttest.setText(container.texttest.getText())
            }

        }
        private fun fetchDrawable(urlString: String?): Drawable? {
            try {
                val `is` = fetch(urlString!!)
                    val bmp = BitmapFactory.decodeStream(`is`)
                val dm = c.resources.displayMetrics
                bmp.density = dm.densityDpi
                val drawable = BitmapDrawable(c.getResources(),bmp)
                //val drawable = Drawable.createFromStream(`is`, "src")
                //`is`.close()
                drawable.setBounds(0, 0, 0 + drawable.intrinsicWidth, 0 + drawable.intrinsicHeight)
                return drawable
            } catch (e: Exception) {
                return null
            }

        }

        @Throws(MalformedURLException::class, IOException::class)
        private fun fetch(urlString: String?): InputStream {
            val httpClient = OkHttpClient.Builder().build()
            val request = Request.Builder().url(urlString!!).build()
            val response: InputStream = httpClient.newCall(request).execute().body()!!.byteStream()

            return response
        }
    }
}