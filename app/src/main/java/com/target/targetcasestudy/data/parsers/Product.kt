package com.target.targetcasestudy.data.parsers


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("aisle")
    val aisle: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("regular_price")
    val regularPrice: RegularPrice,
    @SerializedName("sale_price")
    val salePrice: SalePrice?,
    @SerializedName("title")
    val title: String
)