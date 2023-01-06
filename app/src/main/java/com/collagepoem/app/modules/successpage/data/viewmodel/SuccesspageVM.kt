package com.collagepoem.app.modules.successpage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.successpage.`data`.model.SuccesspageModel
import org.koin.core.KoinComponent

class SuccesspageVM : ViewModel(), KoinComponent {
  val successpageModel: MutableLiveData<SuccesspageModel> = MutableLiveData(SuccesspageModel())

  var navArguments: Bundle? = null
}
