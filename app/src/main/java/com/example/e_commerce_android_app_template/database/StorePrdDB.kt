package com.example.e_commerce_android_app_template.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.e_commerce_android_app_template.database.dao.StoreproductDao
import com.example.e_commerce_android_app_template.model.StoreProduct

@Database(entities = [StoreProduct::class], version = 1)
abstract class StorePrdDB  :RoomDatabase(), StoreproductDao {
//    abstract fun getStoreDao(): StorePrdDB
//
//    companion object{
//        @Volatile
//        private var instance : StorePrdDB? = null
//
//        fun getinstance(context: Context): StorePrdDB {
//            if (instance == null){
//                instance = Room.databaseBuilder(context, StorePrdDB::class.java,"StorePrdDB").build()
//            }
//            return instance!!
//        }
//    }
}