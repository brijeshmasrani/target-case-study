package com.target.targetcasestudy.data

data class DealItemViewData(
  val id: Int,
  val title: String,
  val price: String,
  var aisle: String,
  val image: String?
)