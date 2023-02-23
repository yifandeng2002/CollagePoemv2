package com.collagepoem.app.modules.mycutspagebelongings.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.collagepoem.app.R
import com.collagepoem.app.modules.mycutspagebelongings.`data`.model.SticksRowModel
import kotlin.Int
import kotlin.collections.List
import com.collagepoem.app.databinding.RowSticksBinding


class SticksAdapter(
  var list: List<SticksRowModel>
) : RecyclerView.Adapter<SticksAdapter.RowSticksVH>() {
//  private var clickListener: OnItemClickListener? = null

  inner class RowSticksVH(view: View) : RecyclerView.ViewHolder(view) {
    val txtName: TextView = view.findViewById(R.id.txtBookname)
    val txtCollect: TextView = view.findViewById(R.id.txtRemainingtime)
  }


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowSticksVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_sticks,parent,false)
    return RowSticksVH(view)
  }

  override fun onBindViewHolder(holder: RowSticksVH, position: Int) {
    val thisList = list
    holder.txtName.setText(thisList[position].txtBookname)
    holder.txtCollect.setText(thisList[position].txtRemainingtime.toString())
  }

  override fun getItemCount(): Int = list.size

}