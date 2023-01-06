package com.collagepoem.app.modules.profilepagemyinfo.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class ProfilepageMyinfoModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtThree: String? = MyApp.getInstance().resources.getString(R.string.lbl_3)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtPushed: String? = MyApp.getInstance().resources.getString(R.string.lbl_pushed)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTwoHundredEightySeven: String? = MyApp.getInstance().resources.getString(R.string.lbl_287)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFans: String? = MyApp.getInstance().resources.getString(R.string.lbl_fans)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTwelve: String? = MyApp.getInstance().resources.getString(R.string.lbl_12)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFollow: String? = MyApp.getInstance().resources.getString(R.string.lbl_follow2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtUsername: String? = MyApp.getInstance().resources.getString(R.string.msg_littlemonster_s)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTextslogan: String? = MyApp.getInstance().resources.getString(R.string.lbl_lalalalalaalal)

)
