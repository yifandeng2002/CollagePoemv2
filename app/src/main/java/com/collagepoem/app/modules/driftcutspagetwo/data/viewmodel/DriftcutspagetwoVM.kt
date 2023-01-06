package com.collagepoem.app.modules.driftcutspagetwo.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.driftcutspagetwo.`data`.model.DriftcutspagetwoModel
import org.koin.core.KoinComponent

class DriftcutspagetwoVM : ViewModel(), KoinComponent {
  val driftcutspagetwoModel: MutableLiveData<DriftcutspagetwoModel> =
      MutableLiveData(DriftcutspagetwoModel())

  var navArguments: Bundle? = null
}
