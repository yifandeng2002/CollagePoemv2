package com.collagepoem.app.modules.profilepagemyinfoone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.profilepagemyinfoone.`data`.model.ProfilepageMyinfoOneModel
import com.collagepoem.app.modules.profilepagemyinfoone.`data`.model.Works2RowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class ProfilepageMyinfoOneVM : ViewModel(), KoinComponent {
  val profilepageMyinfoOneModel: MutableLiveData<ProfilepageMyinfoOneModel> =
      MutableLiveData(ProfilepageMyinfoOneModel())

  var navArguments: Bundle? = null

  val worksList: MutableLiveData<MutableList<Works2RowModel>> = MutableLiveData(mutableListOf())
}
