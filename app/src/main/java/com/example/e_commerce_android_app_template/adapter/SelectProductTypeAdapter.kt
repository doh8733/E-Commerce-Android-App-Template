package com.example.e_commerce_android_app_template.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce_android_app_template.R
import com.example.e_commerce_android_app_template.activities.DetailActivity
import com.example.e_commerce_android_app_template.activities.FindTypeProductActivity
import com.example.e_commerce_android_app_template.fragment.BagFragment
import com.example.e_commerce_android_app_template.model.Product
import com.example.e_commerce_android_app_template.model.TypeProduct

class SelectProductTypeAdapter(
    val context: Context,
    var listTypeproduct: MutableList<TypeProduct> = mutableListOf(),
) : RecyclerView.Adapter<SelectProductTypeAdapter.SelectProductTypeviewholder>() {
    class SelectProductTypeviewholder(private val view: View) : RecyclerView.ViewHolder(view) {
        val imgBtnTypeProduct: ImageButton by lazy { view.findViewById<ImageButton>(R.id.imgBtnTypeProduct) }
        val tvProductType: TextView by lazy { view.findViewById<TextView>(R.id.tvProductType) }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectProductTypeviewholder {
        return SelectProductTypeviewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_select, parent, false)


        )

    }

    override fun onBindViewHolder(holder: SelectProductTypeviewholder, position: Int) {

        val item = listTypeproduct[position]
        holder.tvProductType.text = item.name
        holder.imgBtnTypeProduct.setOnClickListener {
            val id : String = item.id.toString()
            val name :String = item.name
            val intent = Intent(context, FindTypeProductActivity::class.java)
            intent.putExtra("id",id.toString())
            intent.putExtra("name",name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listTypeproduct.size
    }
}