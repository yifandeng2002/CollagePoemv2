package com.collagepoem.app.modules.driftcutspageone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collagepoem.app.R
import com.collagepoem.app.databinding.RowWorks3Binding
import com.collagepoem.app.modules.driftcutspageone.`data`.model.Works3RowModel
import kotlin.Int
import kotlin.collections.List

class WorksAdapter(
  var list: List<Works3RowModel>
) : RecyclerView.Adapter<WorksAdapter.RowWorks3VH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowWorks3VH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_works3,parent,false)
    return RowWorks3VH(view)
  }

  override fun onBindViewHolder(holder: RowWorks3VH, position: Int) {
    val works3RowModel = Works3RowModel()
    // TODO uncomment following line after integration with data source
    // val works3RowModel = list[position]
    holder.binding.works3RowModel = works3RowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<Works3RowModel>) {
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
      item: Works3RowModel
    ) {
    }
  }

  inner class RowWorks3VH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowWorks3Binding = RowWorks3Binding.bind(itemView)
    init {
      binding.linearColumnworktitle.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, Works3RowModel())
      }
    }
  }
}
