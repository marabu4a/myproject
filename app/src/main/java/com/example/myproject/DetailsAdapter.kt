package com.example.myproject
import android.content.Intent
import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.list_item.view.*
import android.support.v4.content.ContextCompat.startActivity
class DetailsAdapter : RecyclerView.Adapter<CustomViewHolder>() {
    val listDetails = listOf("Общее","Двигатель","Подвеска и рулевое управление","Трансмиссия","Тормозная система","Электрооборудование")
    override fun getItemCount(): Int {
        return listDetails.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val Row = layoutInflater.inflate(R.layout.list_item,parent,false)
        return CustomViewHolder(Row)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val detailTitle = listDetails.get(position)
        holder.view.car_detail?.text = detailTitle
        if (position % 2 == 0) {
            holder.view.car_detail.setBackgroundResource(R.color.colorPrimary)
        }
        else {
            holder.view.car_detail.setBackgroundResource(R.color.cardview_shadow_start_color)
        }




    }
}
class CustomViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
    init {
        view.setOnClickListener {
            val intent = Intent(view.context,ScndActivity::class.java)
            view.context.startActivity(intent)
        }
    }
}