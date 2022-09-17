package com.example.e_commerce_android_app_template.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerce_android_app_template.R
import com.example.e_commerce_android_app_template.model.Product
import com.google.android.material.button.MaterialButton

class BagProductAdapter(
    val context: Context,
    val listProduct: MutableList<Product> = mutableListOf(),
    val callback : (Int) -> Unit
) : RecyclerView.Adapter<BagProductAdapter.BagProductViewHolde>() {
    class BagProductViewHolde(private val view: View) : RecyclerView.ViewHolder(view) {
        val imgProduct: ImageView by lazy { view.findViewById<ImageView>(R.id.imgProduct) }
        val tvName: TextView by lazy { view.findViewById<TextView>(R.id.tvName) }
        val btnThem: ImageButton by lazy { view.findViewById<ImageButton>(R.id.btnThem) }
        val tvcount: TextView by lazy { view.findViewById<TextView>(R.id.tvCount) }
        val btnTru: ImageButton by lazy { view.findViewById<ImageButton>(R.id.btnTru) }
        val btnBuy: MaterialButton by lazy { view.findViewById<MaterialButton>(R.id.btnBuy) }
        val tvPrice: TextView by lazy { view.findViewById<TextView>(R.id.tvPrice) }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagProductViewHolde {
        val adapter =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product_bag, parent, false)
        return BagProductViewHolde(adapter)
    }

    override fun onBindViewHolder(holder: BagProductViewHolde, position: Int) {
        val item = listProduct[position]
        var count: Int = 1

        holder.btnBuy.setOnClickListener {
            callback.invoke(position)

        }
        Glide.with(context)
            .load(item.image)
            .centerCrop()
            .into(holder.imgProduct)
        holder.tvName.text = item.name
        holder.tvPrice.text = item.price.toString()
        holder.tvcount.text = count.toString()
        holder.btnThem.setOnClickListener {
            count++
            val sum = item.price * count
            holder.tvcount.text = count.toString()
            holder.tvPrice.text = sum.toString()
        }
        holder.btnTru.setOnClickListener {
            count--
            val sum = item.price * count
            holder.tvcount.text = count.toString()
            holder.tvPrice.text = sum.toString()
        }


    }

    override fun getItemCount(): Int {
        return listProduct.size
    }
}