package com.example.myproject

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_scnd.*
val EXTRA_POS = "category_position"

class ScndActivity : AppCompatActivity() {
    var articles: Array<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        //val array = applicationContext.resources.getStringArray(R.array.categories)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scnd)
        categoryView.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        val category_position = (intent.extras!!.get(EXTRA_POS) as Int).toInt()
        categoryView.hasFixedSize()
        chooseArticles(category_position)
        val clAdapter = DetailsCategoryAdapter(articles)
        categoryView.adapter = clAdapter
        val itemDecoration =  DividerItemDecoration(applicationContext,LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(ColorDrawable(Color.BLUE))
        categoryView.addItemDecoration(itemDecoration)
    }


    fun chooseArticles(position: Int) {
        when (position) {
            0 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesEngine)
            }
            1 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesSuspension)
            }
            2 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesTransmission)
            }
            3 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesBrakeSystem)
            }
            4 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesElectric)
            }
            5 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesSteeringWheel)
            }
            6 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesFuelSystem)
            }
            7 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesSensors)
            }
            8 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesCoolingSystem)
            }
            9 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesIgnitionSystem)
            }

        }
    }


}
