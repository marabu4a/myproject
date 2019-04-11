package com.example.myproject

import android.graphics.ColorSpace
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_scnd.*

class ScndActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scnd)
        categoryView.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)

        val categoryList = ArrayList<Model>()
        val clAdapter = DetailsCategoryAdapter(categoryList)
        categoryView.adapter = clAdapter
    }
}
