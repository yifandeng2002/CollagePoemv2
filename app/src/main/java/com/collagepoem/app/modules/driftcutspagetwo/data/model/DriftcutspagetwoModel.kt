package com.collagepoem.app.modules.driftcutspagetwo.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class DriftcutspagetwoModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtTextholder: String? = MyApp.getInstance().resources.getString(R.string.lbl_flow_away)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTextfrom: String? = MyApp.getInstance().resources.getString(R.string.lbl_from)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTexttime: String? = MyApp.getInstance().resources.getString(R.string.lbl_collect_time)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtBridcollectTa: String? =
      MyApp.getInstance().resources.getString(R.string.msg_brid_collect_ta)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDuration: String? = MyApp.getInstance().resources.getString(R.string.msg_2022_10_12_1day)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDurationOne: String? =
      MyApp.getInstance().resources.getString(R.string.msg_left1daystick_w)

)
