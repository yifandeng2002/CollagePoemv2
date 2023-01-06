package com.collagepoem.app.modules.communitypageinfo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collagepoem.app.R
import com.collagepoem.app.databinding.RowCommentsBinding
import com.collagepoem.app.modules.communitypageinfo.`data`.model.CommentsRowModel
import kotlin.Int
import kotlin.collections.List

class CommentsAdapter(
  var list: List<CommentsRowModel>
) : RecyclerView.Adapter<CommentsAdapter.RowCommentsVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowCommentsVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_comments,parent,false)
    return RowCommentsVH(view)
  }

  override fun onBindViewHolder(holder: RowCommentsVH, position: Int) {
    val commentsRowModel = CommentsRowModel()
    // TODO uncomment following line after integration with data source
    // val commentsRowModel = list[position]
    holder.binding.commentsRowModel = commentsRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<CommentsRowModel>) {
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
      item: CommentsRowModel
    ) {
    }
  }

  inner class RowCommentsVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowCommentsBinding = RowCommentsBinding.bind(itemView)
  }
}
