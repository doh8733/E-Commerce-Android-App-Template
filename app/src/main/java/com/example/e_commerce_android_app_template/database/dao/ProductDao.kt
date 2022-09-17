package com.example.e_commerce_android_app_template.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.e_commerce_android_app_template.model.Product

@Dao
interface ProductDao {
    @Insert
    suspend fun insertPrd (product :Product)
    @Query("select * from products_name")
    fun getAllPrd(): LiveData<List<Product>>
    @Query("select *from products_name where id= :id")
    fun checkProduct(id: Int): List<Product>
    @Query("select * from products_name where product_type= :product_type")
    fun getDataWithType(product_type :String): LiveData<List<Product>>

}