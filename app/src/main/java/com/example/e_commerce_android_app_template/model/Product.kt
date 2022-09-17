package com.example.e_commerce_android_app_template.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "products_name")
data class Product(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "product_type") var productType: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "price") var price: Float,

) : Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}