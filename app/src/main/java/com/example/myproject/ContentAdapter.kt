package com.example.myproject

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.text.HtmlCompat
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
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
        if (position == 0) {

            holder.view.content.text = HtmlCompat.fromHtml(now,0)
            holder.view.content.movementMethod = ScrollingMovementMethod()
        }
        else {
            holder.view.content.text = now
            holder.view.content.movementMethod = ScrollingMovementMethod()
            holder.view.title_article.movementMethod = ScrollingMovementMethod()
        }
        }

    }


class ContentRecyclerView (val view: View) : RecyclerView.ViewHolder(view) {
}