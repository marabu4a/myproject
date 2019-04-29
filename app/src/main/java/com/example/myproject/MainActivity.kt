@file:Suppress("DEPRECATION")

package com.example.myproject
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    var adapter:DetailsAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
















        adapter = DetailsAdapter()
        list.layoutManager = LinearLayoutManager(applicationContext)
        list.adapter = adapter
        val itemDecoration =  DividerItemDecoration(applicationContext,LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(ColorDrawable(Color.BLUE))
        list.addItemDecoration(itemDecoration)
        list.hasFixedSize()
        list.background = ColorDrawable(Color.WHITE)





    }










}


