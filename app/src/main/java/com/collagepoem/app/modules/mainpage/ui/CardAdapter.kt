package com.collagepoem.app.modules.mainpage.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.collagepoem.app.R
import com.collagepoem.app.modules.canvaspoem.ui.CanvasPoemActivity
import com.collagepoem.app.modules.mainpage.PoemCut
import com.collagepoem.app.modules.mainpage.data.model.CardRowModel
import com.collagepoem.app.modules.mycutspagebelongings.`data`.model.SticksRowModel
import kotlin.Int
import kotlin.collections.List
import java.sql.Date


class CardAdapter(
    var list: List<CardRowModel>
) : RecyclerView.Adapter<CardAdapter.RowCardVH>() {
//  private var clickListener: OnItemClickListener? = null

    private var context: Context? = null
    inner class RowCardVH(view: View) : RecyclerView.ViewHolder(view) {
        val txtTime: TextView = view.findViewById(R.id.txtTimeCN)
        val txtOrd: TextView = view.findViewById(R.id.txtOrder)
        val txtDes: TextView = view.findViewById(R.id.txtDescription)
        val txtEng: TextView = view.findViewById(R.id.txtTimeEN)

        val imageCard: ImageView = view.findViewById(R.id.imagePoemcard)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowCardVH {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.row_card,parent,false)
        context = view.context
        return RowCardVH(view)
    }

    override fun onBindViewHolder(holder: RowCardVH, position: Int) {
        val thisList = list
        holder.txtTime.setText(thisList[position].TimeCN.toString())
        holder.txtOrd.setText(thisList[position].Order)
        holder.txtEng.setText(thisList[position].TimeEN.toString())
        holder.txtDes.setText(thisList[position].Descrip)
        holder.txtDes.setOnClickListener{
            PoemCut.txtthis = holder.txtDes.text.toString()
            PoemCut.txtName = holder.txtOrd.text.toString()
            PoemCut.txtTimeen = holder.txtEng.text.toString()
            PoemCut.txtTimecn = holder.txtTime.text.toString()
            val destIntent = Intent(context, CanvasPoemActivity::class.java)
            this.context!!.startActivity(destIntent)
        }
        holder.imageCard.setImageDrawable(thisList[position].imageCardView)
        holder.imageCard.setOnClickListener{
            PoemCut.txtthis = holder.txtDes.text.toString()
            PoemCut.txtName = holder.txtOrd.text.toString()
            PoemCut.txtTimeen = holder.txtEng.text.toString()
            PoemCut.txtTimecn = holder.txtTime.text.toString()
            val destIntent = Intent(context, CanvasPoemActivity::class.java)
            this.context!!.startActivity(destIntent)
        }
    }
    override fun getItemCount(): Int = list.size
}