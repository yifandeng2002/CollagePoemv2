package com.collagepoem.app.modules.driftcutspagethree.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class DriftcutspagethreeModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtTextholder: String? = MyApp.getInstance().resources.getString(R.string.lbl_flow_away)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtThank: String? = MyApp.getInstance().resources.getString(R.string.msg_thank_you_for_y2)

)
