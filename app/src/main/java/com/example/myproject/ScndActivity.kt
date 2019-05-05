package com.example.myproject

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_scnd.*
val EXTRA_POS = "my_item_position"

class ScndActivity : AppCompatActivity() {
    val categoryList:ArrayList<Model> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scnd)
        categoryView.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        val my_item_position = (intent.extras!!.get(EXTRA_POS) as Int).toInt()
        checkCategoty(my_item_position)
        categoryView.hasFixedSize()
        val clAdapter = DetailsCategoryAdapter(categoryList)
        categoryView.adapter = clAdapter
        val itemDecoration =  DividerItemDecoration(applicationContext,LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(ColorDrawable(Color.BLUE))
        categoryView.addItemDecoration(itemDecoration)
    }
    fun addCategory() {
        categoryList.add(Model("Устройство двигателей внутреннего сгорания",R.drawable.dvs))
        categoryList.add(Model("Принцип работы ДВС",R.drawable.dvs))
        categoryList.add(Model("Дизельные двигатели",R.drawable.dvs))
        categoryList.add(Model("Автомобильный термостат",R.drawable.b320382))
        categoryList.add(Model("Механический нагнетатель",R.drawable.b320382))
        categoryList.add(Model("Фазы газораспределения двигателя",R.drawable.b320382))
        categoryList.add(Model("Автомобильный трубокомпрессор",R.drawable.b320382))
    }
    fun addCategory1() {
        categoryList.add(Model("-------------",R.drawable.b320382))
        categoryList.add(Model("---------------------------",R.drawable.b320382))
        categoryList.add(Model("Заглушка",R.drawable.b320382))
        categoryList.add(Model("-----",R.drawable.b320382))
    }

    fun checkCategoty(my_item_position: Int) {
        if (my_item_position == 0) {
            addCategory()
        }
        else {
            addCategory1()
        }
    }


}
