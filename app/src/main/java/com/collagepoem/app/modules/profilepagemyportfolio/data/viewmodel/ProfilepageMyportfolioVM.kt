package com.collagepoem.app.modules.profilepagemyportfolio.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.profilepagemyportfolio.`data`.model.ProfilepageMyportfolioModel
import com.collagepoem.app.modules.profilepagemyportfolio.`data`.model.WorkRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class ProfilepageMyportfolioVM : ViewModel(), KoinComponent {
  val profilepageMyportfolioModel: MutableLiveData<ProfilepageMyportfolioModel> =
      MutableLiveData(ProfilepageMyportfolioModel())

  var navArguments: Bundle? = null

  val workList: MutableLiveData<MutableList<WorkRowModel>> = MutableLiveData(mutableListOf())
}
