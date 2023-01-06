package com.collagepoem.app.modules.communitypageinfo.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class CommentsRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtUsernameOne: String? = MyApp.getInstance().resources.getString(R.string.lbl_username1)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDuration: String? = MyApp.getInstance().resources.getString(R.string.lbl_2_days_ago)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtComment: String? = MyApp.getInstance().resources.getString(R.string.lbl_blablabla)

)
