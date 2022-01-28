package com.target.targetcasestudy.ui

import android.annotation.SuppressLint
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ImageRequest
import com.target.targetcasestudy.R
import com.target.targetcasestudy.R.string
import com.target.targetcasestudy.data.DealItemViewData
import java.util.Locale

class DealItemAdapter : RecyclerView.Adapter<DealItemViewHolder>() {

  private var dealList: List<DealItemViewData>? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val view = inflater.inflate(R.layout.deal_list_item, parent, false)
    return DealItemViewHolder(view)
  }

  override fun getItemCount(): Int {
    return dealList?.size ?: 0
  }

  fun setData(dealList: List<DealItemViewData>) {
    this.dealList = dealList
    this.notifyDataSetChanged()
  }

  @SuppressLint("SetTextI18n")
  override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
    dealList?.let { dealList ->
      val item = dealList[position]
      viewHolder.itemView.findViewById<TextView>(R.id.deal_list_item_title).text = item.title
      viewHolder.itemView.findViewById<TextView>(R.id.deal_list_item_price).text = item.price

      val wordtoSpan: Spannable =
        SpannableString(viewHolder.itemView.context.getString(string.text_shipping_label))
      val color = viewHolder.itemView.context.resources.getColor(R.color.gray_color)
      wordtoSpan.setSpan(ForegroundColorSpan(color), wordtoSpan.length - 3, wordtoSpan.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

      viewHolder.itemView.findViewById<TextView>(R.id.deal_list_item_ship_label).text = wordtoSpan
      viewHolder.itemView.findViewById<TextView>(R.id.deal_list_item_aisle).text = item.aisle.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
          Locale.getDefault()
        ) else it.toString()
      }

      viewHolder.itemView.findViewById<ImageView>(R.id.deal_list_item_image_view)
        .load(item.image) {
          (
              listener(onSuccess = { _, _ ->
                println("This is loaded")
              }, onError = { request: ImageRequest, throwable: Throwable ->
                throwable.message?.let { Log.e("DealItemAdapter", it) }
              })
              )
        }

    }

  }
}

class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}