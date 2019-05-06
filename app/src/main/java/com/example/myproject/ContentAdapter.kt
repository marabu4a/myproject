package com.example.myproject

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class ContentAdapter: RecyclerView.Adapter<ContentRecyclerView>() {
    override fun getItemCount(): Int {
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRecyclerView {
        val layoutInflater = LayoutInflater.from(parent.context)
        val Row = layoutInflater.inflate(R.layout.article,parent,false)
        return ContentRecyclerView(Row)
    }

    override fun onBindViewHolder(holder: ContentRecyclerView, position: Int) {
        val detailTitle = .get(position)

    }

}

class ContentRecyclerView (val view: View) : RecyclerView.ViewHolder(view) {
}