package com.collagepoem.app.modules.communitypage.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class CommunitypageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtFollowed: String? = MyApp.getInstance().resources.getString(R.string.lbl_follow)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtRecommand: String? = MyApp.getInstance().resources.getString(R.string.lbl_recommand)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtLatest: String? = MyApp.getInstance().resources.getString(R.string.lbl_latest)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtUsername: String? = MyApp.getInstance().resources.getString(R.string.lbl_admin)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSignature: String? = MyApp.getInstance().resources.getString(R.string.msg_this_is_a_signa)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtContent: String? = MyApp.getInstance().resources.getString(R.string.msg_had_something_i)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTxtsharecount: String? = MyApp.getInstance().resources.getString(R.string.lbl_2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTxtcollectcoun: String? = MyApp.getInstance().resources.getString(R.string.lbl_12)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTxtlikecount: String? = MyApp.getInstance().resources.getString(R.string.lbl_87)
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
