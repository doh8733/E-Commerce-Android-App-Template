package com.example.e_commerce_android_app_template.adapter

import android.app.Application
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.ProductDetails
import com.bumptech.glide.Glide
import com.example.e_commerce_android_app_template.R
import com.example.e_commerce_android_app_template.activities.DetailActivity
import com.example.e_commerce_android_app_template.model.Product
import com.example.e_commerce_android_app_template.viewmodel.ProductViewModel
import com.google.android.material.button.MaterialButton

class ProductAdapter(
    private val context: Context?,
    private var dataProduct: MutableList<Product> = mutableListOf(),
) : RecyclerView.Adapter<ProductAdapter.ProductsViewholder>() {

    class ProductsViewholder(private val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView by lazy { view.findViewById<ImageView>(R.id.imageView) }
        val tvNameProduct: TextView by lazy { view.findViewById<TextView>(R.id.tvNameProduct) }
        val tvPriceProduct: TextView by lazy { view.findViewById<TextView>(R.id.tvPriceProduct) }
        val layout: CardView by lazy { view.findViewById<CardView>(R.id.layout) }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewholder {
        val adapter =
            LayoutInflater.from(parent.context).inflate(R.layout.gird_layout_product, parent, false)
        return ProductsViewholder(adapter)
    }

    override fun onBindViewHolder(holder: ProductsViewholder, position: Int) {
        var item = dataProduct[position]
        holder.imageView.setImageResource(R.drawable.product)
        holder.tvNameProduct.text = item.name.toString()
        holder.tvPriceProduct.text = item.price.toString()

        Glide.with(context!!)
            .load(item.image)
            .centerCrop()
            .into(holder.imageView)

//        holder.itemView.setOnClickListener {
//            callback.invoke(position)
//        }
        holder.layout.setOnClickListener {
            val id: String = item.id.toString()
            val image: String = item.image
            val name: String = item.name
            val price: Float = item.price
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", id.toString())
            intent.putExtra("image", image.toString())
            intent.putExtra("name", name)
            intent.putExtra("price", price)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return dataProduct.size
    }
}