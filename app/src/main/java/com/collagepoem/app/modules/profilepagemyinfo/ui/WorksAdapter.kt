package com.collagepoem.app.modules.profilepagemyinfo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collagepoem.app.R
import com.collagepoem.app.databinding.RowWorksBinding
import com.collagepoem.app.modules.profilepagemyinfo.`data`.model.WorksRowModel
import kotlin.Int
import kotlin.collections.List

class WorksAdapter(
  var list: List<WorksRowModel>
) : RecyclerView.Adapter<WorksAdapter.RowWorksVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowWorksVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_works,parent,false)
    return RowWorksVH(view)
  }

  override fun onBindViewHolder(holder: RowWorksVH, position: Int) {
    val worksRowModel = WorksRowModel()
    // TODO uncomment following line after integration with data source
    // val worksRowModel = list[position]
    holder.binding.worksRowModel = worksRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<WorksRowModel>) {
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
      item: WorksRowModel
    ) {
    }
  }

  inner class RowWorksVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowWorksBinding = RowWorksBinding.bind(itemView)
  }
}
