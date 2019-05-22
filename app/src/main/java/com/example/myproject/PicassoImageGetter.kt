package com.example.myproject

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.BitmapDrawable
import com.squareup.picasso.Picasso
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.support.constraint.solver.widgets.WidgetContainer
import android.widget.TextView
import android.text.Html
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_content.view.*
import org.jetbrains.anko.displayManager
import org.jetbrains.anko.displayMetrics
import org.jetbrains.anko.windowManager


class PicassoImageGetter(internal var container: View, private var c: Context) : Html.ImageGetter {

    override fun getDrawable(source: String?): Drawable? {

        val drawable = BitmapDrawablePlaceHolder()
        Log.d("Activity", source)
       Log.d("Activity", drawable.toString())
        PicassoCache.getPicassoInstance(c)?.load(source)?.error(R.drawable.s1200)?.into(drawable)
        return drawable

    }

    private inner class BitmapDrawablePlaceHolder : BitmapDrawable(), com.squareup.picasso.Target {

        var draw: Drawable? = null

        override fun draw(canvas: Canvas) {
            if (draw != null) {
                draw!!.draw(canvas)
            }
        }

        fun setDrawable(drawable: Drawable?) {
            this.draw = drawable
            val displayMetrics = DisplayMetrics()
            c.windowManager.defaultDisplay.getMetrics(displayMetrics)
            val width = 0 + drawable!!.intrinsicWidth + 400
            val height = 0+ drawable.intrinsicHeight + 400

            if (width <= container.texttest.width) {
                drawable.setBounds(0, 0, width, height)
                setBounds(0, 0, width, height)
            }
            else {
                drawable.setBounds(0,0,drawable!!.intrinsicWidth,drawable.intrinsicHeight)
                setBounds(0,0,drawable!!.intrinsicWidth,drawable.intrinsicHeight)
            }
            if (container.texttest.text != null) {
                container.texttest.text = container.texttest.text
            }
        }

        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom) {
            setDrawable(BitmapDrawable(c.resources, bitmap))
        }

        override fun onBitmapFailed(e: Exception, errorDrawable: Drawable?) {
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        }

    }
}