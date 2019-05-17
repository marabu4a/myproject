package com.example.myproject

import android.graphics.ColorSpace
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.content.Intent.EXTRA_TITLE
import kotlinx.android.synthetic.main.category_details.view.*

class DetailsCategoryAdapter(val articlesList: Array<String>?) : RecyclerView.Adapter<DetailsViewHolder>() {
    override fun getItemCount(): Int {
        return articlesList!!.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val Row = layoutInflater.inflate(R.layout.category_details,parent,false)
        return DetailsViewHolder(Row)
    }


    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.view.name_category?.text = articlesList!![position]
        holder.view.image_category?.setImageResource(R.drawable.b320382)

        holder.view.setOnClickListener {

        val intent = Intent(holder.view.context,ContentActivity::class.java)
            intent.putExtra(EXTRA_NAME,articlesList!![position])
        holder.view.context.startActivity(intent)
        }

    }




}

class DetailsViewHolder (val view: View) : RecyclerView.ViewHolder(view) {

}
