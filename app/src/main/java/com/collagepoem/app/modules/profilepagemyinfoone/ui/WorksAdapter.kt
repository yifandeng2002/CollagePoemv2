package com.collagepoem.app.modules.profilepagemyinfoone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collagepoem.app.R
import com.collagepoem.app.databinding.RowWorks2Binding
import com.collagepoem.app.modules.profilepagemyinfoone.`data`.model.Works2RowModel
import kotlin.Int
import kotlin.collections.List

class WorksAdapter(
  var list: List<Works2RowModel>
) : RecyclerView.Adapter<WorksAdapter.RowWorks2VH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowWorks2VH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_works2,parent,false)
    return RowWorks2VH(view)
  }

  override fun onBindViewHolder(holder: RowWorks2VH, position: Int) {
    val works2RowModel = Works2RowModel()
    // TODO uncomment following line after integration with data source
    // val works2RowModel = list[position]
    holder.binding.works2RowModel = works2RowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<Works2RowModel>) {
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
      item: Works2RowModel
    ) {
    }
  }

  inner class RowWorks2VH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowWorks2Binding = RowWorks2Binding.bind(itemView)
  }
}
