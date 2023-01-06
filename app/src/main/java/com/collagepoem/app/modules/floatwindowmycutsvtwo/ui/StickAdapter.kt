package com.collagepoem.app.modules.floatwindowmycutsvtwo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collagepoem.app.R
import com.collagepoem.app.databinding.RowStickBinding
import com.collagepoem.app.modules.floatwindowmycutsvtwo.`data`.model.StickRowModel
import kotlin.Int
import kotlin.collections.List

class StickAdapter(
  var list: List<StickRowModel>
) : RecyclerView.Adapter<StickAdapter.RowStickVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowStickVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_stick,parent,false)
    return RowStickVH(view)
  }

  override fun onBindViewHolder(holder: RowStickVH, position: Int) {
    val stickRowModel = StickRowModel()
    // TODO uncomment following line after integration with data source
    // val stickRowModel = list[position]
    holder.binding.stickRowModel = stickRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<StickRowModel>) {
    list = newData
    notifyDataSetChanged()
  }

  fun setOnItemClickListener(clickListener: OnItemClickListener) {
    this.clickListener = clickListener
  }

  interface OnItemClickListener {
    fun onItemClick(
      view: View,
      position: Int,
      item: StickRowModel
    ) {
    }
  }

  inner class RowStickVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowStickBinding = RowStickBinding.bind(itemView)
  }
}
