package com.example.myproject

import android.support.v7.widget.RecyclerView
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.article.view.*

class ContentAdapter(list:List<String>): RecyclerView.Adapter<ContentRecyclerView>() {
    private val temp = list
    override fun getItemCount(): Int {
        return temp.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRecyclerView {
        val layoutInflater = LayoutInflater.from(parent.context)
        val Row = layoutInflater.inflate(R.layout.article,parent,false)
        return ContentRecyclerView(Row)
    }

    override fun onBindViewHolder(holder: ContentRecyclerView, position: Int) {
        val now = temp[position]
        }
    }


class ContentRecyclerView (val view: View) : RecyclerView.ViewHolder(view) {
}