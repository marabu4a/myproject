package com.example.myproject
import android.widget.ImageView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_content.*

class ContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        text_content.text = "Двигатель внутреннего сгорания - это самый распространенный двигатель из всех , котоыре устанавливаются в настоящее время на автомобили"



    }





}
