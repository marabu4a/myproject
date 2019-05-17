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
import android.util.Log
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_content.view.*
import org.jetbrains.anko.displayManager
import org.jetbrains.anko.displayMetrics


class PicassoImageGetter(internal var container: View, private var c: Context) : Html.ImageGetter {
   // private var textView: TextView? = target
    //internal var mContext: Context = context
    override fun getDrawable(source: String?): Drawable? {

        val drawable = BitmapDrawablePlaceHolder()
        Log.d("Activity", source)
       Log.d("Activity", drawable.toString())
           //Picasso.get().load(source).placeholder(R.drawable.s1200).into(drawable)
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
            val width = 0 + drawable!!.intrinsicWidth + 400
            val height = 0+ drawable.intrinsicHeight + 400
            /*val defaultWidth = drawable?.intrinsicWidth
            val defaultHeight = drawable?.intrinsicHeight
            val defaultProportion: Float = (defaultWidth!! / defaultHeight!!).toFloat()
            val width = Math.min((container.texttest.width),defaultWidth)
            val height = (width / defaultProportion).toInt()
                //if ((bounds.right != container.texttest.width || bounds.bottom != height)) {
                setBounds(0,0,container.texttest.width,height)
                val halfOfPlaceHolderWidth = (bounds.right.toFloat() / 2f).toInt()
                val halfOfImageWidth = (width.toFloat() / 2f).toInt()
                drawable.setBounds(halfOfPlaceHolderWidth-halfOfImageWidth,0,halfOfPlaceHolderWidth+halfOfImageWidth,height)
                container.texttest.text = container.texttest.text*/
            //}
            drawable.setBounds(0, 0, width, height)
            setBounds(0, 0, width, height)
                //this@PicassoImageGetter.container.invalidate()
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