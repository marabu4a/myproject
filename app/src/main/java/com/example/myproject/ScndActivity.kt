package com.example.myproject

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_scnd.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.startActivity

val EXTRA_POS = "category_position"

class ScndActivity : AppCompatActivity() {
    var articles: Array<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scnd)
        setSupportActionBar(toolbar3)

        val actionBar = supportActionBar
        actionBar?.title = "Устройство автомобиля"
        actionBar?.elevation = 4.0F

        categoryView.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        val category_position = (intent.extras!!.get(EXTRA_POS) as Int).toInt()
        categoryView.hasFixedSize()
        chooseArticles(category_position)
        val clAdapter = DetailsCategoryAdapter(articles)
        categoryView.adapter = clAdapter
        val itemDecoration =  DividerItemDecoration(applicationContext,LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(ColorDrawable(Color.BLUE))
        categoryView.addItemDecoration(itemDecoration)
        val intent = Intent(this,ScndActivity::class.java)
        nav1.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawer1.closeDrawers()
            when (menuItem.itemId) {
                R.id.engine -> {
                    intent.putExtra(EXTRA_POS,0)
                    finish()
                    this.startActivity(intent)
                }
                R.id.suspension -> {
                    intent.putExtra(EXTRA_POS,1)
                    finish()
                    this.startActivity(intent)
                }
                R.id.transmission -> {
                    intent.putExtra(EXTRA_POS,2)
                    finish()
                    this.startActivity(intent)
                }
                R.id.brakeSystem -> {
                    intent.putExtra(EXTRA_POS,3)
                    finish()
                    this.startActivity(intent)
                }
                R.id.electric -> {
                    intent.putExtra(EXTRA_POS,4)
                    finish()
                    this.startActivity(intent)
                }
                R.id.wheel -> {
                    intent.putExtra(EXTRA_POS,5)
                    finish()
                    this.startActivity(intent)
                }
                R.id.fuel -> {
                    intent.putExtra(EXTRA_POS,6)
                    finish()
                    this.startActivity(intent)
                }
                R.id.sensors-> {
                    intent.putExtra(EXTRA_POS,7)
                    finish()
                    this.startActivity(intent)
                }
                R.id.cool-> {
                    intent.putExtra(EXTRA_POS,8)
                    finish()
                    this.startActivity(intent)
                }
                R.id.igni-> {
                    intent.putExtra(EXTRA_POS,9)
                    finish()
                    this.startActivity(intent)
                }
            }
            true




        }
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
