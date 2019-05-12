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


class URLImageParser
/***
 * Construct the URLImageParser which will execute AsyncTask and refresh the container
 * @param t
 * @param c
 */
    (internal var container: View, internal var c: Context) : ImageGetter {

    override fun getDrawable(source: String): Drawable {
        val urlDrawable = URLDrawable()

        // get the actual source
        val asyncTask = ImageGetterAsyncTask(urlDrawable)

        asyncTask.execute(source)

        // return reference to URLDrawable where I will change with actual image from
        // the src tag
        return urlDrawable
    }

    inner class ImageGetterAsyncTask(internal var urlDrawable: URLDrawable) : AsyncTask<String, Void, Drawable>() {

        override fun doInBackground(vararg params: String): Drawable? {
            val source = params[0]
            return fetchDrawable(source)
        }

        override fun onPostExecute(result: Drawable ) {
            // set the correct bound according to the result from HTTP call
            urlDrawable.setBounds(0,0,  0 + result.intrinsicWidth+500, 0 + result.intrinsicHeight+500)
            // change the reference of the current drawable to the result
            // from the HTTP call
            urlDrawable.drawable = result


            // redraw the image by invalidating the container
            this@URLImageParser.container.invalidate()
            container.texttest.setText(container.texttest.getText())
        }
        private fun fetchDrawable(urlString: String): Drawable? {
            try {
                val `is` = fetch(urlString)
                val drawable = Drawable.createFromStream(`is`, "src")
                drawable.setBounds(0, 0, 0 + drawable.intrinsicWidth+500, 0 + drawable.intrinsicHeight+500)
                return drawable
            } catch (e: Exception) {
                return null
            }

        }

        @Throws(MalformedURLException::class, IOException::class)
        private fun fetch(urlString: String): InputStream {
            val httpClient = OkHttpClient.Builder().build()
            val request = Request.Builder().url(urlString).build()
            val response: InputStream = httpClient.newCall(request).execute().body()!!.byteStream()

            return response
        }
    }
}