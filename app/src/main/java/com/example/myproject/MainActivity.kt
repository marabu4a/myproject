

package com.example.myproject
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity() {
    var adapter:DetailsAdapter?=null
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar2)
        val actionBar = supportActionBar
        actionBar?.title = "Устройство автомобиля"
        actionBar?.elevation = 4.0F
        actionBar?.subtitle = "Категории"
        val drawerToggle:ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer,
            toolbar2,
            R.string.open,
            R.string.close
        ){
            override fun onDrawerClosed(view: View){
                super.onDrawerClosed(view)
            }

            override fun onDrawerOpened(drawerView: View){
                super.onDrawerOpened(drawerView)
            }
        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        actionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        val categories = applicationContext.resources.getStringArray(R.array.categories)
        val categoriesannot = applicationContext.resources.getStringArray(R.array.categoriesAnnotation)
        adapter = DetailsAdapter(categories,categoriesannot)
        list.layoutManager = GridLayoutManager(applicationContext,2) as RecyclerView.LayoutManager?
        list.adapter = adapter
        list.hasFixedSize()
        list.background = ColorDrawable(Color.BLUE)


        val intent = Intent(this,ScndActivity::class.java)
        nav.setNavigationItemSelectedListener { menuItem ->
                menuItem.isChecked = true
                drawer.closeDrawers()
                when (menuItem.itemId) {
                    R.id.engine -> {
                        intent.putExtra(EXTRA_TITLE,"Двигатель")
                        intent.putExtra(EXTRA_POS,0)
                        this.startActivity(intent)
                    }
                    R.id.suspension -> {
                        intent.putExtra(EXTRA_TITLE,"Подвеска")
                        intent.putExtra(EXTRA_POS,1)
                        this.startActivity(intent)
                    }
                    R.id.transmission -> {
                        intent.putExtra(EXTRA_TITLE,"Трансмиссия")
                        intent.putExtra(EXTRA_POS,2)
                        this.startActivity(intent)
                    }
                    R.id.brakeSystem -> {
                        intent.putExtra(EXTRA_TITLE,"Тормозная система")
                        intent.putExtra(EXTRA_POS,3)
                        this.startActivity(intent)
                    }
                    R.id.electric -> {
                        intent.putExtra(EXTRA_TITLE,"Электрооборудование")
                        intent.putExtra(EXTRA_POS,4)
                        this.startActivity(intent)
                    }
                    R.id.wheel -> {
                        intent.putExtra(EXTRA_TITLE, "Рулевое управление")
                        intent.putExtra(EXTRA_POS,5)
                        this.startActivity(intent)
                    }
                    R.id.fuel -> {
                        intent.putExtra(EXTRA_TITLE,"Топливная система")
                        intent.putExtra(EXTRA_POS,6)
                        this.startActivity(intent)
                    }
                    R.id.sensors-> {
                        intent.putExtra(EXTRA_TITLE,"Датчики")
                        intent.putExtra(EXTRA_POS,7)
                        this.startActivity(intent)
                    }
                    R.id.cool-> {
                        intent.putExtra(EXTRA_TITLE,"Система охлаждения")
                        intent.putExtra(EXTRA_POS,8)
                        this.startActivity(intent)
                    }
                    R.id.igni-> {
                        intent.putExtra(EXTRA_TITLE, "Система зажигания")
                        intent.putExtra(EXTRA_POS,9)
                        this.startActivity(intent)
                    }
                }
            drawer.closeDrawer(GravityCompat.START)
            true




        }






    }









}


