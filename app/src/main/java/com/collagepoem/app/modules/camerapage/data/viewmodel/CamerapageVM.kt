package com.collagepoem.app.modules.camerapage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.collagepoem.app.modules.camerapage.`data`.model.CamerapageModel
import org.koin.core.KoinComponent

class CamerapageVM : ViewModel(), KoinComponent {
  val camerapageModel: MutableLiveData<CamerapageModel> = MutableLiveData(CamerapageModel())

  var navArguments: Bundle? = null
}
