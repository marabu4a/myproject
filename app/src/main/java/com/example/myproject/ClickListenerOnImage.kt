package com.example.myproject

import android.support.v4.view.MotionEventCompat.getSource
import android.text.style.URLSpan
import android.text.style.ImageSpan
import android.text.Spannable
import android.view.View

/*class ClickListenerOnImage {
    interface Callback {
        fun onImageClick(imageUrl: String?)
    }

    fun setClickListenerOnHtmlImageGetter(html: Spannable, callback: Callback) {
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
}*/