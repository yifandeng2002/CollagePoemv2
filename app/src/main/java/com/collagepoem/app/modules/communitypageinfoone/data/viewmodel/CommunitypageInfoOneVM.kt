package com.collagepoem.app.modules.communitypageinfoone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.communitypageinfoone.`data`.model.Comments1RowModel
import com.collagepoem.app.modules.communitypageinfoone.`data`.model.CommunitypageInfoOneModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class CommunitypageInfoOneVM : ViewModel(), KoinComponent {
  val communitypageInfoOneModel: MutableLiveData<CommunitypageInfoOneModel> =
      MutableLiveData(CommunitypageInfoOneModel())

  var navArguments: Bundle? = null

  val commentsList: MutableLiveData<MutableList<Comments1RowModel>> =
      MutableLiveData(mutableListOf())
}
