package com.example.e_commerce_android_app_template.database

import android.app.Application
import android.content.Context
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.e_commerce_android_app_template.database.dao.ProductDao
import com.example.e_commerce_android_app_template.model.Product

class ProductRepository(app : Application) {
    private val prdDao :ProductDao

    init {
        val productDb : ProductDb = ProductDb.getinstance(app)
        prdDao = productDb.getProductDao()
    }
    suspend fun insertPrd(prd : Product){
        prdDao.insertPrd(prd) }
    fun getAllPrd() :LiveData<List<Product>> = prdDao.getAllPrd()
    fun checkProduct(id :Int): List<Product> =  prdDao.checkProduct(id)
    fun getAllDataWithType(product_type: String) = prdDao.getDataWithType(product_type)
}