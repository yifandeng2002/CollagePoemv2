package com.collagepoem.app.modules.communitypageinfo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.communitypageinfo.`data`.model.CommentsRowModel
import com.collagepoem.app.modules.communitypageinfo.`data`.model.CommunitypageInfoModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class CommunitypageInfoVM : ViewModel(), KoinComponent {
  val communitypageInfoModel: MutableLiveData<CommunitypageInfoModel> =
      MutableLiveData(CommunitypageInfoModel())

  var navArguments: Bundle? = null

  val commentsList: MutableLiveData<MutableList<CommentsRowModel>> =
      MutableLiveData(mutableListOf())
}
