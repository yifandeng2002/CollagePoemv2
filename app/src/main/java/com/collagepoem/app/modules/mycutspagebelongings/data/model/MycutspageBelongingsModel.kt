package com.collagepoem.app.modules.mycutspagebelongings.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class MycutspageBelongingsModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtTextholder: String? = MyApp.getInstance().resources.getString(R.string.lbl_clips)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtHad: String? = MyApp.getInstance().resources.getString(R.string.lbl_had)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFlowaway: String? = MyApp.getInstance().resources.getString(R.string.lbl_flow_away)

)
