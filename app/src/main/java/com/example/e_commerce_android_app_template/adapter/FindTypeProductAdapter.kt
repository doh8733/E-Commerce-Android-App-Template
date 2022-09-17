package com.example.e_commerce_android_app_template.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerce_android_app_template.R
import com.example.e_commerce_android_app_template.activities.DetailActivity
import com.example.e_commerce_android_app_template.activities.FindTypeProductActivity
import com.example.e_commerce_android_app_template.model.Product
import com.example.e_commerce_android_app_template.model.StoreProduct

class FindTypeProductAdapter(
    val context: FindTypeProductActivity,
    var list: MutableList<StoreProduct> = mutableListOf()
):
    RecyclerView.Adapter<FindTypeProductAdapter.FindTyperProductViewHolder>() {
    class FindTyperProductViewHolder(view :View) : RecyclerView.ViewHolder(view) {
         val imageView: ImageView by lazy { view.findViewById<ImageView>(R.id.imageView) }
         val tvNameProduct: TextView by lazy { view.findViewById<TextView>(R.id.tvNameProduct) }
         val tviconPrice: TextView by lazy { view.findViewById<TextView>(R.id.tviconPrice) }
         val tvPriceProduct: TextView by lazy { view.findViewById<TextView>(R.id.tvPriceProduct) }
         val layout: CardView by lazy { view.findViewById<CardView>(R.id.layout) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindTyperProductViewHolder {
        return FindTyperProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_produc_type,parent,false))
    }

    override fun onBindViewHolder(holder: FindTyperProductViewHolder, position: Int) {
        val item = list[position]
        holder.tvNameProduct.text = item.name
        Glide.with(context).load(item.image).centerCrop().into(holder.imageView)
        holder.tvPriceProduct.text = item.price.toString()

        holder.layout.setOnClickListener {
            val id : String = item.id.toString()
            val image :String = item.image
            val name :String = item.name
            val price : Float = item.price
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id",id.toString())
            intent.putExtra("image",image.toString())
            intent.putExtra("name",name)
            intent.putExtra("price",price)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}