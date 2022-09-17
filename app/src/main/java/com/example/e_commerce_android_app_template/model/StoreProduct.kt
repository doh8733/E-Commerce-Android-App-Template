package com.example.e_commerce_android_app_template.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "store_product")
class StoreProduct(
    @ColumnInfo(name = "name_st") var name: String,
    @ColumnInfo(name = "product_type_st") var productType: String,
    @ColumnInfo(name = "image_st") var image: String,
    @ColumnInfo(name = "price_st") var price: Float,
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}