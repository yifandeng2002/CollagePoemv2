package com.collagepoem.app.modules.driftcutspagethree.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.driftcutspagethree.`data`.model.DriftcutspagethreeModel
import org.koin.core.KoinComponent

class DriftcutspagethreeVM : ViewModel(), KoinComponent {
  val driftcutspagethreeModel: MutableLiveData<DriftcutspagethreeModel> =
      MutableLiveData(DriftcutspagethreeModel())

  var navArguments: Bundle? = null
}
