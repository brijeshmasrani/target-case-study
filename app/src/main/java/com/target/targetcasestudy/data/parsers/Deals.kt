package com.target.targetcasestudy.data.parsers


import com.google.gson.annotations.SerializedName

data class Deals(
    @SerializedName("products")
    val products: List<Product>
)