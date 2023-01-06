package com.collagepoem.app.modules.communitypageinfoone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collagepoem.app.R
import com.collagepoem.app.databinding.RowComments1Binding
import com.collagepoem.app.modules.communitypageinfoone.`data`.model.Comments1RowModel
import kotlin.Int
import kotlin.collections.List

class CommentsAdapter(
  var list: List<Comments1RowModel>
) : RecyclerView.Adapter<CommentsAdapter.RowComments1VH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowComments1VH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_comments1,parent,false)
    return RowComments1VH(view)
  }

  override fun onBindViewHolder(holder: RowComments1VH, position: Int) {
    val comments1RowModel = Comments1RowModel()
    // TODO uncomment following line after integration with data source
    // val comments1RowModel = list[position]
    holder.binding.comments1RowModel = comments1RowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<Comments1RowModel>) {
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
      item: Comments1RowModel
    ) {
    }
  }

  inner class RowComments1VH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowComments1Binding = RowComments1Binding.bind(itemView)
  }
}
