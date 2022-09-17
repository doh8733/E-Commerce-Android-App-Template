package com.example.e_commerce_android_app_template.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.e_commerce_android_app_template.database.dao.ProductDao
import com.example.e_commerce_android_app_template.model.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDb  :RoomDatabase(){
    abstract fun getProductDao():ProductDao

    companion object{
        @Volatile
        private var instance :ProductDb? = null

        fun getinstance(context: Context): ProductDb{
            if (instance == null){
                instance = Room.databaseBuilder(context,ProductDb::class.java,"producDB").build()
            }
            return instance!!
        }
    }
}