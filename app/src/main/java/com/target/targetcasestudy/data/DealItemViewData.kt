package com.target.targetcasestudy.data

data class DealItemViewData(
  val id: Int,
  val title: String,
  val description:String,
  val salePrice: String?,
  val regularPrice: String,
  var aisle: String,
  val image: String?
)