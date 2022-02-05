package com.target.targetcasestudy.ui

import android.annotation.SuppressLint
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.target.targetcasestudy.R
import com.target.targetcasestudy.R.string
import com.target.targetcasestudy.data.DealItemViewData
import java.util.Locale

class DealItemAdapter constructor(private val dealList: List<DealItemViewData>, val clickListener: (DealItemViewData) -> Unit) :
  RecyclerView.Adapter<DealItemViewHolder>() {
  private var imageSize: Int = 0

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val view = inflater.inflate(R.layout.deal_list_item, parent, false)

    imageSize = view.context.resources.getDimensionPixelSize(R.dimen.deal_list_item_image_size)
    return DealItemViewHolder(view)
  }

  override fun getItemCount(): Int {
    return dealList.size ?: 0
  }

  @SuppressLint("SetTextI18n")
  override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
    dealList.let { dealList ->
      val item = dealList[position]
      viewHolder.itemView.findViewById<TextView>(R.id.deal_list_item_title).text = item.title
      viewHolder.itemView.findViewById<TextView>(R.id.deal_list_item_price).text = item.salePrice ?: item.regularPrice

      val wordToSpan: Spannable =
        SpannableString(viewHolder.itemView.context.getString(string.text_shipping_label))
      val color = viewHolder.itemView.context.resources.getColor(R.color.gray_color)
      wordToSpan.setSpan(ForegroundColorSpan(color), wordToSpan.length - 3, wordToSpan.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

      viewHolder.itemView.findViewById<TextView>(R.id.deal_list_item_ship_label).text = wordToSpan
      viewHolder.itemView.findViewById<TextView>(R.id.deal_list_item_aisle).text = item.aisle.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
          Locale.getDefault()
        ) else it.toString()
      }

      viewHolder.itemView.findViewById<ImageView>(R.id.deal_list_item_image_view)
        .load(item.image) {
          (crossfade(true))
          placeholder(R.drawable.deal_list_place_holder)
          size(imageSize)
        }
      viewHolder.itemView.setOnClickListener { clickListener(item) }
    }
  }
}

class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}