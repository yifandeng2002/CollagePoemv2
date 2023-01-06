package com.collagepoem.app.modules.profilepagemyportfolio.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class ProfilepageMyportfolioModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtOwn: String? = MyApp.getInstance().resources.getString(R.string.lbl_works)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSkartch: String? = MyApp.getInstance().resources.getString(R.string.lbl_skartch)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtHomeOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_poems)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCommunity: String? = MyApp.getInstance().resources.getString(R.string.lbl_community)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMy: String? = MyApp.getInstance().resources.getString(R.string.lbl_my)

)
