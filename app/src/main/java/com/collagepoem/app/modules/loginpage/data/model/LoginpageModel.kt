package com.collagepoem.app.modules.loginpage.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class LoginpageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtSlogan: String? = MyApp.getInstance().resources.getString(R.string.msg_start_in_instan)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etAcoountValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etPasswordValue: String? = null
)
