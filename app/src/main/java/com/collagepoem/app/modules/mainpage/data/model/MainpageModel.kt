package com.collagepoem.app.modules.mainpage.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class MainpageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtWelcometext: String? =
      MyApp.getInstance().resources.getString(R.string.msg_welcome_back_st)
  ,
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
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTimeCNOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_2022_1_3)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOrderOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_one)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDescriptionOne: String? =
      MyApp.getInstance().resources.getString(R.string.msg_content_here_co2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTimeENOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_01_05)
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
