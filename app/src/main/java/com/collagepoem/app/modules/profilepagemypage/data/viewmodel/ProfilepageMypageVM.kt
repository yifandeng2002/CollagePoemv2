package com.collagepoem.app.modules.profilepagemypage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.profilepagemypage.`data`.model.ProfilepageMypageModel
import com.collagepoem.app.modules.profilepagemypage.`data`.model.Works1RowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class ProfilepageMypageVM : ViewModel(), KoinComponent {
  val profilepageMypageModel: MutableLiveData<ProfilepageMypageModel> =
      MutableLiveData(ProfilepageMypageModel())

  var navArguments: Bundle? = null

  val worksList: MutableLiveData<MutableList<Works1RowModel>> = MutableLiveData(mutableListOf())
}
