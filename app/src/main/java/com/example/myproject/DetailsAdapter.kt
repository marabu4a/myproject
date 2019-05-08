package com.example.myproject
import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*


class DetailsAdapter(array: Array<String>) : RecyclerView.Adapter<CustomViewHolder>() {
    val arrayy = array
    val categories = R.array.categories

    override fun getItemCount(): Int {
        return listDetails.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val Row = layoutInflater.inflate(R.layout.list_item,parent,false)
        return CustomViewHolder(Row)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val detailTitle = arrayy.get(position)
        val imgName = "file:///android_asset/maincategories/engine.png"
        Picasso.get().load(imgName).into(holder.view.car_detail_image)
        holder.view.car_detail?.text = detailTitle
        holder.view.annot?.text = "заглушка,заглушка"
        holder.view.setOnClickListener {

                val intent = Intent(holder.view.getContext(),ScndActivity::class.java)
                intent.putExtra(EXTRA_POS,position)
                holder.view.getContext().startActivity(intent)
        }
    }
}
class CustomViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
}