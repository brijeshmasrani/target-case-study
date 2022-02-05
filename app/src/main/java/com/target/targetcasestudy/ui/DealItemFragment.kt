package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import coil.load
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.DealItemViewData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealItemFragment(private val dealItemViewData: DealItemViewData) : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_deal_item, container, false)

    val itemImage = view.findViewById<ImageView>(R.id.deal_detail_image)
    val itemSalePrice = view.findViewById<TextView>(R.id.deal_detail_item_sale_price)
    val itemRegularPrice = view.findViewById<TextView>(R.id.deal_detail_item_regular_price)
    val itemTitle = view.findViewById<TextView>(R.id.deal_detail_item_name)
    val itemDetail = view.findViewById<TextView>(R.id.deal_detail_item_detail)

    itemImage.load(dealItemViewData.image)
    itemSalePrice.text = dealItemViewData.salePrice
    itemRegularPrice.text = dealItemViewData.regularPrice
    itemTitle.text = dealItemViewData.title
    itemDetail.text = dealItemViewData.description
    return view
  }
}
