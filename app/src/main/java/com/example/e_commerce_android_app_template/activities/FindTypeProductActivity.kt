package com.example.e_commerce_android_app_template.activities

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce_android_app_template.R
import com.example.e_commerce_android_app_template.adapter.FindTypeProductAdapter
import com.example.e_commerce_android_app_template.model.StoreProduct
import com.example.e_commerce_android_app_template.viewmodel.ProductViewModel

class FindTypeProductActivity : AppCompatActivity() {
    private var listStr: MutableList<StoreProduct> = mutableListOf()
    private val findList: MutableList<StoreProduct> = mutableListOf()
    private val productViewModel: ProductViewModel by lazy {
        ViewModelProvider(
            this, ProductViewModel.ProductFactory(
                this.applicationContext as Application
            )
        )[ProductViewModel::class.java]
    }
    val storeProduct: StoreProduct
        get() {
            TODO()
        }
    val rcView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rcView) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_type_product)

        var bundle: Bundle? = intent.extras
        var id: String = bundle?.getString("id")!!
        var name: String? = bundle?.getString("name")

        listStr = getlist()
        var post: Int = -1
        for (i in listStr.indices) {
            if ((listStr[i].productType.contains(name!!))) {

                findList.addAll(
                    listOf(
                        StoreProduct(
                            listStr[i].name,
                            listStr[i].productType,
                            listStr[i].image,
                            listStr[i].price,
                        )
                    )

                )
//                findList.clear()
                post = i
            }
        }
        if (post != -1) {
            Toast.makeText(this, "found$name", Toast.LENGTH_SHORT).show()
            rcView.adapter = FindTypeProductAdapter(this, findList)
            rcView.layoutManager = GridLayoutManager(this, 2)
        } else {
            Toast.makeText(this, "not found$name", Toast.LENGTH_SHORT).show()

        }

    }

    private fun getlist(): MutableList<StoreProduct> {
        listStr.add(
            StoreProduct(
                "Nike", "Giày", "https://dqshop.vn/wp-content/uploads/2021/06/af1-gucci.jpg", 123F

            )
        )
        listStr.add(
            StoreProduct(
                "Jordan",
                "Giày",
                "https://dqshop.vn/wp-content/uploads/2022/05/giay-nike-air-jordan-1-trophy-room-chicago-like-auth-1.jpg",
                123F
            )
        )
        listStr.add(
            StoreProduct(
                "Vans",
                "Quần",
                "https://dqshop.vn/wp-content/uploads/2022/07/vans-caro-lv.jpg",
                153F
            )
        )
        listStr.add(
            StoreProduct(
                "ABC",
                "Giày",
                "https://dqshop.vn/wp-content/uploads/2022/05/giay-nike-air-jordan-1-trophy-room-chicago-like-auth-1.jpg",
                123F
            )
        )
        listStr.add(
            StoreProduct(
                "XYZ",
                "Giay",
                "https://dqshop.vn/wp-content/uploads/2021/06/af1-gucci.jpg",
                143F
            )

        )
        return listStr
    }
}