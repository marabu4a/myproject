package com.example.myproject

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.category_details.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class DetailsCategoryAdapter(val articlesList: Array<String>?,val imageList:String?) : RecyclerView.Adapter<DetailsViewHolder>() {
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
        val tmp = "$imageList$position.jpg"
        Picasso.get().load(tmp).into(holder.view.image_category)
        holder.view.setOnClickListener {

        val intent = Intent(holder.view.context,ContentActivity::class.java)
            intent.putExtra(EXTRA_IMAGE,tmp)
            intent.putExtra(EXTRA_NAME,articlesList[position])
        holder.view.context.startActivity(intent)
        }

    }




}

class DetailsViewHolder (val view: View) : RecyclerView.ViewHolder(view) {

}
