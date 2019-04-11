package com.example.myproject

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.CardView
import android.support.v7.widget.DividerItemDecoration
import android.telecom.Call
import android.view.View
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
        list.background = ColorDrawable(Color.BLACK)




    }



}
