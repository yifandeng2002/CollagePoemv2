package com.collagepoem.app.modules.loadingworkpage.`data`.model

import com.collagepoem.app.R
import com.collagepoem.app.appcomponents.di.MyApp
import kotlin.String

data class LoadingworkpageModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtDescription: String? = MyApp.getInstance().resources.getString(R.string.msg_in_preparation)

)
