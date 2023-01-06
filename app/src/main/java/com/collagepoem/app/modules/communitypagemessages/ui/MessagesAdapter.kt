package com.collagepoem.app.modules.communitypagemessages.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.collagepoem.app.R
import com.collagepoem.app.databinding.RowMessagesBinding
import com.collagepoem.app.modules.communitypagemessages.`data`.model.MessagesRowModel
import kotlin.Int
import kotlin.collections.List

class MessagesAdapter(
  var list: List<MessagesRowModel>
) : RecyclerView.Adapter<MessagesAdapter.RowMessagesVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowMessagesVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_messages,parent,false)
    return RowMessagesVH(view)
  }

  override fun onBindViewHolder(holder: RowMessagesVH, position: Int) {
    val messagesRowModel = MessagesRowModel()
    // TODO uncomment following line after integration with data source
    // val messagesRowModel = list[position]
    holder.binding.messagesRowModel = messagesRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<MessagesRowModel>) {
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
      item: MessagesRowModel
    ) {
    }
  }

  inner class RowMessagesVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowMessagesBinding = RowMessagesBinding.bind(itemView)
  }
}
