package com.example.e_commerce_android_app_template.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.e_commerce_android_app_template.database.dao.StoreproductDao
import com.example.e_commerce_android_app_template.model.StoreProduct

//class Storerepository(app:Application){
//    private val storeDao : StoreproductDao
//    init {
//        val storePrdDB : StorePrdDB = StorePrdDB.getinstance(app)
//        storeDao = storePrdDB.getStoreDao()
//    }
//    suspend fun insertStr(str : StoreProduct){
//        storeDao.insertPrd(str) }
//    fun getAllStr() : LiveData<List<StoreProduct>> = storeDao.getAllStorePrd()
//
//}