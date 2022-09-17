package com.example.e_commerce_android_app_template.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.*
import com.example.e_commerce_android_app_template.R
import com.example.e_commerce_android_app_template.adapter.ProductAdapter
import com.example.e_commerce_android_app_template.model.Product
import com.google.common.collect.ImmutableList


private const val inapp_type_1 = "free_image_animal_15_day"
private const val inapp_type_2 = "free_image_animal_1_day"
private const val inapp_type_3 = "free_image_animal_30_day"
private const val inapp_type_4 = "free_image_animal_3_day"
private const val inapp_type_5 = "free_image_animal_7_day"

class ShopFragment : Fragment() {
    private var listProduct: MutableList<Product> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    private var listdetailprd = mutableListOf<ProductDetails>()
    private lateinit var billingClient: BillingClient
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val purchasesUpdatedListener = PurchasesUpdatedListener { billingResult, mutableList -> }
        billingClient = BillingClient.newBuilder(requireContext())
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()

        val rcViewProduct: RecyclerView by lazy { view.findViewById<RecyclerView>(R.id.rcViewProduct) }
        rcViewProduct.layoutManager = GridLayoutManager(requireContext(), 2)
        rcViewProduct.adapter = ProductAdapter(context, getlist())

    }

    private fun getlist(): MutableList<Product> {
        listProduct.add(
            Product(
                "Nike",
                "giay",
                "https://dqshop.vn/wp-content/uploads/2021/06/af1-gucci.jpg",
                123F
            )
        )
        listProduct.add(
            Product(
                "Jordan",
                "giay",
                "https://dqshop.vn/wp-content/uploads/2022/05/giay-nike-air-jordan-1-trophy-room-chicago-like-auth-1.jpg",
                123F
            )
        )
        listProduct.add(
            Product(
                "Vans",
                "Dep",
                "https://dqshop.vn/wp-content/uploads/2022/07/vans-caro-lv.jpg",
                153F
            )
        )
        listProduct.add(
            Product(
                "ABC",
                "giay",
                "https://dqshop.vn/wp-content/uploads/2022/05/giay-nike-air-jordan-1-trophy-room-chicago-like-auth-1.jpg",
                123F
            )
        )
        listProduct.add(
            Product(
                "XYZ",
                "giay",
                "https://dqshop.vn/wp-content/uploads/2021/06/af1-gucci.jpg",
                143F
            )

        )
        return listProduct
    }

    private fun buildingBillingFunc() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {
                TODO("Not yet implemented")
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
                        listdetailprd = productDetailsList
                    }
                }
            }

        })
    }
}