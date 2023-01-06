package com.collagepoem.app.modules.landinpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.landinpage.`data`.model.LandinpageModel
import org.koin.core.KoinComponent

class LandinpageVM : ViewModel(), KoinComponent {
  val landinpageModel: MutableLiveData<LandinpageModel> = MutableLiveData(LandinpageModel())

  var navArguments: Bundle? = null
}
