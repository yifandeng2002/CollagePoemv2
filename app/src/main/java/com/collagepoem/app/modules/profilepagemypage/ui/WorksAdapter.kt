package com.collagepoem.app.modules.profilepagemypage.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collagepoem.app.R
import com.collagepoem.app.databinding.RowWorks1Binding
import com.collagepoem.app.modules.profilepagemypage.`data`.model.Works1RowModel
import kotlin.Int
import kotlin.collections.List

class WorksAdapter(
  var list: List<Works1RowModel>
) : RecyclerView.Adapter<WorksAdapter.RowWorks1VH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowWorks1VH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_works1,parent,false)
    return RowWorks1VH(view)
  }

  override fun onBindViewHolder(holder: RowWorks1VH, position: Int) {
    val works1RowModel = Works1RowModel()
    // TODO uncomment following line after integration with data source
    // val works1RowModel = list[position]
    holder.binding.works1RowModel = works1RowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<Works1RowModel>) {
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
      item: Works1RowModel
    ) {
    }
  }

  inner class RowWorks1VH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowWorks1Binding = RowWorks1Binding.bind(itemView)
  }
}
