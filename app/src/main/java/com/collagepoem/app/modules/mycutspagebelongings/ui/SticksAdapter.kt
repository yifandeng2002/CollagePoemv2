package com.collagepoem.app.modules.mycutspagebelongings.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collagepoem.app.R
import com.collagepoem.app.databinding.RowSticksBinding
import com.collagepoem.app.modules.mycutspagebelongings.`data`.model.SticksRowModel
import kotlin.Int
import kotlin.collections.List

class SticksAdapter(
  var list: List<SticksRowModel>
) : RecyclerView.Adapter<SticksAdapter.RowSticksVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowSticksVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_sticks,parent,false)
    return RowSticksVH(view)
  }

  override fun onBindViewHolder(holder: RowSticksVH, position: Int) {
    val sticksRowModel = SticksRowModel()
    // TODO uncomment following line after integration with data source
    // val sticksRowModel = list[position]
    holder.binding.sticksRowModel = sticksRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<SticksRowModel>) {
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
      item: SticksRowModel
    ) {
    }
  }

  inner class RowSticksVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowSticksBinding = RowSticksBinding.bind(itemView)
  }
}
