package com.collagepoem.app.modules.profilepagemypage.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class ProfilepageMypageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtName: String? = MyApp.getInstance().resources.getString(R.string.msg_littlemonster_s)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSlogan: String? = MyApp.getInstance().resources.getString(R.string.msg_lalalalalalalal)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTextpersonalh: String? = MyApp.getInstance().resources.getString(R.string.lbl_personal)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtWorknumber: String? = MyApp.getInstance().resources.getString(R.string.lbl_9)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtWorks: String? = MyApp.getInstance().resources.getString(R.string.lbl_works)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFlipnumber: String? = MyApp.getInstance().resources.getString(R.string.lbl_20)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtFlips: String? = MyApp.getInstance().resources.getString(R.string.lbl_filps)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCollectnumber: String? = MyApp.getInstance().resources.getString(R.string.lbl_12)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCollect: String? = MyApp.getInstance().resources.getString(R.string.lbl_collection)
  ,
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
