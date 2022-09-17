package com.example.e_commerce_android_app_template.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.android.billingclient.api.*
import com.bumptech.glide.Glide
import com.example.e_commerce_android_app_template.R
import com.example.e_commerce_android_app_template.model.Product
import com.example.e_commerce_android_app_template.viewmodel.ProductViewModel
import com.google.android.material.button.MaterialButton
import com.google.common.collect.ImmutableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val inapp_type_1 = "free_image_animal_15_day"
private const val inapp_type_2 = "free_image_animal_1_day"
private const val inapp_type_3 = "free_image_animal_30_day"
private const val inapp_type_4 = "free_image_animal_3_day"
private const val inapp_type_5 = "free_image_animal_7_day"

class DetailActivity : AppCompatActivity() {

    private val productViewModel: ProductViewModel by lazy {
        ViewModelProvider(
            this@DetailActivity, ProductViewModel.ProductFactory(
                this@DetailActivity.application
            )
        )[ProductViewModel::class.java]
    }
    private lateinit var billingClient: BillingClient
    private var productListDetail: MutableList<ProductDetails> = mutableListOf()
    private val imageView: ImageView by lazy { findViewById<ImageView>(R.id.imageView) }
    private val tvNameProduct: TextView by lazy { findViewById<TextView>(R.id.tvNameProduct) }
    private val tviconPrice: TextView by lazy { findViewById<TextView>(R.id.tviconPrice) }
    private val tvPriceProduct: TextView by lazy { findViewById<TextView>(R.id.tvPriceProduct) }
    private val btnAddProduct: MaterialButton by lazy { findViewById<MaterialButton>(R.id.btnAddProduct) }
    private val btnPay: MaterialButton by lazy { findViewById<MaterialButton>(R.id.btnPay) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle: Bundle? = intent.extras
        val id: Int = bundle?.getInt("id")!!
        val image: String? = bundle?.getString("image")
        val name: String? = bundle?.getString("name")
        val price: Float = bundle?.getFloat("price")!!
        val productType: Float = bundle?.getFloat("product_type")!!
//        val price2: String = bundle?.getString("price2")!!


        val purchasesUpdatedListener = PurchasesUpdatedListener { billingResult, purchases -> }

        billingClient = BillingClient.newBuilder(this)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases().build()
        Glide.with(this)
            .load(image)
            .centerCrop()
            .into(imageView)
        tvNameProduct.text = name.toString()
        tvPriceProduct.text = price.toString()

        val product = Product(name.toString(), productType.toString(), image.toString(), price)
        btnAddProduct.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                if (checkUser(product)) {
                    Toast.makeText(this@DetailActivity, "them that bai", Toast.LENGTH_SHORT).show()
                    return@launch
                }
                productViewModel.insertProduct(product)
                finish()
            }
/*
            val productDetailsParamsList = listOf(
                BillingFlowParams.ProductDetailsParams.newBuilder()
                    .setProductDetails(productListDetail[1])
                    .build()
            )
            val billingFlowParams =
                BillingFlowParams.newBuilder().setProductDetailsParamsList(productDetailsParamsList)
                    .build()
            val billingResult = billingClient.launchBillingFlow(this, billingFlowParams)


        }

 */
//        buidingBillingClientFunc()
        }
    }

    fun checkUser(product: Product): Boolean {
        val list: List<Product> = productViewModel.checkUser(product.id)
        return list != null && list.isNotEmpty()
    }
/*
//    private fun buidingBillingClientFunc() {
//        billingClient.startConnection(object : BillingClientStateListener {
//            override fun onBillingServiceDisconnected() {
//                TODO("Not yet implemented")
//            }
//
//            override fun onBillingSetupFinished(p0: BillingResult) {
//                if (p0.responseCode == BillingClient.BillingResponseCode.OK) {
//                    val queryProductDetailsParams =
//                        QueryProductDetailsParams.newBuilder().setProductList(
//                            ImmutableList.of(
//                                QueryProductDetailsParams.Product.newBuilder()
//                                    .setProductId(inapp_type_1)
//                                    .setProductType(BillingClient.ProductType.INAPP).build()
//                            )
//                        ).build()
//                    billingClient.queryProductDetailsAsync(queryProductDetailsParams) { billingResult, productDetailsList ->
//                        productListDetail = productDetailsList
//                        tviconPrice.text = productDetailsList[0].toString()
//                    }
//                }
//            }
//
//
//        })
//    }
}

 */
}