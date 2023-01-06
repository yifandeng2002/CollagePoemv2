package com.collagepoem.app.modules.albumpage.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collagepoem.app.R
import com.collagepoem.app.databinding.RowImagegridBinding
import com.collagepoem.app.modules.albumpage.`data`.model.ImagegridRowModel
import kotlin.Int
import kotlin.collections.List

class ImagegridAdapter(
  var list: List<ImagegridRowModel>
) : RecyclerView.Adapter<ImagegridAdapter.RowImagegridVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowImagegridVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_imagegrid,parent,false)
    return RowImagegridVH(view)
  }

  override fun onBindViewHolder(holder: RowImagegridVH, position: Int) {
    val imagegridRowModel = ImagegridRowModel()
    // TODO uncomment following line after integration with data source
    // val imagegridRowModel = list[position]
    holder.binding.imagegridRowModel = imagegridRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ImagegridRowModel>) {
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
      item: ImagegridRowModel
    ) {
    }
  }

  inner class RowImagegridVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowImagegridBinding = RowImagegridBinding.bind(itemView)
  }
}
