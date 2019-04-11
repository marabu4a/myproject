package com.example.myproject

import android.graphics.ColorSpace
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

class DetailsCategoryAdapter(val categoryList: ArrayList<Model>) : RecyclerView.Adapter<DetailsViewHolder>() {
    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(p0: DetailsViewHolder, p1: Int) {
        val view
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DetailsViewHolder {

    }

}

class DetailsViewHolder (val view: View) : RecyclerView.ViewHolder(view) {

}
