package com.collagepoem.app.modules.communitypage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.communitypage.`data`.model.CommunitypageModel
import org.koin.core.KoinComponent

class CommunitypageVM : ViewModel(), KoinComponent {
  val communitypageModel: MutableLiveData<CommunitypageModel> =
      MutableLiveData(CommunitypageModel())

  var navArguments: Bundle? = null
}
