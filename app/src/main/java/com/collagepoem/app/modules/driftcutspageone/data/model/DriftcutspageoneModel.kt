package com.collagepoem.app.modules.driftcutspageone.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class DriftcutspageoneModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtTextholder: String? = MyApp.getInstance().resources.getString(R.string.lbl_flow_away)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescription: String? =
      MyApp.getInstance().resources.getString(R.string.msg_thanks_your_sha)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtUsedUser: String? = MyApp.getInstance().resources.getString(R.string.lbl_used_user)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPushed: String? = MyApp.getInstance().resources.getString(R.string.lbl_pushed2)

)
