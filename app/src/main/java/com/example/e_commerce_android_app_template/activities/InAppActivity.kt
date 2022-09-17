package com.example.e_commerce_android_app_template.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.*
import com.example.e_commerce_android_app_template.R
import com.example.e_commerce_android_app_template.adapter.InAppAdapter
import com.google.android.material.button.MaterialButton
import com.google.common.collect.ImmutableList

private const val inapp_type_1 = "free_image_animal_15_day"
private const val inapp_type_2 = "free_image_animal_1_day"
private const val inapp_type_3 = "free_image_animal_30_day"
private const val inapp_type_4 = "free_image_animal_3_day"
private const val inapp_type_5 = "free_image_animal_7_day"

class InAppActivity : AppCompatActivity(), ProductDetailsResponseListener,
    PurchasesUpdatedListener {
    private lateinit var billingClient: BillingClient
    private var listDetails = mutableListOf<ProductDetails>()
    private lateinit var adapter: InAppAdapter
    private val rcView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rcView) }
    private val btnGetData: MaterialButton by lazy { findViewById<MaterialButton>(R.id.btnGetData) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_app)

        buildingBillingFunc()
        adapter = InAppAdapter(this, listDetails, billingClient)
        rcView.layoutManager = LinearLayoutManager(this)
        rcView.adapter = InAppAdapter(this, listDetails, billingClient)
//        btnGetData.setOnClickListener {
//            if (billingClient.isReady){
//                adapter.loaddata(listDetails)
//            }
//        }
    }

    private fun buildingBillingFunc() {
        val purchasesUpdatedListener = PurchasesUpdatedListener { billingResult, mutableList -> }
        billingClient = BillingClient.newBuilder(this)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {

            }

            override fun onBillingSetupFinished(p0: BillingResult) {
                if (p0.responseCode == BillingClient.BillingResponseCode.OK) {
                    val queryProductDetailsParams =
                        QueryProductDetailsParams.newBuilder()
                            .setProductList(
                                ImmutableList.of(
                                    QueryProductDetailsParams.Product.newBuilder()
                                        .setProductId(inapp_type_1)
                                        .setProductType(BillingClient.ProductType.INAPP)
                                        .build(),
                                    QueryProductDetailsParams.Product.newBuilder()
                                        .setProductId(inapp_type_2)
                                        .setProductType(BillingClient.ProductType.INAPP)
                                        .build(),
                                    QueryProductDetailsParams.Product.newBuilder()
                                        .setProductId(inapp_type_3)
                                        .setProductType(BillingClient.ProductType.INAPP)
                                        .build(),
                                    QueryProductDetailsParams.Product.newBuilder()
                                        .setProductId(inapp_type_4)
                                        .setProductType(BillingClient.ProductType.INAPP)
                                        .build(),
                                    QueryProductDetailsParams.Product.newBuilder()
                                        .setProductId(inapp_type_5)
                                        .setProductType(BillingClient.ProductType.INAPP)
                                        .build()
                                )
                            ).build()
                    billingClient.queryProductDetailsAsync(queryProductDetailsParams) { billingResult, productDetailsList ->
                        listDetails = productDetailsList
                        Log.d("logtag", "onBillingSetupFinished: ${listDetails}")
//                        rcView.adapter = InAppAdapter(this@InAppActivity, listDetails, billingClient)
                        rcView.layoutManager = LinearLayoutManager(this@InAppActivity)
                        rcView.adapter = InAppAdapter(this@InAppActivity, listDetails, billingClient)

                    }

                }
            }

        })
    }

    fun loadRcv(lst: List<ProductDetails>) {
        runOnUiThread {
            adapter.loaddata(lst)
        }
    }
    private fun handleItemAlrealyPurchases(purchases: List<Purchase>) {
//        val inappItem: StringBuilder = StringBuilder(txtPremium.getText())
        buildingBillingFunc()
//        for(Purchase purchase:purchases){
//            if(purchase.get().equals("id_product_inapp1")||purchase.getSku().equals("id_product_inapp2")||purchase.getSku().equals("id_product_inapp3")||purchase.getSku().equals("id_product_inapp4")){
//                ConsumeParams consumeParams=ConsumeParams.newBuilder()
//                        .setPurchaseToken(purchase.getPurchaseToken())
//                        .build();
//                billingClient.consumeAsync(consumeParams,listener);
//            }
//            inappItem.append("\n"+purchase.getSku())
//                    .append("\n");
//        }
//        txtPremium.setText(inappItem.toString());
//        txtPremium.setVisibility(View.VISIBLE);
    }
    override fun onProductDetailsResponse(p0: BillingResult, p1: MutableList<ProductDetails>) {
        TODO("Not yet implemented")
    }

    override fun onPurchasesUpdated(p0: BillingResult, p1: MutableList<Purchase>?) {
        if (p0.getResponseCode() == BillingClient.BillingResponseCode.OK && p1 != null)
            handleItemAlrealyPurchases(p1)
    }
}