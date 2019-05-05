@file:Suppress("DEPRECATION")

package com.example.myproject
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {


    var adapter:DetailsAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        adapter = DetailsAdapter()
        list.layoutManager = GridLayoutManager(applicationContext,2) as RecyclerView.LayoutManager?
        list.adapter = adapter
        //val itemDecoration =  DividerItemDecoration(applicationContext,GridLayoutManager.VERTICAL)
        //itemDecoration.setDrawable(ColorDrawable(Color.BLUE))
        //list.addItemDecoration(itemDecoration)
        list.hasFixedSize()
        list.background = ColorDrawable(Color.BLUE)






    }

    fun loadImageFromAsset(name: String): Drawable? {

        val stream = applicationContext.assets.open("databases/$name")
        val d = Drawable.createFromStream(stream, null)
        return d
    }









}


