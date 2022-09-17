package com.example.e_commerce_android_app_template.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.e_commerce_android_app_template.database.ProductRepository
import com.example.e_commerce_android_app_template.model.Product
import com.example.e_commerce_android_app_template.model.StoreProduct
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ProductViewModel(app: Application) : ViewModel() {
    private val productRepository: ProductRepository = ProductRepository(app)
//    private val strResponsitory: Storerepository = Storerepository(app)
    fun insertProduct(product: Product) {
        viewModelScope.launch {
            productRepository.insertPrd(product)
        }

    }

//    fun insertPrdStr(storeProduct: StoreProduct) {
//        viewModelScope.launch {
//            strResponsitory.insertStr(storeProduct)
//        }
//    }
//
//    fun getAllStr(): LiveData<List<StoreProduct>> = strResponsitory.getAllStr()

    fun getAllProduct(): LiveData<List<Product>> = productRepository.getAllPrd()
    fun checkUser(id: Int): List<Product> = productRepository.checkProduct(id)
    fun getAllDataWithType(product_type: String): LiveData<List<Product>> =
        productRepository.getAllDataWithType(product_type)

    class ProductFactory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom((ProductViewModel::class.java))) {
                return ProductViewModel(app) as T
            }
            throw  IllegalArgumentException("Unable construct viewModel")
        }
    }

}