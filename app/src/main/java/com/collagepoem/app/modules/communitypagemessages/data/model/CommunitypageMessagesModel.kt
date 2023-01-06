package com.collagepoem.app.modules.communitypagemessages.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class CommunitypageMessagesModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtTxtmessage: String? = MyApp.getInstance().resources.getString(R.string.lbl_notification)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTxtmessageOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_message)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTxtcomment: String? = MyApp.getInstance().resources.getString(R.string.lbl_comment)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTxtatme: String? = MyApp.getInstance().resources.getString(R.string.lbl_me)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTxtlike: String? = MyApp.getInstance().resources.getString(R.string.lbl_like)

)
