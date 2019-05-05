package com.example.myproject
import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*



class DetailsAdapter : RecyclerView.Adapter<CustomViewHolder>() {
    val listDetails = listOf(
        "Двигатель",
        "Подвеска",
        "Трансмиссия",
        "Тормозная система",
        "Электрооборудование",
        "Рулевое управление",
        "Топливная система",
        "Датчики",
        "Система охлаждения",
        "Система зажигания")



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
        val detailTitle = listDetails.get(position)
        val imgName = "file:///android_asset/maincategories/engine.png"
        Picasso.get().load(imgName).into(holder.view.car_detail_image)
        holder.view.car_detail?.text = detailTitle
        holder.view.annot?.text = "заглушка,заглушка"
        holder.view.setOnClickListener {

                val intent = Intent(holder.view.getContext(),ScndActivity::class.java)
                intent.putExtra(EXTRA_POS,position)
                holder.view.getContext().startActivity(intent)

                //val intent = Intent(view.getContext(), HotelViewActivity::class.java)
            //intent.putExtra(HotelViewActivity.EXTRA_POS, position)
            //view.getContext().startActivity(intent)

        }




    }
}
class CustomViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
    /*init {
        view.setOnClickListener {
            val intent = Intent(view.context,ScndActivity::class.java)
            view.context.startActivity(intent)


        }
    }
    */
}