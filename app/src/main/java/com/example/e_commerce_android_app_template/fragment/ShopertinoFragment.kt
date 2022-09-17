package com.example.e_commerce_android_app_template.fragment

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.*
import com.example.e_commerce_android_app_template.R
import com.example.e_commerce_android_app_template.activities.InAppActivity
import com.example.e_commerce_android_app_template.adapter.BagProductAdapter
import com.example.e_commerce_android_app_template.model.Product
import com.example.e_commerce_android_app_template.viewmodel.ProductViewModel
import com.google.android.material.button.MaterialButton
import com.google.common.collect.ImmutableList

private const val inapp_type_1 = "free_image_animal_15_day"
private const val inapp_type_2 = "free_image_animal_1_day"
private const val inapp_type_3 = "free_image_animal_30_day"
private const val inapp_type_4 = "free_image_animal_3_day"
private const val inapp_type_5 = "free_image_animal_7_day"

class ShopertinoFragment : Fragment() {

    private val productViewModel: ProductViewModel by lazy {
        ViewModelProvider(
            this, ProductViewModel.ProductFactory(
                context?.applicationContext as Application
            )
        )[ProductViewModel::class.java]
    }
    private var listDetailProduct: List<ProductDetails> = mutableListOf()
    private lateinit var billingClient: BillingClient
    var list: LiveData<List<Product>> = MutableLiveData()
    private lateinit var adapterBag: BagProductAdapter
    val tvSum: TextView by lazy { requireView().findViewById<TextView>(R.id.tvSum) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopertino, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rcvViewBag: RecyclerView by lazy { view.findViewById<RecyclerView>(R.id.rcvViewBag) }
        val btnBuyPakage: MaterialButton by lazy { view.findViewById<MaterialButton>(R.id.btnBuyPakage) }

        val purchasesUpdatedListener = PurchasesUpdatedListener { billingResult, purchases ->

        }
        billingClient = BillingClient.newBuilder(requireContext())
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()

        list = productViewModel.getAllProduct()
        productViewModel.getAllProduct().observe(viewLifecycleOwner) {
            adapterBag = BagProductAdapter(requireContext(), it as MutableList<Product>) { post ->
//                val productDetailsParamsList = listOf(
//                    BillingFlowParams.ProductDetailsParams.newBuilder()
//                        .setProductDetails(listDetailProduct[post])
//                        .build()
//                )
//                val billingflowParams = BillingFlowParams.newBuilder()
//                    .setProductDetailsParamsList(productDetailsParamsList)
//                    .build()
//                val billingResult = billingClient.launchBillingFlow(requireContext() as Activity, billingflowParams)
//                Log.d("objectProduct ", "onCreate: $post")

            }
            rcvViewBag.layoutManager = LinearLayoutManager(context)
            rcvViewBag.adapter = adapterBag
            var sum: Int = 0
        }
        btnBuyPakage.setOnClickListener {
            startActivity(Intent(requireContext(),InAppActivity::class.java))
        }
//        buildProductBillingFunc()
    }

    private fun buildProductBillingFunc() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {

            }

            override fun onBillingSetupFinished(p0: BillingResult) {
                if (p0.responseCode == BillingClient.BillingResponseCode.OK) {
                    val queryProductDetailsParams = QueryProductDetailsParams
                        .newBuilder().setProductList(
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
                        Log.d("log1", "onBillingSetupFinished: " + productDetailsList.size)
                        listDetailProduct = productDetailsList
                        for (i in listDetailProduct.indices)
                            tvSum.text = listDetailProduct[i].name
                    }
                }
            }
        })
    }
}