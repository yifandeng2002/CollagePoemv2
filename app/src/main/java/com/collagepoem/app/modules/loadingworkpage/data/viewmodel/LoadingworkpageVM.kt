package com.collagepoem.app.modules.loadingworkpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.loadingworkpage.`data`.model.LoadingworkpageModel
import org.koin.core.KoinComponent

class LoadingworkpageVM : ViewModel(), KoinComponent {
  val loadingworkpageModel: MutableLiveData<LoadingworkpageModel> =
      MutableLiveData(LoadingworkpageModel())

  var navArguments: Bundle? = null
}
