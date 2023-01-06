package com.collagepoem.app.modules.profilepagemyinfo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.profilepagemyinfo.`data`.model.ProfilepageMyinfoModel
import com.collagepoem.app.modules.profilepagemyinfo.`data`.model.WorksRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class ProfilepageMyinfoVM : ViewModel(), KoinComponent {
  val profilepageMyinfoModel: MutableLiveData<ProfilepageMyinfoModel> =
      MutableLiveData(ProfilepageMyinfoModel())

  var navArguments: Bundle? = null

  val worksList: MutableLiveData<MutableList<WorksRowModel>> = MutableLiveData(mutableListOf())
}
