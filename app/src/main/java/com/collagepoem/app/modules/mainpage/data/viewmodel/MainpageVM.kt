package com.collagepoem.app.modules.mainpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.mainpage.`data`.model.MainpageModel
import org.koin.core.KoinComponent

class MainpageVM : ViewModel(), KoinComponent {
  val mainpageModel: MutableLiveData<MainpageModel> = MutableLiveData(MainpageModel())

  var navArguments: Bundle? = null
}
