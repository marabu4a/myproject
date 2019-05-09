package com.example.myproject

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article.view.*

class ContentAdapter(list:ArrayList<String>): RecyclerView.Adapter<ContentRecyclerView>() {
    val temp = list
    override fun getItemCount(): Int {
        return temp.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRecyclerView {
        val layoutInflater = LayoutInflater.from(parent.context)
        val Row = layoutInflater.inflate(R.layout.article,parent,false)
        return ContentRecyclerView(Row)
    }

    override fun onBindViewHolder(holder: ContentRecyclerView, position: Int) {
        val now = temp.get(position)
        if (now[0] == 'f') {
            Picasso.get().load(now).into(holder.view.image_content)
        }
        else {
            holder.view.content.text = now
        }

        }

    }


class ContentRecyclerView (val view: View) : RecyclerView.ViewHolder(view) {
}