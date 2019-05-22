package com.example.myproject

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_scnd.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.startActivity

val EXTRA_POS = "category_position"
val EXTRA_TITLE = "category_title"

class ScndActivity : AppCompatActivity() {
    var path: String? = null
    var articles: Array<String>? = null
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scnd)
        setSupportActionBar(toolbar3)
        val category_title = (intent.extras!!.get(EXTRA_TITLE) as String).toString()
        val actionBar = supportActionBar
        actionBar?.title = category_title
        actionBar?.elevation = 4.0F
        categoryView.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)

        val category_position = (intent.extras!!.get(EXTRA_POS) as Int).toInt()

        categoryView.hasFixedSize()
        chooseArticles(category_position)
        val clAdapter = DetailsCategoryAdapter(articles,path)
        categoryView.adapter = clAdapter
        val itemDecoration =  DividerItemDecoration(applicationContext,LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(ColorDrawable(Color.BLUE))
        categoryView.addItemDecoration(itemDecoration)
        val intent = Intent(this,ScndActivity::class.java)
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer1,
            toolbar3,
            R.string.open,
            R.string.close
        ){
            override fun onDrawerClosed(view: View){
                super.onDrawerClosed(view)
            }

            override fun onDrawerOpened(drawerView: View){
                super.onDrawerOpened(drawerView)
                //toast("Drawer opened")
            }
        }
        actionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer1.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        nav1.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawer1.closeDrawers()
            when (menuItem.itemId) {
                R.id.engine -> {
                    intent.putExtra(EXTRA_TITLE,"Двигатель")
                    intent.putExtra(EXTRA_POS,0)
                    finish()
                    this.startActivity(intent)
                }
                R.id.suspension -> {
                    intent.putExtra(EXTRA_TITLE,"Подвеска")
                    intent.putExtra(EXTRA_POS,1)
                    finish()
                    this.startActivity(intent)
                }
                R.id.transmission -> {
                    intent.putExtra(EXTRA_TITLE,"Трансмиссия")
                    intent.putExtra(EXTRA_POS,2)
                    finish()
                    this.startActivity(intent)
                }
                R.id.brakeSystem -> {
                    intent.putExtra(EXTRA_TITLE,"Тормозная система")
                    intent.putExtra(EXTRA_POS,3)
                    finish()
                    this.startActivity(intent)
                }
                R.id.electric -> {
                    intent.putExtra(EXTRA_TITLE,"Электрооборудование")
                    intent.putExtra(EXTRA_POS,4)
                    finish()
                    this.startActivity(intent)
                }
                R.id.wheel -> {
                    intent.putExtra(EXTRA_TITLE, "Рулевое управление")
                    intent.putExtra(EXTRA_POS,5)
                    finish()
                    this.startActivity(intent)
                }
                R.id.fuel -> {
                    intent.putExtra(EXTRA_TITLE,"Топливная система")
                    intent.putExtra(EXTRA_POS,6)
                    finish()
                    this.startActivity(intent)
                }
                R.id.sensors-> {
                    intent.putExtra(EXTRA_TITLE,"Датчики")
                    intent.putExtra(EXTRA_POS,7)
                    finish()
                    this.startActivity(intent)
                }
                R.id.cool-> {
                    intent.putExtra(EXTRA_TITLE,"Система охлаждения")
                    intent.putExtra(EXTRA_POS,8)
                    finish()
                    this.startActivity(intent)
                }
                R.id.igni-> {
                    intent.putExtra(EXTRA_TITLE, "Система зажигания")
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
                path = "file:///android_asset/categoryEngine/engine-"
            }
            1 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesSuspension)
                path = "file:///android_asset/categorySuspension/suspension-"
            }
            2 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesTransmission)
                path = "file:///android_asset/categoryTransmission/tr-"
            }
            3 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesBrakeSystem)
                path = "file:///android_asset/categoryBrake/brake-"
            }
            4 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesElectric)
                path = "file:///android_asset/categoryElectric/el-"
            }
            5 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesSteeringWheel)
                path = "file:///android_asset/categoryWheel/wheel-"
            }
            6 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesFuelSystem)
                path = "file:///android_asset/categoryFuel/fuel-"
            }
            7 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesSensors)
                path = "file:///android_asset/categorySensors/sensor-"
            }
            8 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesCoolingSystem)
                path = "file:///android_asset/categoryCool/cool-"
            }
            9 -> {
                articles = applicationContext.resources.getStringArray(R.array.articlesIgnitionSystem)
                path = "file:///android_asset/categoryIgni/igni-"
            }

        }
    }


}
