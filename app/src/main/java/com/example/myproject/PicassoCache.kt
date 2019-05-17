package com.example.myproject

import android.annotation.SuppressLint
import android.content.Context
import com.squareup.picasso.Picasso
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Downloader
import org.jetbrains.anko.db.INTEGER


class PicassoCache(context: Context) {

    init {

        val downloader = OkHttp3Downloader(context, Long.MAX_VALUE)
        val builder = Picasso.Builder(context)
        builder.downloader(downloader)

        picassoInstance = builder.build()
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        var picassoInstance: Picasso? = null

        fun getPicassoInstance(context: Context): Picasso? {

            if (picassoInstance == null) {

                PicassoCache(context)
                return picassoInstance
            }

            return picassoInstance
        }
    }
}