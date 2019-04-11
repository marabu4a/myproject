package com.example.myproject

import android.graphics.ColorSpace
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import kotlinx.android.synthetic.main.category_details.view.*

class DetailsCategoryAdapter(val categoryList: ArrayList<Model>) : RecyclerView.Adapter<DetailsViewHolder>() {
    override fun getItemCount(): Int {
        return categoryList.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val Row = layoutInflater.inflate(R.layout.category_details,parent,false)
        return DetailsViewHolder(Row)
    }


    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.view.name_category?.text = categoryList[position].categ
        holder.view.image_category?.setImageResource(categoryList[position].image)

        holder.view.setOnClickListener {

        val intent = Intent(holder.view.context,ContentActivity::class.java)
        holder.view.context.startActivity(intent)
        }

    }




}

class DetailsViewHolder (val view: View) : RecyclerView.ViewHolder(view) {

}
