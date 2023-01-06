package com.collagepoem.app.modules.canvaspoem.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class CanvasPoemModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtTimeCN: String? = MyApp.getInstance().resources.getString(R.string.lbl_2022_1_3)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOrder: String? = MyApp.getInstance().resources.getString(R.string.lbl_one)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescription: String? =
      MyApp.getInstance().resources.getString(R.string.msg_content_here_co)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTimeEN: String? = MyApp.getInstance().resources.getString(R.string.lbl_01_05)

)
