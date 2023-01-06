package com.collagepoem.app.modules.communitypagemessages.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class MessagesRowModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtUsername: String? = MyApp.getInstance().resources.getString(R.string.lbl_admin)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtMessagecontent: String? =
      MyApp.getInstance().resources.getString(R.string.msg_thank_you_for_y)

)
