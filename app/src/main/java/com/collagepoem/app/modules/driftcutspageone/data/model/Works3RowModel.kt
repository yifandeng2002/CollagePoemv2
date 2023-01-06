package com.collagepoem.app.modules.driftcutspageone.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class Works3RowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtWorktitle: String? = MyApp.getInstance().resources.getString(R.string.lbl_tianchai)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLikecount: String? = MyApp.getInstance().resources.getString(R.string.lbl_3)

)
