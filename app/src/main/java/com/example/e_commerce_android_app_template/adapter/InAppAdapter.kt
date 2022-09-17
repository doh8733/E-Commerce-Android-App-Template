package com.example.e_commerce_android_app_template.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.ProductDetails
import com.example.e_commerce_android_app_template.R
import com.example.e_commerce_android_app_template.activities.DetailActivity
import com.google.android.material.button.MaterialButton

class InAppAdapter(
    val app: AppCompatActivity,
    val list: MutableList<ProductDetails>,
    private val billingClient: BillingClient
) : RecyclerView.Adapter<InAppAdapter.InAppViewHolder>() {

    fun loaddata( lst : List<ProductDetails>){
        if (lst.isNotEmpty()){
            list.clear()
            list.addAll(lst)
        }
        notifyDataSetChanged()
    }
    class InAppViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtMoney: TextView by lazy { view.findViewById<TextView>(R.id.txt_money) }
        val btnBuyGoi: MaterialButton by lazy { view.findViewById<MaterialButton>(R.id.btn_buy_goi) }
        val txtName: TextView by lazy { view.findViewById<TextView>(R.id.txt_Name) }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InAppViewHolder {
        return InAppViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_bill, parent, false)
        )
    }

    override fun onBindViewHolder(holder: InAppViewHolder, position: Int) {
        val item = list[position]
        holder.txtName.text = item.title
        holder.txtMoney.text = item.oneTimePurchaseOfferDetails!!.formattedPrice

        holder.btnBuyGoi.setOnClickListener {
            val params =
                BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails(item).build()
            val list = mutableListOf<BillingFlowParams.ProductDetailsParams>()
            list.add(params)
            val billingFlowParams =
                BillingFlowParams.newBuilder().setProductDetailsParamsList(list).build()
            val res: Int = billingClient.launchBillingFlow(app, billingFlowParams).responseCode
            when (res) {
                BillingClient.BillingResponseCode.BILLING_UNAVAILABLE -> {
                    Toast.makeText(app, "BILLING_UNAVAILABLE", Toast.LENGTH_SHORT)
                        .show()
                }
                BillingClient.BillingResponseCode.DEVELOPER_ERROR -> {
                    Toast.makeText(app, "DEVELOPER_ERROR", Toast.LENGTH_SHORT).show()
                }
                BillingClient.BillingResponseCode.FEATURE_NOT_SUPPORTED -> {
                    Toast.makeText(app, "FEATURE_NOT_SUPPORTED", Toast.LENGTH_SHORT)
                        .show()
                }
                BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> {
                    Toast.makeText(app, "ITEM_ALREADY_OWNED", Toast.LENGTH_SHORT)
                        .show()
                }
                BillingClient.BillingResponseCode.SERVICE_DISCONNECTED -> {
                    Toast.makeText(app, "SERVICE_DISCONNECTED", Toast.LENGTH_SHORT)
                        .show()
                }
                BillingClient.BillingResponseCode.SERVICE_TIMEOUT -> {
                    Toast.makeText(app, "SERVICE_TIMEOUT", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
        override fun getItemCount(): Int {
            return list.size
        }
    }