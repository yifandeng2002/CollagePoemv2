package com.collagepoem.app.modules.communitypageinfoone.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class CommunitypageInfoOneModel(
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
  var txtFowardCounter: String? = MyApp.getInstance().resources.getString(R.string.lbl_foward_2)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCommentCounter: String? = MyApp.getInstance().resources.getString(R.string.lbl_comment_35)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etAddcommentValue: String? = null
)
